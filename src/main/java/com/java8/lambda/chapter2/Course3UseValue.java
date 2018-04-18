package com.java8.lambda.chapter2;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 *  引用值，而不是变量
 * @author hzweiyongqiang
 *
 */
public class Course3UseValue extends JFrame{

	private static final long serialVersionUID = -6491680405731011511L;

	public Course3UseValue() {
		setLayout(new GridLayout(1,2,5,5));
		
		Container container = getContentPane();
		JButton jb1 = new JButton("button1");
		container.add(jb1);
		JButton jb2 = new JButton("button2");
		container.add(jb2);
		
		// 使用匿名内部类将行为和按钮单击进行关联
		// 匿名内部引用它所在方法里的变量。这时，需要将变量声明为 final 
		// Java 8 虽然放松了这一限制，可以引用非 final 变量，但是该变量在既成事实上必须是 final 。
		// 既成事实上的 final 是指只能给该变量赋值一次。换句话说，Lambda 表达式引用的是值，	而不是变量。
		String name1 = "name1";
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("button1 clichek!" + name1);
			}
		});
		
		// 使用 Lambda 表达式将行为和按钮单击进行关联
		String name2 = "name2";
		jb2.addActionListener(event -> System.out.println("button2 clicked!" + name2));
		
		setTitle("按钮组件");	//设置窗口标题
        setVisible(true);	//设置窗口可视化
        setSize(200,100);	//设置窗口的大小
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	//设置窗口的关闭方式
	}
	
	public static void main(String[] args) {
		new Course3UseValue();
	}
}
