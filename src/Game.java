import java.awt.Canvas;
import java.awt.Dimension;


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
			System.out.println("Running...");
			
		}
		
	}

}
