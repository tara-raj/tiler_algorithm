import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	Board b;
	JFrame f;
	public int change = 0;
	JButton start = null;
	JButton next = null;
	
	ArrayList<Piece> solutions;
	int solutionCounter = 0;
	
	public Window(Board b) throws IOException {
		super();
		this.b = b;
		f = new JFrame();
		System.out.println(b.width + " / " + b.height);
		f.setSize(b.width*25, b.height*25);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		start = new JButton("Start Program");
		start.setVerticalTextPosition(AbstractButton.CENTER);
		start.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		start.setMnemonic(KeyEvent.VK_D);
		start.setActionCommand("start");
		start.addActionListener(this);
		this.add(start);
		
		next = new JButton("Next Solution");
		next.setVerticalTextPosition(AbstractButton.CENTER);
		next.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		next.setMnemonic(KeyEvent.VK_D);
		next.setActionCommand("next");
		next.addActionListener(this);
		this.add(next);
		next.setVisible(false);
		
		
	}
	
	public void refresh() {
		this.setSize(b.width*25, b.height*25+10);
		revalidate();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.solutions != null)
		for (Piece p : this.solutions) {
//			float r = rand.nextFloat();
//			float g = rand.nextFloat();
//			float b = rand.nextFloat();
//			Color dotColor = new Color(r,g,b);
			for (int y = 0; y < p.height; y++) {
				for (int x = 0; x < p.width; x++) {
				
					int asc = p.tiles[y][x];
					
					Color color = Color.WHITE;
				
					if (asc == 32) { // 32 is char value for space
						color = Color.WHITE;
					} else {
						g.setColor(Color.WHITE);
						g.fillRect((x + p.x) * 25, (y + p.y) * 25, 27, 27);
						color = colorLib().get(asc);
						g.setColor(color);
						g.fillRect((x + p.x) * 25, (y + p.y) * 25, 25, 25);
					}
				}
			}
		}
	}
	

	// Full library of HTML preset colors from W3Schools documentation
	// An ArrayList of the preset colors is created so we can refer to the
	// library in our paint method (there are 148 preset colors)
	public static ArrayList<Color> colorLib() {
		ArrayList<Color> colorList = new ArrayList<Color>(); // ArrayList to
																// hold colors
		colorList.add(new Color(0xF0, 0xF8, 0xFF));
		colorList.add(new Color(0xFA, 0xEB, 0xD7));
		colorList.add(new Color(0x00, 0xFF, 0xFF));
		colorList.add(new Color(0x7F, 0xFF, 0xD4));
		colorList.add(new Color(0xF0, 0xFF, 0xFF));
		colorList.add(new Color(0xF5, 0xF5, 0xDC));
		colorList.add(new Color(0xFF, 0xE4, 0xC4));
		colorList.add(new Color(0x00, 0x00, 0x00));
		colorList.add(new Color(0xFF, 0xEB, 0xCD));
		colorList.add(new Color(0x00, 0x00, 0xFF));
		colorList.add(new Color(0x8A, 0x2B, 0xE2));
		colorList.add(new Color(0xA5, 0x2A, 0x2A));
		colorList.add(new Color(0xDE, 0xB8, 0x87));
		colorList.add(new Color(0x5F, 0x9E, 0xA0));
		colorList.add(new Color(0x7F, 0xFF, 0x00));
		colorList.add(new Color(0xD2, 0x69, 0x1E));
		colorList.add(new Color(0xFF, 0x7F, 0x50));
		colorList.add(new Color(0x64, 0x95, 0xED));
		colorList.add(new Color(0xFF, 0xF8, 0xDC));
		colorList.add(new Color(0xDC, 0x14, 0x3C));
		colorList.add(new Color(0x00, 0xFF, 0xFF));
		colorList.add(new Color(0x00, 0x00, 0x8B));
		colorList.add(new Color(0x00, 0x8B, 0x8B));
		colorList.add(new Color(0xB8, 0x86, 0x0B));
		colorList.add(new Color(0xA9, 0xA9, 0xA9));
		colorList.add(new Color(0x00, 0x64, 0x00));
		colorList.add(new Color(0xBD, 0xB7, 0x6B));
		colorList.add(new Color(0x8B, 0x00, 0x8B));
		colorList.add(new Color(0x55, 0x6B, 0x2F));
		colorList.add(new Color(0xFF, 0x8C, 0x00));
		colorList.add(new Color(0x99, 0x32, 0xCC));
		colorList.add(new Color(0x8B, 0x00, 0x00));
		colorList.add(new Color(0xE9, 0x96, 0x7A));
		colorList.add(new Color(0x8F, 0xBC, 0x8F));
		colorList.add(new Color(0x48, 0x3D, 0x8B));
		colorList.add(new Color(0x2F, 0x4F, 0x4F));
		colorList.add(new Color(0x00, 0xCE, 0xD1));
		colorList.add(new Color(0x94, 0x00, 0xD3));
		colorList.add(new Color(0xFF, 0x14, 0x93));
		colorList.add(new Color(0x00, 0xBF, 0xFF));
		colorList.add(new Color(0x69, 0x69, 0x69));
		colorList.add(new Color(0x1E, 0x90, 0xFF));
		colorList.add(new Color(0xB2, 0x22, 0x22));
		colorList.add(new Color(0xFF, 0xFA, 0xF0));
		colorList.add(new Color(0x22, 0x8B, 0x22));
		colorList.add(new Color(0xFF, 0x00, 0xFF));
		colorList.add(new Color(0xDC, 0xDC, 0xDC));
		colorList.add(new Color(0xF8, 0xF8, 0xFF));
		colorList.add(new Color(0xFF, 0xD7, 0x00));
		colorList.add(new Color(0xDA, 0xA5, 0x20));
		colorList.add(new Color(0x80, 0x80, 0x80));
		colorList.add(new Color(0x00, 0x80, 0x00));
		colorList.add(new Color(0xAD, 0xFF, 0x2F));
		colorList.add(new Color(0xF0, 0xFF, 0xF0));
		colorList.add(new Color(0xFF, 0x69, 0xB4));
		colorList.add(new Color(0xCD, 0x5C, 0x5C));
		colorList.add(new Color(0x4B, 0x00, 0x82));
		colorList.add(new Color(0xFF, 0xFF, 0xF0));
		colorList.add(new Color(0xF0, 0xE6, 0x8C));
		colorList.add(new Color(0xE6, 0xE6, 0xFA));
		colorList.add(new Color(0xFF, 0xF0, 0xF5));
		colorList.add(new Color(0x7C, 0xFC, 0x00));
		colorList.add(new Color(0xFF, 0xFA, 0xCD));
		colorList.add(new Color(0xAD, 0xD8, 0xE6));
		colorList.add(new Color(0xF0, 0x80, 0x80));
		colorList.add(new Color(0xE0, 0xFF, 0xFF));
		colorList.add(new Color(0xFA, 0xFA, 0xD2));
		colorList.add(new Color(0xD3, 0xD3, 0xD3));
		colorList.add(new Color(0x90, 0xEE, 0x90));
		colorList.add(new Color(0xFF, 0xB6, 0xC1));
		colorList.add(new Color(0xFF, 0xA0, 0x7A));
		colorList.add(new Color(0x20, 0xB2, 0xAA));
		colorList.add(new Color(0x87, 0xCE, 0xFA));
		colorList.add(new Color(0x77, 0x88, 0x99));
		colorList.add(new Color(0xB0, 0xC4, 0xDE));
		colorList.add(new Color(0xFF, 0xFF, 0xE0));
		colorList.add(new Color(0x00, 0xFF, 0x00));
		colorList.add(new Color(0x32, 0xCD, 0x32));
		colorList.add(new Color(0xFA, 0xF0, 0xE6));
		colorList.add(new Color(0xFF, 0x00, 0xFF));
		colorList.add(new Color(0x80, 0x00, 0x00));
		colorList.add(new Color(0x66, 0xCD, 0xAA));
		colorList.add(new Color(0x00, 0x00, 0xCD));
		colorList.add(new Color(0xBA, 0x55, 0xD3));
		colorList.add(new Color(0x93, 0x70, 0xDB));
		colorList.add(new Color(0x3C, 0xB3, 0x71));
		colorList.add(new Color(0x7B, 0x68, 0xEE));
		colorList.add(new Color(0x00, 0xFA, 0x9A));
		colorList.add(new Color(0x48, 0xD1, 0xCC));
		colorList.add(new Color(0xC7, 0x15, 0x85));
		colorList.add(new Color(0x19, 0x19, 0x70));
		colorList.add(new Color(0xF5, 0xFF, 0xFA));
		colorList.add(new Color(0xFF, 0xE4, 0xE1));
		colorList.add(new Color(0xFF, 0xE4, 0xB5));
		colorList.add(new Color(0xFF, 0xDE, 0xAD));
		colorList.add(new Color(0x00, 0x00, 0x80));
		colorList.add(new Color(0xFD, 0xF5, 0xE6));
		colorList.add(new Color(0x80, 0x80, 0x00));
		colorList.add(new Color(0x6B, 0x8E, 0x23));
		colorList.add(new Color(0xFF, 0xA5, 0x00));
		colorList.add(new Color(0xFF, 0x45, 0x00));
		colorList.add(new Color(0xDA, 0x70, 0xD6));
		colorList.add(new Color(0xEE, 0xE8, 0xAA));
		colorList.add(new Color(0x98, 0xFB, 0x98));
		colorList.add(new Color(0xAF, 0xEE, 0xEE));
		colorList.add(new Color(0xDB, 0x70, 0x93));
		colorList.add(new Color(0xFF, 0xEF, 0xD5));
		colorList.add(new Color(0xFF, 0xDA, 0xB9));
		colorList.add(new Color(0xCD, 0x85, 0x3F));
		colorList.add(new Color(0xFF, 0xC0, 0xCB));
		colorList.add(new Color(0xDD, 0xA0, 0xDD));
		colorList.add(new Color(0xB0, 0xE0, 0xE6));
		colorList.add(new Color(0x80, 0x00, 0x80));
		colorList.add(new Color(0xFF, 0x00, 0x00));
		colorList.add(new Color(0xBC, 0x8F, 0x8F));
		colorList.add(new Color(0x41, 0x69, 0xE1));
		colorList.add(new Color(0x8B, 0x45, 0x13));
		colorList.add(new Color(0xFA, 0x80, 0x72));
		colorList.add(new Color(0xF4, 0xA4, 0x60));
		colorList.add(new Color(0x2E, 0x8B, 0x57));
		colorList.add(new Color(0xFF, 0xF5, 0xEE));
		colorList.add(new Color(0xA0, 0x52, 0x2D));
		colorList.add(new Color(0xC0, 0xC0, 0xC0));
		colorList.add(new Color(0x87, 0xCE, 0xEB));
		colorList.add(new Color(0x6A, 0x5A, 0xCD));
		colorList.add(new Color(0x70, 0x80, 0x90));
		colorList.add(new Color(0xFF, 0xFA, 0xFA));
		colorList.add(new Color(0x00, 0xFF, 0x7F));
		colorList.add(new Color(0x46, 0x82, 0xB4));
		colorList.add(new Color(0xD2, 0xB4, 0x8C));
		colorList.add(new Color(0x00, 0x80, 0x80));
		colorList.add(new Color(0xD8, 0xBF, 0xD8));
		colorList.add(new Color(0xFF, 0x63, 0x47));
		colorList.add(new Color(0x40, 0xE0, 0xD0));
		colorList.add(new Color(0xEE, 0x82, 0xEE));
		colorList.add(new Color(0xF5, 0xDE, 0xB3));
		colorList.add(new Color(0xFF, 0xFF, 0xFF));
		colorList.add(new Color(0xF5, 0xF5, 0xF5));
		colorList.add(new Color(0xFF, 0xFF, 0x00));
		colorList.add(new Color(0x9A, 0xCD, 0x32));

		return colorList;
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("start")) {
			b.placePieces();
			System.out.println("Done!");
			start.setVisible(false);
			next.setVisible(true);
		}
		if (e.getActionCommand().equals("next")) {
			if (solutionCounter >= b.solutions.size() - 1) {
				next.setVisible(false);
			}
			
			try{
			this.solutions = b.solutions.get(solutionCounter);
			solutionCounter++;
			}
			catch(Exception ArrayOutOfBounds){
				
			}
			
			revalidate();
			repaint();
		}
		
	}



}
