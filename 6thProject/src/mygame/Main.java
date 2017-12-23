package mygame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;




public class Main implements Runnable,KeyListener{
	
	int height,width;
	String title;
	int count =0;
	
	JFrame frame;
	static Canvas canvas;
	static Graphics g;
	BufferStrategy bs;
	int playerNo =0;
	
	int player_xpos,player_ypos,player_height,player_width;
	public static int ball_xpos,ball_ypos,ball_radius,ball_xspeed,ball_yspeed;
	
	
	static boolean isRunning =true;
	static boolean ballIsMoving =false;
	
	static public Image ballImg =null;
	
	
	/*public void paint(Graphics g)
	{
		g.fillOval(200, 200, 20, 20);
	}*/
	
	static public void InitBall()
	{
		ball_xpos=200;
		ball_ypos=585;
		ball_radius=20;
		ball_xspeed=3;
		ball_yspeed=3;
		
		Main ball =new Main(3);
		
		
		ballImg=ball.getImage("icons\\green_ball.png");
		
		
	}
	
	Main(int pNo)
	{
		
		playerNo = pNo;
		canvas.addKeyListener(this);
		canvas.setFocusable(true);
	}
	Main(int width,int height, String title,int pNo)
	{
		playerNo = pNo;
		
		frame =new JFrame(title);
		frame.setSize(width,height);
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		canvas=new Canvas();
		canvas.setSize(width, height);
		
		frame.add(canvas);
		frame.pack();
		
		
		
		canvas.addKeyListener(this);
		canvas.setFocusable(true);
		
		
		
	}
	
	public synchronized void run()
	{
		 
		 
		 int fps=60;
		 double timePerTick=1000000000/fps;
		 double delta=0;
		 long now;
		 long lastTime=System.nanoTime();
		 while(isRunning)
		 {
			 now =System.nanoTime();
			 delta+=(now-lastTime)/timePerTick;
			 lastTime=now;
			 
			 if(delta>=1)
			 {
				 Tick();
				 Render();
				 delta--;
			 }
			
		 }
		
	}
	private void Render() 
	{
		g=canvas.getGraphics();
		g.clearRect(0, 0, width, height);
		
		g.setColor(Color.CYAN);
		g.fillRect(40, 200, 40, 20);
		
		g.drawImage(ballImg, ball_xpos, ball_ypos, ball_radius, ball_radius, null, null);
		
		g.dispose();
		
	}

	private void Tick() 
	{
		 moveBall();
		
	}
	void moveBall()
	{
		if(ballIsMoving)
		{
			
			ball_xpos=ball_xpos+ball_xspeed;
			ball_ypos=ball_ypos+ball_yspeed;
			
		}
		
		
		if(ball_xpos<=20)
		{
			ball_xspeed=-ball_xspeed;
			count=0;
		
		}
		if(ball_xpos>=960)
		{
			ball_xspeed=-ball_xspeed;
			count=0;
		}
		
		if(ball_ypos<=20)
		{
			ball_yspeed=-ball_yspeed;
			count=0;
		}
		if(ball_ypos>=660)
		{
			ball_yspeed=-ball_yspeed;
			count=0;
		}
	}
	
	



	
	
	


	
	public void keyPressed(KeyEvent e) {
		
		if(this.playerNo ==1)
		{
			if(e.getKeyCode()==KeyEvent.VK_D)
			{
				
			}
			
		}
		if(this.playerNo ==2)
		{
			if(e.getKeyCode()==KeyEvent.VK_LEFT)
			{
				
			}
		}
		
	}

	
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Image getImage(String path)
	{
		Image img =null;
		
		try {
			//img=new ImageIcon("D:\\6thSemProject\\6thSemProject\\src\\abcd.jpg").getImage();
			img = new ImageIcon(getClass().getResource(path)).getImage();
		}
		
		catch(Exception e)
		{
			System.out.println("no image found");
			System.exit(1);
		}

		return img;
	}
	
	public static void main(String args[])
	{
		Main obj =new Main(1000,700,"hello",1);
		Main ob =new Main(2);
		
		
		Thread t =new Thread(obj);
		Thread t2 =new Thread(ob);
		
		t.start();
		t2.start();

	}

}
