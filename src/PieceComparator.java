import java.util.Comparator;

public class PieceComparator implements Comparator<Piece> {


	@Override
	public int compare(Piece o1, Piece o2) {
		// TODO Auto-generated method stub
		return o2.height*o2.width - o1.height*o1.width;
	}
	
}