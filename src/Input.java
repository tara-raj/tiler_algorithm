import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Input {
	
	
	//reads in file into a 2d array
	//takes in a board, and a text file
	Board board;
	
	public Input() throws IOException {
		
	}
	
	public Input(Board board, String filename) {
		try {
			processInput(board, filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void processInput(Board b, String filename) throws IOException {
		
		//PHASE ONE: LOAD FILE INTO 2D ARRAY
		
		Scanner lengthChecker = new Scanner(new File(filename));
		
		int x = 0;
		int y = 0;
		
		while (lengthChecker.hasNextLine()) {
			int temp = lengthChecker.nextLine().length();
			if (temp > x) x = temp;
			y++;
		}
		
		Scanner inputFile = new Scanner(new File(filename));
		
		//TODO: get number of rows
		//TODO: get maximum line length
		
		char[][] arr = new char[y][x];
		
		for (int row = 0; row < y;row++) {
			String line = inputFile.nextLine();
			
			for (int col = 0; col < line.length() ; col ++ ) {
				arr[row][col] = line.charAt(col);
			}
		}
		
		//END PHASE ONE
		
		
		
		
		//PHASE TWO: SCAN FOR PIECES
		
		//scans from top to bottom, then from left to right
		for (int col = 0 ; col < x ; col ++) {
			for (int row = 0 ; row < y ; row ++) {
				
				//if it finds a piece with a value
				if (arr[row][col] != ' ' && arr[row][col] != '\u0000') {
//					System.out.println("found a tile! at col:" + col + ", row:" + row + "\nbuilding a piece now");
					Piece p = new Piece(arr, col, row);
					Tile t = new Tile();
					t.value = arr[row][col];
					t.x = col;
					t.y = row;
					p.build(arr, t);
//					System.out.println("");

					b.pieces.add(p);
//					System.out.println("piece added!");
				}
			}
		}
		lengthChecker.close();
		inputFile.close();
	}
	
	public Board getBoard() {
		return this.board;
	}
}
