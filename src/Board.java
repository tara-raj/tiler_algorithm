import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Board {


	char[][] 	current; 			// temporary board we are placing on
	char[][] 	goal; 				// goal board we want to get to

	int	 		width; 				// width of the board
	int 		height; 			// height of the board
	
	int			currY = 0;
	int			currX = 0;

	boolean 	allowRotations 		= true; 	// boolean flag for allowing rotations
	boolean 	allowReflections 	= true; 	// boolean flag for allowing reflections

	ArrayList<Piece> 	pieces 		= new ArrayList<Piece>();
	ArrayList<Piece> 	usedPieces 	= new ArrayList<Piece>();
	
	ArrayList<ArrayList<Piece>>		solutions		= new ArrayList<ArrayList<Piece>>();
	
	long startTime;

	public Board(String filename) {
		startTime = System.currentTimeMillis();
		try {
			Input.processInput(this, filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.makeMatrix(pieces);

		Collections.sort(pieces, new PieceComparator());
		
		
		System.out.println("Board made!");
	}

	// Initialize the current and goal boards
	public void makeMatrix(ArrayList<Piece> pieces) {
		int largest = 0;
		Piece largestPiece = null;
		for (Piece p : pieces) {
			if (p.size > largest) {
				largestPiece = p;
				goal = p.tiles;
				largest = p.size;
			}
		}
		pieces.remove(largestPiece);
		this.width = goal[0].length;
		this.height = goal.length;

		// Initialize all the indices of the current board to be a space
		
		//y, x
		current = new char[this.height][this.width];
		
		//initialize every value to space
		for (char[] row : current) {
			Arrays.fill(row, ' ');
		}
	}

	// If the entire piece doesn't fit, go and delete the progress
	public void deletePiece(Piece p, int x, int y) {

	}

	// Rotate the piece 90 degrees
	public void RotatePiece(Piece p) {
		if (allowRotations == true) {
			int newHeight = p.width;
			int newWidth = p.height;
			char[][] output = new char[newHeight][newWidth];

			for (int j = 0; j < newHeight; j++)
				for (int i = 0; i < newWidth; i++)
					output[j][newWidth - 1 - i] = p.tiles[i][j];

			p.width = newWidth;
			p.height = newHeight;
			p.tiles = output;
		}
		// System.out.println("rotated");
	}

	// Reflect the piece over its x-axis
	public void ReflectPieceX(Piece p) {
		if (allowReflections == true) {
			char[][] output = new char[p.height][p.width];

			for (int j = 0 ; j < p.height ; j++) {
				for (int i = 0 ; i < p.width ; i++) {
					output[j][i] = p.tiles[p.height-1-j][i];}}
			p.tiles = output;}
	}

	// Reflect the piece over its y-axis
	public void ReflectPieceY(Piece p) {
		if (allowReflections == true) {
			char[][] output = new char[p.height][p.width];

			for (int j = 0 ; j < p.height ; j++) {
				for (int i = 0 ; i < p.width ; i++) {
					output[j][i] = p.tiles[j][p.width-1-i];}}
			p.tiles = output;}
	}
	
	
	public boolean doesItFit(Piece p, int y, int x) {
		
		//IF ROTATIONS ARE ENABLED
		if (this.allowRotations == true) {
		
			boolean fits = true;
		
			for (int rotate = 0 ; rotate < 4 ; rotate ++) {
			
				fits = true;
			
				//NO REFLECTIONS
				for (int j = 0; j < p.height; j++) {
					for (int i = 0; i < p.width ; i++) {
						int newY = y + j; int newX = x + i;
						if (newX < 0 || newX >= this.width ) {fits = false;break;}
						if (newY < 0 || newY >= this.height) {fits = false;break;}
						if (p.tiles[j][i] != this.goal[newY][newX] || !(this.current[newY][newX] == ' ' || this.current[newY][newX] == '\u0000')) {
							if (!(p.tiles[j][i] ==  ' ' || p.tiles[j][i] == '\u0000')) {fits = false;break;}}}
					if (fits == false) break;}
				if (fits) {return true;}
				
				if (this.allowReflections == true) {
					//REFLECTED ON X AXIS ONLY
					
					fits = true;
					
					this.ReflectPieceX(p);
					for (int j = 0; j < p.height; j++) {
						for (int i = 0; i < p.width ; i++) {
							int newY = y + j; int newX = x + i;
							if (newX < 0 || newX >= this.width ) {fits = false;break;}
							if (newY < 0 || newY >= this.height) {fits = false;break;}
							if (p.tiles[j][i] != this.goal[newY][newX] || !(this.current[newY][newX] == ' ' || this.current[newY][newX] == '\u0000')) {
								if (!(p.tiles[j][i] ==  ' ' || p.tiles[j][i] == '\u0000')) {fits = false;break;}}}
						if (fits == false) break;}
					if (fits) {return true;}
					
					fits = true;
					
					//REFLECTED ON X AND Y AXES
					this.ReflectPieceY(p);
					for (int j = 0; j < p.height; j++) {
						for (int i = 0; i < p.width ; i++) {
							int newY = y + j; int newX = x + i;
							if (newX < 0 || newX >= this.width ) {fits = false;break;}
							if (newY < 0 || newY >= this.height) {fits = false;break;}
							if (p.tiles[j][i] != this.goal[newY][newX] || !(this.current[newY][newX] == ' ' || this.current[newY][newX] == '\u0000')) {
								if (!(p.tiles[j][i] ==  ' ' || p.tiles[j][i] == '\u0000')) {fits = false;break;}}}
						if (fits == false) break;}
					if (fits) {return true;}
					
					fits = true;
					
					//REFLECTED ON Y AXIS ONLY
					this.ReflectPieceX(p);
					for (int j = 0; j < p.height; j++) {
						for (int i = 0; i < p.width ; i++) {
							int newY = y + j; int newX = x + i;
							if (newX < 0 || newX >= this.width ) {fits = false;break;}
							if (newY < 0 || newY >= this.height) {fits = false;break;}
							if (p.tiles[j][i] != this.goal[newY][newX] || !(this.current[newY][newX] == ' ' || this.current[newY][newX] == '\u0000')) {
								if (!(p.tiles[j][i] ==  ' ' || p.tiles[j][i] == '\u0000')) {fits = false;break;}}}
						if (fits == false) break;}
					if (fits) {return true;}
					
					
					//RETURNS TO DEFAULT
					this.ReflectPieceY(p);
				}
			
				System.out.println("Rotated piece from:"+ p);
				this.RotatePiece(p);
				System.out.println("to: " + p);
			}
			return false;
		}
		
		//IF ROTATIONS ARE DISABLED
		else {
			for (int j = 0; j < p.height; j++) {
				for (int i = 0; i < p.width ; i++) {
					int newY = y + j;
					int newX = x + i;
				
					if (newX < 0 || newX >= this.width ) return false;
					if (newY < 0 || newY >= this.height) return false;
			
					if (p.tiles[j][i] != this.goal[newY][newX] || !(this.current[newY][newX] == ' ' || this.current[newY][newX] == '\u0000')) {
						if (!(p.tiles[j][i] ==  ' ' || p.tiles[j][i] == '\u0000')) {
							return false;
						}
					}
				}
			}
			
			return true;
		}
	}
	//recursive method
	//BASE CASE: all pieces have been placed
	//recursive cases
		//move cursor forward until you find an open tile
		//check to see if each piece fits there in each rotation + reflection combination
		//if a piece fits, pass it into the next recursive call for that location
		//if it doesn't fit, let the method run out, which will remove the last played piece
		//and continue the previous level's iterating through the pieces
	public void placePieces()  {
		boolean placed = false;
		for (Piece p : pieces) {
			placed = false;
			if (!this.usedPieces.contains(p)){
				

				//IF ROTATIONS ARE ENABLED
				if (this.allowRotations == true) {
				
					boolean fits = true;
				
					for (int rotate = 0 ; rotate < 4 ; rotate ++) {
					
						fits = true;
					
						//NO REFLECTIONS
						for (int j = 0; j < p.height; j++) {
							for (int i = 0; i < p.width ; i++) {
								int newY = currY + j; int newX = currX + i;
								if (newX < 0 || newX >= this.width ) {fits = false;break;}
								if (newY < 0 || newY >= this.height) {fits = false;break;}
								if (p.tiles[j][i] != this.goal[newY][newX] || !(this.current[newY][newX] == ' ' || this.current[newY][newX] == '\u0000')) {
									if (!(p.tiles[j][i] ==  ' ' || p.tiles[j][i] == '\u0000')) {fits = false;break;}}}
							if (fits == false) break;}
						if (fits) {
							place(p,currY,currX);placePieces();placed=true;checkSolution();}
																								
						if (this.allowReflections == true) {
							//REFLECTED ON X AXIS ONLY
							
							fits = true;
							
							if (!p.placed) {
							this.ReflectPieceX(p);
							for (int j = 0; j < p.height; j++) {
								for (int i = 0; i < p.width ; i++) {
									int newY = currY + j; int newX = currX + i;
									if (newX < 0 || newX >= this.width ) {fits = false;break;}
									if (newY < 0 || newY >= this.height) {fits = false;break;}
									if (p.tiles[j][i] != this.goal[newY][newX] || !(this.current[newY][newX] == ' ' || this.current[newY][newX] == '\u0000')) {
										if (!(p.tiles[j][i] ==  ' ' || p.tiles[j][i] == '\u0000')) {fits = false;break;}}}
								if (fits == false) break;}
							if (fits) {
								place(p,currY,currX);placePieces();placed=true;checkSolution();}
							}
																												
							fits = true;
							
							//REFLECTED ON X AND Y AXES
							if (!p.placed) {
							this.ReflectPieceY(p);
							for (int j = 0; j < p.height; j++) {
								for (int i = 0; i < p.width ; i++) {
									int newY = currY + j; int newX = currX + i;
									if (newX < 0 || newX >= this.width ) {fits = false;break;}
									if (newY < 0 || newY >= this.height) {fits = false;break;}
									if (p.tiles[j][i] != this.goal[newY][newX] || !(this.current[newY][newX] == ' ' || this.current[newY][newX] == '\u0000')) {
										if (!(p.tiles[j][i] ==  ' ' || p.tiles[j][i] == '\u0000')) {fits = false;break;}}}
								if (fits == false) break;}
							if (fits) {
								place(p,currY,currX);placePieces();placed=true;checkSolution();}
							}
																												
							fits = true;
							
							//REFLECTED ON Y AXIS ONLY
							if (!p.placed) {
							this.ReflectPieceX(p);
							for (int j = 0; j < p.height; j++) {
								for (int i = 0; i < p.width ; i++) {
									int newY = currY + j; int newX = currX + i;
									if (newX < 0 || newX >= this.width ) {fits = false;break;}
									if (newY < 0 || newY >= this.height) {fits = false;break;}
									if (p.tiles[j][i] != this.goal[newY][newX] || !(this.current[newY][newX] == ' ' || this.current[newY][newX] == '\u0000')) {
										if (!(p.tiles[j][i] ==  ' ' || p.tiles[j][i] == '\u0000')) {fits = false;break;}}}
								if (fits == false) break;}
							if (fits) {
								place(p,currY,currX);placePieces();placed=true;checkSolution();}
							}
																												
							
							//RETURNS TO DEFAULT
							if (!p.placed) this.ReflectPieceY(p);
						}
					
//						System.out.println("Rotated piece from:"+ p);
//						System.out.println("to: " + p);
						if (!p.placed) this.RotatePiece(p);
					}
				}
				
				//IF ROTATIONS ARE DISABLED
				else {
					
					boolean fits = true;
					
					for (int j = 0; j < p.height; j++) {
						for (int i = 0; i < p.width ; i++) {
							int newY = currY + j;
							int newX = currX + i;
						
							if (newX < 0 || newX >= this.width ) {fits = false; break;}
							if (newY < 0 || newY >= this.height) {fits = false; break;}
					
							if (p.tiles[j][i] != this.goal[newY][newX] || !(this.current[newY][newX] == ' ' || this.current[newY][newX] == '\u0000')) {
								if (!(p.tiles[j][i] ==  ' ' || p.tiles[j][i] == '\u0000')) {fits = false; break;}
							}
						} if (fits == false) break;
					} if (fits) {
						place(p,currY,currX);placePieces();placed=true;checkSolution();}
					
				}
				
				//AFTER RECURSION
				if (placed) {
					currX=p.x;
					currY=p.y;
					this.remove(p);
				}
			}
		}
		//if nothing was placed, increment and try again
		if (!placed) {
			if (currX==this.width-1 && currY==this.height-1) {
				return;
			}
			else if (currX==this.width-1) {
				currX=0; currY++;
			}
			else currX++;
			
			this.placePieces();
		}
		
		
		System.out.println("END");
		long endTime   = System.currentTimeMillis();
    	long totalTime = endTime - startTime;
    	System.out.println(totalTime + " milliseconds - END TIME");
	}
	
	public void checkSolution() {
		if (isSolved()) {
			System.out.println("SOLVED!!!!");
			long endTime   = System.currentTimeMillis();
	    	long totalTime = endTime - startTime;
	    	System.out.println(totalTime + " milliseconds - SOLUTION");
			ArrayList<Piece> newSolution = new ArrayList<Piece>();
			for (Piece pce : this.pieces) {
				newSolution.add((Piece) pce.clone());
			}
			solutions.add(newSolution);
			System.out.println("added new solution");
		}
	}
	
	public boolean isSolved() {
		return Arrays.deepEquals(this.current, this.goal);
	}
	
	public void place(Piece p, int y, int x) {
		for (int j = 0 ; j < p.height ; j++) {
			for (int i = 0 ; i < p.width ; i++) {
				if (p.tiles[j][i] != ' ' && p.tiles[j][i] != '\u0000' )
					this.current[y+j][x+i] = p.tiles[j][i];
			}
		}
		p.x = x;
		p.y = y;
		this.usedPieces.add(p);
		System.out.println("Placed piece: " + p + " on the board! at y: " + currY + ", x: " + currX);
		p.placed = true;
	}
	
	public void remove(Piece p) {
		if (!usedPieces.contains(p)){
			System.out.println("ATTEMPED TO REMOVE PIECE NOT ON BOARD");
			return;
		}
		for (int j = 0 ; j < p.height ; j++) {
			for (int i = 0 ; i < p.width ; i++) {
				if (p.tiles[j][i] != ' ' && p.tiles[j][i] != '\u0000' )
					this.current[p.y+j][p.x+i] = ' ';
			}
		}
		p.x = -1;
		p.y = -1;
		this.usedPieces.remove(p);
		
		System.out.println("Piece " + p + "successfully removed");
		p.placed = false;
	}
	
	public String toString() {
		String s = "";
		for (int y = 0 ; y < current.length ; y++) {
			for (int x = 0 ; x < current[y].length; x++) {
				s+=current[y][x];
			}
			s+="\n";
		}
		return s;
	}
	
}
