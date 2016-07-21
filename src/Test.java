import java.io.IOException;

import javax.swing.JApplet;
@SuppressWarnings("serial")

public class Test extends JApplet {
	static Window w = null;
	static Board b;
	
    public void init() {
    	Board b = new Board("../res/2.txt");
		try {w = new Window(b);} 
		catch (IOException e) {e.printStackTrace();}
		
        this.add(w);
        
        for (Piece p : b.pieces) {
        	System.out.println(p);
        }

//		b.placePieces();
    }
}
