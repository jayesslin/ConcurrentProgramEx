
package view;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import ClientController.GameController;

/*
import Controller.GameController;
import Controller.TimeThread;
import TimeTools.CutDownTime;
*/
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import model.Banana;
import model.Grid;
import model.Monkey;

public class GameGUI extends JFrame {
	
	Socket connectToServer;
	
    
	MyPanel mp1=null;
	Monkey m = Monkey.getInstance();
	Banana b = Banana.getInstance();
	//CutDownTime n =new CutDownTime();
	
	//TimeThread tt = new TimeThread();
	GameController GC = new GameController();
	
	static Grid g = new Grid();
	//Image
	Image image = Toolkit.getDefaultToolkit().getImage("C:/Users/49396/Desktop/monkey.png");
	//Image image = Toolkit.getDefaultToolkit().getImage("imgaes/monkey.png");
	
	//ImageIcon imglogo = new ImageIcon(ClassLoader.getSystemResource("images/monkey.png"));
	//("images/monkey.png") or ("C:/Users/49396/Desktop/monkey.png")
	//ImageObserver observer;
	static int length=g.getN();
	static int width= 10;
	static int heigth=10;
	//set width ! 14:19
	//static int width= 15;
	//static int heigth=15;

	
	//Singleton GameGUI
	private static GameGUI instance=new GameGUI();
	public static GameGUI geyInstance() {
		return instance;
	}
	private  GameGUI() {
		{
			mp1 = new MyPanel();
			this.add(mp1);
			this.setSize(800,600);
			this.setVisible(true);
			this.setIconImage(image);
			this.setTitle("ANGRY MONKEY");
			this.setBackground(Color.black);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.addKeyListener((KeyListener) mp1);
			try {
				connectToServer = new Socket("192.168.3.4",5500);
				System.out.println("链接上 ："+connectToServer.getInetAddress());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//Draw the Scenes!
	class MyPanel extends JPanel implements KeyListener
	{
		public void paint(Graphics g)
		{
			//ini with super class function
			super.paint(g);	
			this.repaint();
			//System.out.println("猴子出现在 ： "+ Monkey.getInstance().getX() +", "+Monkey.getInstance().getY());
			//back groud  in BLACK
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			
			g.setColor(Color.lightGray);
			for(int i=0;i<(length+2)*width;i+=width) {
				//g.drawLine(i, 0, i, 765);
				g.drawLine(i, 0, i, (length+1)*width);
			}
			for(int i=0;i<(length+2)*width;i+=heigth) {
				//g.drawLine(0, i, 765, i);
				g.drawLine(0, i, (length+1)*width, i);
			}
			
			
			//g.drawImage(image, m.getX()*width,m.getY()*width,width,heigth,this);
			
			
			//monkey position	//fillRect(x,y,width,length)  Angry Monkey!
			Random random = new Random();
			int num = random.nextInt(4)+1;
			if(num==1) {
			g.setColor(Color.red);}
			else if (num==2) {
				g.setColor(Color.orange);
			}
			else if(num==3) {
				g.setColor(Color.blue);
			}
			else {
				g.setColor(Color.black);
			}
			g.fillRect(m.getX()*width,m.getY()*width,width,heigth);
			//banana position
			Random random1 = new Random();
			int num1 = random.nextInt(2)+1;
			if(num1==1) {
			g.setColor(Color.orange);}
			else {
			g.setColor(Color.yellow);
			}
			g.fillRect(b.getX()*width,b.getY()*width,width,heigth);
			
			
			
			//Information Board
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman",Font.BOLD,20));
			//g.drawString("Your score is :"+m.getScore(),550 , 150);
			//Couping with Game boundary!
			g.drawString("Your score is :"+m.getScore(),(length+1)*width+50 , 150);
			//CountDown Time
			/*if(n.getCursec()<=10) {
				g.setColor(Color.RED);
				g.setFont(new Font("TimesRoman",Font.BOLD,25));
			}*/
			g.setFont(new Font("TimesRoman",Font.BOLD,20));
			//Couping with Game boundary!
			//g.drawString("Remians: "+n.getCursec()+" s",(length+1)*width+50 , 170);
			
			
			//legend Imformation Monkey
			if(num==1) {
				g.setColor(Color.red);}
				else if (num==2) {
					g.setColor(Color.orange);
				}
				else {
					g.setColor(Color.black);
				}
			//g.setColor(Color.red);
			g.fillRect(540,470,width,heigth);
			g.setColor(Color.white);
			g.drawString("Monkey",560,480);
			
			
			//legend Imformation Banana
			g.setColor(Color.yellow);
			g.fillRect(540,490,width,heigth);
			g.setColor(Color.white);
			g.drawString("Banana",560,500);
			
			//if(n.getCursec()!=0) {
			//this.repaint();
			//}else { 
			//int Scoretmp =m.getScore();
			//n.setCursec(tt.GameTime);
			//GC.Changestate("end");

			//someThing BUGs!!!!!!
			//JOptionPane.showMessageDialog(GameGUI.geyInstance(), "Your score is :"+Scoretmp+" !", "Game Over!",JOptionPane.WARNING_MESSAGE); 
			//Update the new GameInterface
			
			
			/*GC.Changestate("start");
			this.update(g);
			this.repaint();*/


			
			}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			String msg = null;
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					msg="down";
					GC.MoveMonkey(connectToServer, msg);
					this.repaint();
					break;
				case KeyEvent.VK_DOWN:
					msg="up";
					GC.MoveMonkey(connectToServer, msg);
					this.repaint();
					break;
				case KeyEvent.VK_LEFT:
					msg="left";
					GC.MoveMonkey(connectToServer, msg);
					this.repaint();
					break;
				case KeyEvent.VK_RIGHT:
					msg="right";
					GC.MoveMonkey(connectToServer, msg);
					this.repaint();
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		}
		/*public void keyPressed(KeyEvent e) {
			
			String msg = null;
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					msg="down";
					GC.MoveMonkey(connectToServer, msg);
					this.repaint();
					break;
				case KeyEvent.VK_DOWN:
					msg="up";
					GC.MoveMonkey(connectToServer, msg);
					this.repaint();
					break;
				case KeyEvent.VK_LEFT:
					msg="left";
					GC.MoveMonkey(connectToServer, msg);
					this.repaint();
					break;
				case KeyEvent.VK_RIGHT:
					msg="right";
					GC.MoveMonkey(connectToServer, msg);
					this.repaint();
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
			}
}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}*/
	
	
}
