import lombok.Getter;


public class Point {
	@Getter private final int row, col;
	@Getter private final Point parent;
	
	public Point(int row, int col, Point parent){
		this.row = row;
		this.col = col;
		this.parent = parent;
	}
}
