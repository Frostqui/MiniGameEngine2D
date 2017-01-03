package frostqui.github.io.graphics;

public class Screen {

	private int width, height;
	private int[] pixels;
	
	public Screen(int width, int height){
		
		this.width = width;
		this.height = height;
		this.pixels = new int[width*height];
		
	}

	public void render(){
		for (int j=0; j<height; j++){
			for (int i=0; i<width; i++){
				pixels[i + j * width] = 0xff00ff;
			}
		}
	}

	public int[] getPixels() {
		
		return pixels;
	}
}
