import java.util.ArrayList;
import java.util.Arrays;


public class MazeGenerator {
	
	private static Maze maze;
	private static final int rowSize = 40;
	private static final int colSize = 60;
	private static final double percentOfWalls = 0.20;
	private static final int numOfWalls = (int)(percentOfWalls*rowSize*colSize);
	private static final boolean generateRandomMaze = true;
	
	public static Maze generate(){
		
		if(generateRandomMaze){
			return generateRandomMaze();
		}
		else{
			return generatePredefinedMaze();
		}
		
	}
	
	private static Maze generateRandomMaze(){
		
		maze = new Maze(rowSize, colSize);
		
		//put starting tile
		int randRow = (int)(Math.random()*(rowSize));
		int randCol = (int)(Math.random()*(colSize));
		maze.tile[randRow][randCol] = MazeTile.START;
		maze.setStartPoint(randRow, randCol);
		
		//put ending tile
		while(maze.tile[randRow][randCol] != MazeTile.EMPTY){
			randRow = (int)(Math.random()*(rowSize));
			randCol = (int)(Math.random()*(colSize));
		}
		maze.tile[randRow][randCol] = MazeTile.END;
		
		
		//put walls
		for(int i=0; i<numOfWalls; i++){
			while(maze.tile[randRow][randCol] != MazeTile.EMPTY){
				randRow = (int)(Math.random()*(rowSize));
				randCol = (int)(Math.random()*(colSize));
			}
			maze.tile[randRow][randCol] = MazeTile.WALL;
		}
			
		return maze;
	}
	
	private static Maze generatePredefinedMaze(){
		return null;
	}
	
}
