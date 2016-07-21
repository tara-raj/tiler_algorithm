import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Piece implements Cloneable {
	Tile root;
	int size;
	int width;
	int height;
	int x;
	int y;
	public boolean fixed = false;
	public boolean placed = false;
	//TODO: implement height and width
	ArrayList<Tile> tileList = new ArrayList<Tile>();
	char[][] tiles = {null};
	
	//methods to add:
	//	reflect
	//	rotate
	//	build
	
    public Object clone() {
        Piece p = null;
        try {
            p = (Piece)super.clone();
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
        }
        p.tiles = this.tiles.clone();
        p.x = this.x;
        p.y = this.y;
        return p;
    }
	
	public Piece() {
		
	}
	
	public Piece(char[][] arr, int x, int y) {
		
		this.root = new Tile(arr[y][x], x, y);
		this.root.x = x;
		this.root.y = y;
		tileList.add(this.root);
		size++;
		
		build(arr, root);
		
		makeTileMatrix();
		
		width = tiles[0].length;
		height = tiles.length;
		
	}
	
	public void makeTileMatrix() {
		int minX = 100000;
		int minY = 100000;
		
		int maxX = 0;
		int maxY = 0;
		
		for (Tile t : tileList) {
			if (minX > t.x) minX = t.x;
			if (minY > t.y) minY = t.y;
			
			if (maxX < t.x) maxX = t.x;
			if (maxY < t.y) maxY = t.y;
		}
		
		for (Tile t : tileList) {
			t.x -= minX;
			t.y -= minY;
		}
		
		maxX -= minX;
		maxY -= minY;
		
		this.tiles = new char[maxY+1][maxX+1];
		
		//initialize
		for (char[] row : this.tiles) {
			Arrays.fill(row, ' ');
		}
		
		//places all the tiles in the matrix
		for (Tile t : tileList) {
			tiles[t.y][t.x] = t.value;
		}
	}
	
	public void build(char[][] arr, Tile t) {
		int x = t.x;
		int y = t.y;
		arr[y][x] = ' ';
		
		//left
		//if the spot to the left of x is in bounds
		//if y is between 0 and arr.length
		//if the spot to the left of x isn't null
		if (x-1 >= 0 && y >= 0 && y < arr.length && arr[y][x-1] != ' ' && arr[y][x-1] != '\u0000') {
			Tile temp = new Tile();
			temp.x = x-1;
			temp.y = y;
			temp.value = arr[y][x-1];
			t.left = temp;
			this.size ++;
			tileList.add(temp);
//			System.out.println("added new tile to the left");
			build(arr,temp);
		}
		
		//right
		//if the spot to the right is less than the length of that row
		//if the y value is within its bounds
		//if the spot to the right of x isn't null
		if (x+1 < arr[y].length && y >= 0 && y < arr.length && arr[y][x+1] != ' ' && arr[y][x+1] != '\u0000') {
			Tile temp = new Tile();
			temp.x = x+1;
			temp.y = y;
			temp.value = arr[y][x+1];
			t.right = temp;
			this.size ++;
			tileList.add(temp);
//			System.out.println("added new tile to the right");

			build(arr,temp);
		}
		
		//top
		//if x is within bounds
		//if the spot above is within bounds
		//if the spot above isn't null
		if (x >= 0 && x < arr[y].length && y-1 >= 0 && arr[y-1][x] != ' ' && arr[y-1][x] != '\u0000') {
			Tile temp = new Tile();
			temp.x = x;
			temp.y = y-1;
			temp.value = arr[y-1][x];
			t.up = temp;
			this.size ++;
			tileList.add(temp);
//			System.out.println("added new tile to the top");

			build(arr,temp);
		}
		
		//bottom
		//if x is within bounds
		//if the spot below is within bounds
		//if the spot below is not null
		if ( y+1 < arr.length && x >= 0 && x < arr[y+1].length && arr[y+1][x] != ' ' && arr[y+1][x] != '\u0000') {
			Tile temp = new Tile();
			temp.x = x;
			temp.y = y+1;
			temp.value = arr[y+1][x];
			t.down = temp;
			this.size ++;
			tileList.add(temp);
//			System.out.println("added new tile to the bottom");

			build(arr,temp);
		}
	}
	
	public String toString() {
		String s = "\n";
		for (int y = 0 ; y < this.height ; y++) {
			for (int x = 0 ; x < this.width ; x++) {
				s += this.tiles[y][x];
				
			}
			s += "\n";
		}
		
		
		return s;
	}
}