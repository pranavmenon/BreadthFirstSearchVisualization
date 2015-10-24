import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;



public class Engine {
	
	private JFrame frame;
	private MazePainter painter;
	
	public void run(){
		Maze maze = MazeGenerator.generate();
		int numOfRow = maze.tile.length;
		int numOfCol = maze.tile[0].length;
		System.out.println(maze);
		
		painter = new MazePainter(maze);
		painter.delayedRepaint(0);
		frame = new JFrame();
		frame.setSize( ((numOfCol+2)*10)-3, ((numOfRow+4)*10));
		frame.add(painter);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		//start bfs
		Queue<Point> q = new LinkedList<Point>();
		q.add(maze.getStartPoint());
		
		
		Point endPoint = null;
		while(!q.isEmpty()){
			Point p = q.remove();
			if(maze.tile[p.row][p.col] == MazeTile.END){
				endPoint = p;
				break;
			}
			
			//queue children
			for(int i=p.row-1; i<=p.row+1; i++){
				for(int k=p.col-1; k<=p.col+1; k++){
					conditionalEnqueue(i,k,p,q,maze);
				}
			}
			
			if(maze.tile[p.row][p.col] != MazeTile.START){
				maze.tile[p.row][p.col] = MazeTile.VISITED;
			}
			
			painter.delayedRepaint(10);
		}
		
		
		//paint path
		if(endPoint != null){
			
			Point temp = endPoint.parent;
			while(temp!=null && maze.tile[temp.row][temp.col]!=MazeTile.START){
				maze.tile[temp.row][temp.col] = MazeTile.PATH;
				temp = temp.parent;
			}
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			painter.repaint();
		}
		
		
	}
	
	private static void conditionalEnqueue(int row, int col, Point parent, Queue<Point> q, Maze maze){
		
		//check indices
		if(row==-1 || row==maze.tile.length || col==-1 || col==maze.tile[row].length){
			return;
		}
		
		//check if tile can be entered
		if( (maze.tile[row][col] != MazeTile.EMPTY) && (maze.tile[row][col] != MazeTile.END) ){
			return;
		}
		
		
		//valid tile
		Point p = new Point(row,col,parent);
		q.add(p);
		if(maze.tile[row][col] != MazeTile.END){
			maze.tile[row][col] = MazeTile.CHILD_OF_CURRENT_NODE;
		}
		
		return;
	}
}
