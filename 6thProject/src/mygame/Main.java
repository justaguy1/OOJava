package mygame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


import javax.swing.ImageIcon;
import javax.swing.JFrame;






public class Main   implements Runnable,KeyListener{
	
	int height,width;
	String title;
	 static int count =0;
	
	JFrame frame;
	static Canvas canvas;
	 Graphics2D g;
	 BufferStrategy bs;
	int playerNo =0;
	
	int player_xpos,player_ypos,player_height,player_width;
	
	static boolean _left=false,_right=false,left=false,right=false;
	
	//related to thread
	static boolean isRunning =true;
	
	//related to ball
	public static int ball_xpos,ball_ypos,ball_radius,ball_xspeed,ball_yspeed;
	public static Image ballImg =null;
	static boolean ballIsMoving =false;
	
	Image playerImage=null;
	
	static int clearRectCount =0;
	
	
	
	
	
	
	

	
	 public void InitBall()
	{
		ball_xpos=200;
		ball_ypos=585;
		ball_radius=20;
		ball_xspeed=3;
		ball_yspeed=3;
		
		
		
		
		this.ballImg=this.getImage("icons\\green_ball.png");
		
	
		
		
	}
	
	void InitPlayer(int x,int y,int height, int width)
	{
		player_xpos=x;
		player_ypos=y;
		player_height=height;
		player_width=width;
		
		this.playerImage=this.getImage("icons\\player.png");
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
	private synchronized void Render() 
	{
		
        this.bs=canvas.getBufferStrategy();
		
		if(bs==null)
		{
			canvas.createBufferStrategy(2);
			return;
		}
		
		
	  g=(Graphics2D) bs.getDrawGraphics();
	 
		  

       
        
	   
       
       
       if(count >3)
       {
    	   count=0;
       }
       else
       {
    	   count++;
       }
       
       if(count==0)
       {
    	   g.clearRect(0, 0, 1000, 700);
       }
       System.out.println(count);
		
       g.drawImage(playerImage, player_xpos,player_ypos,player_width,player_height,null);
		g.drawImage(ballImg, ball_xpos, ball_ypos, ball_radius, ball_radius,null);
        
       
        	
       
        	
        
        
        
        
       
            
        
        
        
        this.bs.show();
		this.g.dispose();
  
        
		
		
	}

	private void Tick() 
	{
		if(playerNo==3) 
		 moveBall();
		checkPlayerInput();
		
	}
	private void checkPlayerInput() {
		if(this.playerNo ==1)
		{
			if(right==true)
			{
				if(player_xpos<=860)
				{
					player_xpos+=10;
				}
			}
				
			if(left==true)
			{
	
				if(player_xpos>=20)
				{
					player_xpos-=10;
				}
			}
		}
		
		if(this.playerNo ==2)
		{
				
			if(_right==true)
			{
	
				if(player_xpos<=860)
				{
					player_xpos+=10;
				}
			}
				
			if(_left==true)
			{
				
				if(player_xpos>=20)
				{
					player_xpos-=10;
				}
			}
		}
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
		
		
			if(e.getKeyCode()==KeyEvent.VK_D)
				right=true;
			
			if(e.getKeyCode()==KeyEvent.VK_A)
				left =true;

		
			if(e.getKeyCode()==KeyEvent.VK_RIGHT)
				_right=true;
			
			if(e.getKeyCode()==KeyEvent.VK_LEFT)
				_left=true;
		
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if(!ballIsMoving)
			{
				ballIsMoving=true;
			}
			
		}
	}
		
	

	
	public void keyReleased(KeyEvent e) {

		if(this.playerNo ==1)
		{
			if(e.getKeyCode()==KeyEvent.VK_D)
				right=false;
			
			if(e.getKeyCode()==KeyEvent.VK_A)				
				left =false;
			
			
		}
		if(this.playerNo ==2)
		{
			if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            _right=false;

         	if(e.getKeyCode()==KeyEvent.VK_LEFT)
			_left=false;

		}
		
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
		Main player_01 =new Main(1000,700,"hello",1);
		Main player_02 =new Main(2);
		Main ball =new Main(3);
		
		player_01.InitPlayer(60, 100, 40, 80);
		player_02.InitPlayer(60, 600, 40, 80);
		
		ball.InitBall();
		
		
		Thread t =new Thread(player_01);
		Thread t2 =new Thread(player_02);
		Thread t3 =new Thread(ball);
		
		
		t.start();
		t2.start();
		t3.start();
		
		

	}

}
