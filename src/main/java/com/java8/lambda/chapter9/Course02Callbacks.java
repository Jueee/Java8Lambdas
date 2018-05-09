package com.java8.lambda.chapter9;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.platform.Verticle;



/**
 * 	回调
 *	
 *	@author hzweiyongqiang
 */
public class Course02Callbacks {
	/**
	 * 	为了展示非阻塞式 I/O 的原则，我们将运行一个极其简单的聊天应用，没有那些花里胡哨的功能。
	 * 	当用户第一次连接应用时，需要设定用户名，随后便可通过应用收发信息。
	 * 
	 * 	如果将消息发送到有多个处理程序监听的地址，则会轮询决定哪个处理程序会接收到消息。
	 */
	
	/**
	 *	接收 TCP 连接
	 *	在聊天应用中，我们用它建立一个接收 TCP 连接的服务器。
	 */
	public class ChatVerticle extends Verticle {
		@Override
		public void start() {
			System.out.println("BasicVerticle started");
			vertx.createNetServer()
				 .connectHandler(socket->{							// 回调：每当有用户连接到聊天应用时，都会调用该 Lambda 表达式
					 												// 好处是，应用不必控制线程模型——Vert.x 框架为我们管理线程，打理好了一切相关复杂性，程序员只需考虑事件和回调就够了。
					 container.logger().info("socket connected");
					 socket.dataHandler(new User(socket, this));	// dataHandler 注册了另外一个回调，每当从网络套接字读取数据时，该回调就会被调用
				 }).listen(10_000);
			container.logger().info("ChatVerticle started");
		}
	}
	/**
	 * 	处理用户连接
	 */
	public class User implements Handler<Buffer>{
		private final Pattern newline = Pattern.compile("\\n");	// 匹配换行符
		private final NetSocket socket;
		private final Set<String> names;
		private final EventBus eventBus;
		private Optional<String> name;
		
		public User(NetSocket socket,Verticle verticle) {
			Vertx vertx  = verticle.getVertx();
			this.socket = socket;
			names = vertx.sharedData().getSet("names");
			eventBus = vertx.eventBus();
			name = Optional.empty();
		}
		@Override
		public void handle(Buffer buffer) {				// 变量 buffer 包含了网络连接写入的数据，我们使用的是一个分行的文本协议，因此需要先将其转换成一个字符串，然后依换行符分割
			newline.splitAsStream(buffer.toString())	// splitAsStream 方法使用正则表达式将字符串分割好后，生成一个包含分割结果的流对象
				   .forEach(line->{
					   if (!name.isPresent()) {			// 用户连上聊天服务器后，首先要做的事是设置用户名。
						   setName(line);				// 如果用户名未知，则执行设置用户名的逻辑
					   } else {
						   handleMessage(line);			// 否则正常处理聊天消息。
					   }
				   });
		}
		/**
		 * 	设置用户名
		 *	@param name
		 */
		public void setName(String name) {
			this.name = Optional.of(name);
		}
		/**
		 * 	注册聊天消息
		 * 	接收来自其他用户的消息，并且将它们传递给聊天程序客户端，让接收者能够读取消息。
		 * 	registerHandler 方法将一个处理程序和一个地址关联，有消息发送给该地址时，就将之作为参数传递给处理程序，并且自动调用处理程序
		 * 	使用用户名作为地址。
		 *	@param line
		 */
		public void handleMessage(String line) {
			eventBus.registerHandler(name.get(), (Message<String> msg)->{
				sendClient(msg.body());
			});
		}
		public void sendClient(String message) {
			sendMessage(name.get(), message);
		}
		/**
		 * 	发送聊天信息
		 *	@param message
		 */
		public void sendMessage(String user,String message) {
			eventBus.send(user, name.get() + ">" + message);	// 
		}
		/**
		 * 	向关注者群发消息
		 *	@param message
		 */
		public void broadcastMessage(String message) {
			String name = this.name.get();
			eventBus.publish(name + ".followers", name + ">" + message);
		}
		/**
		 * 	接收群发的消息
		 *	@param user
		 */
		public void followUser(String user) {
			eventBus.registerHandler(user + ".followers", (Message<String> message) -> {
				sendClient(message.body());
			});
		}
	}
	
	public static void main(String[] args) {
		Course02Callbacks test = new Course02Callbacks();
	}
}
