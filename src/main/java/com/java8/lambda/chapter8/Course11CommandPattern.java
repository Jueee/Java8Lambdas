package com.java8.lambda.chapter8;

import java.util.ArrayList;
import java.util.List;

/**
 * 	命令者模式
 *	
 *	命令者是一个对象，它封装了调用另一个方法的所有细节，命令者模式使用该对象，可以编写出根据运行期条件，顺序调用方法的一般化代码。
 *
 *	命令者模式中有四个类参与其中：
 *	1、命令接收者：执行实际任务。
 *	2、命令者：封装了所有调用命令执行者的信息。
 *	3、发起者：控制一个或多个命令的顺序和执行。
 *	4、客户端：创建具体的命令者实例。
 *
 *
 *	@author hzweiyongqiang
 */
public class Course11CommandPattern {
	/**
	 * 	看一个命令者模式的具体例子，看看如何使用 Lambda 表达式改进该模式。
	 * 	假设有一个 GUI  Editor 组件，在上面可以执行 open 、 save 等一系列操作。
	 * 	现在我们想实现宏功能——也就是说，可以将一系列操作录制下来，日后作为一个操作执行，这就是我们的命令接收者。
	 */
	// 文本编辑器可能具有的一般功能
	public interface Editor{	
		public void save();
		public void open();
		public void close();
	}
	// 【命令接收者】所有操作均实现 Action 接口 ，它代表了一个操作。
	public interface Action{	
		public void perform();
	}
	// 保存操作代理给 Editor 方法
	public class Save implements Action {	
		private final Editor editor;
		public Save(Editor editor) {
			this.editor = editor;
		}
		@Override
		public void perform() {
			editor.save();
		}
	}
	// 打开文件操作代理给 Editor 方法
	public class Open implements Action {	
		private final Editor editor;
		public Open(Editor editor) {
			this.editor = editor;
		}
		@Override
		public void perform() {
			editor.open();
		}
	}
	// 关闭文件操作代理给 Editor 方法
	public class Close implements Action {	
		private final Editor editor;
		public Close(Editor editor) {
			this.editor = editor;
		}
		@Override
		public void perform() {
			editor.close();
		}
	}
	// 【命令发起者】包含操作序列的宏，可按顺序执行操作
	public class Macro{
		private final List<Action> actions;		// 使用 List 保存操作序列
		public Macro() {
			actions = new ArrayList<>();
		}
		public void record(Action action) {
			actions.add(action);
		}
		public void run() {
			actions.forEach(Action::perform);	// 调用 forEach 方法按顺序执行每一个 Action
		}
	}
	/**
	 * 	使用命令者模式构建宏
	 *	@param editor
	 */
	public void useMacro(Editor editor) {
		Macro macro = new Macro();
		macro.record(new Open(editor));
		macro.record(new Save(editor));
		macro.record(new Close(editor));
		macro.run();
	}
	/**
	 * 	使用 Lambda 表达式构建宏
	 *	@param editor
	 */
	public void useMacroByLambda(Editor editor) {
		Macro macro = new Macro();
		macro.record(()->editor.open());
		macro.record(()->editor.save());
		macro.record(()->editor.close());
		macro.run();
	}
	/**
	 * 	使用方法引用构建宏，使用方法引用将命令和宏对象关联起来
	 *	@param editor
	 */
	public void useMacroByMethod(Editor editor) {
		Macro macro = new Macro();
		macro.record(editor::open);
		macro.record(editor::save);
		macro.record(editor::close);
		macro.run();
	}
}
