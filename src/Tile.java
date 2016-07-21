
public class Tile {
	public char value;
	public Tile left;
	public Tile right;
	public Tile up;
	public Tile down;
	public int x; //referring to the original file
	public int y;
	
	public Tile() {
		this.value = '\u0000'; //null
		left = null;
		right = null;
		up = null;
		down = null;
	}
	
	public Tile(char v, int x, int y) {
		this.value = v; //null
		left = null;
		right = null;
		up = null;
		down = null;
	}
}
