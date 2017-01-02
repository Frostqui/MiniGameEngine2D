import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	
	private static final long serialVersionUID = 1L;
	public static final int width = 320;
	public static final int height = width / 16 * 9;
	public static final int scale = 3;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;	
	
	
	
	public Game(){
		Dimension dimension = new Dimension(width * scale,height * scale);
		setPreferredSize(dimension);
		frame = new JFrame();
	}
	
	public static void main(String[] args) {
	
		Game game = new Game();
		game.frame.setTitle("Untitled game");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
		
			

	}
	
	/**
	 * Starts the game loop
	 */
	public void start(){
		running  = true;
		thread = new Thread(this, "Display");
		thread.start();
		
		
	}
	
	/**
	 * Stops the game loop
	 */
	
	public void stop(){
		running = false;
		try{
			thread.join();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Game loop
	 */
	
	@Override
	public void run() {
		while(running ){
			update();
			render();
			
		}
		
	}

	/**
	 * Game logic. This method is running in a specific time of time, cause if we update and render at the same speed, and you have a better computer, for example, the sprites will move faster. 
	 */
	
	private void update() {
		
		
		
		
		
	}
	
	
	/**
	 * The computer reders pixel by pixel but this may cause graphical issues, so we need calculate first the pixels and store in a temporary storage. When we have calculated the pixels, we can replace 
	 * the frame with the new frame calculated. We use a specific area of the RAM using DataBuffer
	 */
	
	private void render() {

		
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null){
			
			/*
			 * If we got the screen and the storage where the pixels are calculated, we need to wait if we want to calculate another until the first one is calculated. 
			 * But with the screen and two storage we can process two at the same time
			 */
			
			createBufferStrategy(3);
			return;
			
		}
	
		
		
	}

}
