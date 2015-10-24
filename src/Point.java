
public class Point {
	public int row, col;
	public Point parent;
	
	public Point(int r, int c, Point parent){
		row = r;
		col = c;
		this.parent = parent;
	}
}
