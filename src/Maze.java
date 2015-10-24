import java.awt.Color;


public class Maze {
	 public MazeTile tile[][];
	 private Point startPoint;
	 
	 public Maze(int rowSize, int colSize){
		 tile = new MazeTile[rowSize][colSize];
		 for(int i=0; i<rowSize; i++){
			 for(int k=0; k<colSize; k++){
				 tile[i][k] = MazeTile.EMPTY;
			 }
		 }
		 startPoint = new Point(-1,-1, null);
	 }
	 
	 public void setStartPoint(int r, int c){
		 startPoint.row = r;
		 startPoint.col = c;
		 startPoint.parent = null;
	 }
	 
	 public Point getStartPoint(){
		 return startPoint;
	 }
	 
	 @Override
	 public String toString(){
		 if(tile == null){
			 return null;
		 }
		  
		 StringBuilder builder = new StringBuilder();
		 String ret = "";
		 for(int i=0; i<tile.length; i++){
			 for(int k=0; k<tile[i].length; k++){
				 switch(tile[i][k]){
				 	case EMPTY:
				 		builder.append("o");
				 		break;
				 	case START:
				 		builder.append("$");
				 		break;
				 	case END:
				 		builder.append("@");
				 		break;
				 	case WALL:
				 		builder.append("|");
				 		break;
				 	case VISITED:
				 		builder.append("x");
				 		break;
				 	case CURRENT:
				 		builder.append("/");
				 		break;
				 	case CHILD_OF_CURRENT_NODE:
				 		builder.append("#");
				 		break;
        			case PATH:
        				builder.append("*");
        				break;
				 }
			 }
			 builder.append("\n");
		 }
		 
		 builder.append("\n\n\n");
		 return builder.toString();
	 }
}
