package frostqui.github.io.gfx;

import java.awt.List;
import java.util.Random;

import world.tile.Tile;

public class Screen {

	private int width, height;
	private int[] pixels;
	
	private int[] tiles = new int[64*64];
	
	//private List<Sprite> sprites = new List<Sprite>();
	
	
	private Random random = new Random();

	
	public Screen(int width, int height){
		
		this.width = width;
		this.height = height;
		this.pixels = new int[width*height];
		
		for (int i = 0; i<64*64; i++){
			tiles[i] = random.nextInt(0xffffff);
		}
		
	}

	
	
	public void render(int xOffset, int yOffset){
		
		for (int j=0; j<height; j++){
			int yy = j + yOffset;
			
			//if(yy < 0 || yy >= height)break;
			
				for (int i=0; i<width; i++){
					int xx = i + xOffset;
			
				//	if(xx < 0 || xx >= width)break;
					
						
						int tileIndex = ((xx >> 4) & 7) + ((yy >> 4) & 7) * 8;
						//pixels[i + j * width] = random.nextInt(0xffffff);
						
						pixels[i + j * width] = Sprite.grass.pixels[(xx & 15) + (j & 15) * Sprite.size];
						
					
				}
			
		}
	}

	
	public void renderTile(int xp, int yp, Tile tile){
		for (int y = 0; y<tile.sprite.size; y++){
			int ya = y+yp;
			for (int x = 0; x<tile.sprite.size; x++){
				int xa = x + xp;
				if(xa<0 || ya < 0 || xa >= width || ya >= height)break;
					pixels[xa + ya * width] = tile.sprite.pixels[x+y*tile.sprite.size];
			}
		}
		
	}
	
	public void clear(){
		for(int i=0; i<pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	
	// Getters & Setters
	
	public int[] getPixels() {
		
		return pixels;
	}
}
