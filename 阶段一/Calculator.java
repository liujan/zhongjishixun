import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Calculator
{
	public static void main(String[] args)
	{
		CalMenu cm = new CalMenu(); //生成一个计算器对象
		cm.launch();  //启动计算器
	}
}

class CalMenu
{
	Frame f;
	JLabel tf;  //用于显示输入和计算结果
	
	public CalMenu()
	{
				
	}
	public void launch()
	{
		f = new Frame("Easy Calculator");
		f.setLayout(new GridLayout(3,1));
		
		Panel all = new Panel(); //用于存放数字和运算符
		all.setLayout(new GridLayout(1,2));
		Panel menu = new Panel(); //用于存放运算符
		menu.setLayout(new GridLayout(4,4));
		Panel num = new Panel();  // 用于存放数字
		num.setLayout(new GridLayout(4,3));
		  
		Listener l = new Listener();  //监听器
		  
		
		for(int i = 1; i < 10; i++) //生成九个数字的按钮
		{
			JButton b = new JButton(Integer.toString(i));
			b.setActionCommand("number");
			b.addActionListener(l);
			num.add(b);
		}
		JButton bb = new JButton("0");
	  	bb.setActionCommand("number");
	  	bb.addActionListener(l);
	  	num.add(bb);
		tf = new JLabel("0");
		tf.setText("0"); //初始化结果为0
		  
		  //生成运算符
	    JButton add = new JButton("+");
	    JButton sub = new JButton("-");
	    JButton mul = new JButton("*");
	    JButton div = new JButton("/");
		JButton equ = new JButton("=");
		JButton pt = new JButton(".");
		pt.setActionCommand("number");
		JButton clear = new JButton("clear");
		  
		  //添加监听器
		add.addActionListener(l);
		sub.addActionListener(l);
		mul.addActionListener(l);
		div.addActionListener(l);
		equ.addActionListener(l);
		pt.addActionListener(l);
		clear.addActionListener(l);
		 
		  //将运算符添加到面板上
		menu.add(add);
		menu.add(sub);
		menu.add(mul);
		menu.add(div);
		menu.add(pt);
		
		num.add(pt);
		num.add(equ);
		  
		all.add(num);
		all.add(menu);
		
		f.addWindowListener(l);
		f.add(tf);
		f.add(all);
		f.add(clear);
		f.pack();
		f.setVisible(true);
			
	}
		
	//监听器
	class Listener extends WindowAdapter implements ActionListener
	{

		private double firstvalue = Double.MIN_VALUE; //第一个输入数
		private int action = 0;  //表示运算符
		double r = 0; //保存结果
		String tmp = "";
		private double secondvalue;   //第二个输入数
		//监听事件
		public void actionPerformed(ActionEvent e)
		{
			    String command = e.getActionCommand();
				JButton b = (JButton)e.getSource();
				String lb = b.getLabel();
			    
				
				if ("number" == command) //如果按下的是数字按钮
				{
					if (0 == action)  //如果还没按下运算符（即表示此时是第一个输入数）
					{
						tmp += lb;
						firstvalue = Double.parseDouble(tmp);
						r = firstvalue;
					}
					else   //第二个输入数
					{
						
						tmp += lb;					
						secondvalue = Double.parseDouble(tmp);						
					}
					tf.setText(tmp);
				}
				else   //按下的是运算符
				{						
					if (firstvalue == Double.MIN_VALUE)  //如果还没按下过数字按钮
					{
						if ("+" == lb)  //表示输入正数
						{
							tmp = "";
						}
						else if ("-" == lb)  //输入负数
						{
							tmp = "-";						
						}
					}
					else //用aciton来表示四种运算
					{
						if (action != 0 && lb != "=")
						{
							Action();
							showResult(r);
							firstvalue = r;
						}
						if ("+" == lb)   //1表示加法
						{
							action = 1;
						}
						else if ("-" == lb)  //2表示减法
						{
							action = 2;
						}
						else if ("*" == lb) //3表示乘法
						{
							action = 3;
						}
						else if ("/" == lb) //4表示除法
						{
							action = 4;
						}
						else if ("=" == lb)  //输出运算结果
						{		
							Action();
							secondvalue = 0;
							tmp = "";
							firstvalue = r;
							showResult(r);  //显示运算结果
							action = 0;
						}
						tmp = "";
					}
					if ("clear" == lb)   //清零
					{
						Clear();
					}
					
				}
		}
		
		private void Action()
		{
			switch(action)
			{
				case 1:  //加法
				r = firstvalue + secondvalue;
				break;
				case 2: //减法
				r = firstvalue - secondvalue;
				break;
				case 3:  //乘法
				r = firstvalue * secondvalue;
				break;
				case 4: //除法
				r = firstvalue / secondvalue;
				break;
			}
		}
		
		//关闭窗口事件
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
		
		//显示结果
		private void showResult(double result)
		{
			String s = Double.toString(result);
			tf.setText(s);
		}
		
		//清零
		private void Clear()
		{
			tf.setText("0");
			firstvalue = Double.MIN_VALUE;
			secondvalue = 0;
			tmp = "";
			action = 0;
		}
	}
}
