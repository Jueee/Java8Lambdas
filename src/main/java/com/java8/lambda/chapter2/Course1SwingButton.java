package com.java8.lambda.chapter2;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Course1SwingButton extends JFrame{

	private static final long serialVersionUID = -6491680405731011511L;

	public Course1SwingButton() {
		setLayout(new GridLayout(1,2,5,5));
		
		Container container = getContentPane();
		JButton jb1 = new JButton("button1");
		container.add(jb1);
		JButton jb2 = new JButton("button2");
		container.add(jb2);
		
		// 使用匿名内部类将行为和按钮单击进行关联
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("button1 clichek!");
			}
		});
		
		// 使用 Lambda 表达式将行为和按钮单击进行关联
		jb2.addActionListener(event -> System.out.println("button2 clicked!"));
		
		setTitle("按钮组件");	//设置窗口标题
        setVisible(true);	//设置窗口可视化
        setSize(200,100);	//设置窗口的大小
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	//设置窗口的关闭方式
	}
	
	public static void main(String[] args) {
		new Course1SwingButton();
	}
}
