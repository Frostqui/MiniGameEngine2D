package frostqui.github.io;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import frostqui.github.io.gfx.Screen;

public class Game extends Canvas implements Runnable{

	
	private static final long serialVersionUID = 1L;
	public static final int width = 320;
	public static final int height = width / 16 * 9;
	public static final int scale = 3;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;	
	private Random random;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); //Pixels of the screen
	
	private Screen screen;
	
	int x,y = 0;
	
	
	public Game(){
		Dimension dimension = new Dimension(width * scale,height * scale);
		setPreferredSize(dimension);
		frame = new JFrame();
		screen = new Screen(width,height);
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
		long lastTime = System.nanoTime(); //nanoTime is better than currentTimeint timer = 0;
		long timer = System.currentTimeMillis();
		final double nanoSecs = 1000000000.0 / 60.0;
		double delta = 0;
		int fps = 0;
		int ticks = 0;
		
		
		
		while(running ){
			long now = System.nanoTime(); //Not the same time than lastTime
			delta += (now - lastTime) / nanoSecs; 
			lastTime  = now;
			
			
			while (delta >= 1){
				tick();
				ticks++;
				delta--;
			}
			
			render();
			fps++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(ticks+" ticks,  "+fps+" fps");
				ticks = 0;
				fps = 0;
			}
			
		}
		stop();
		
	}

	/**
	 * Game logic. This method is running in a specific time of time, cause if we update and render at the same speed, and you have a better computer, for example, the sprites will move faster. 
	 */
	
	private void tick() {
		
		x++;
		
		
		
	}
	
	
	/**
	 * The computer reders pixel by pixel but this may cause graphical issues, so we need calculate first the pixels and store in a temporary storage. When we have calculated the pixels, we can replace 
	 * the frame with the new frame calculated. We use a specific area of the RAM using DataBuffer
	 */
	
	private void render() {

		
		System.setProperty("sun.java2d.opengl", "true");
		
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null){
			
			/*
			 * If we got the screen and the storage where the pixels are calculated, we need to wait if we want to calculate another until the first one is calculated. 
			 * But with the screen and two storage we can process two at the same time
			 */
			
			createBufferStrategy(3);
			
			
			return;
			
			
			
		}
		
		screen.clear(); //Clear all the pixels of the screen
		screen.render(x,y); //Render the pixels of the screen
		
		for(int i=0; i<pixels.length; i++){
			pixels[i] = screen.getPixels()[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
	
		
		
	}

}
