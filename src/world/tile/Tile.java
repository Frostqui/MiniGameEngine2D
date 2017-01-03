package world.tile;

import frostqui.github.io.gfx.Screen;
import frostqui.github.io.gfx.Sprite;

public class Tile {

	
	public int x,y;
	public Sprite sprite;
	
	
	public Tile(Sprite sprite){
	
		this.sprite = sprite;
		
		
		
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x,y,this);
	}
	
	
	
	
}
