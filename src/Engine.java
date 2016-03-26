import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;

public class Engine {

  private JFrame frame;
  private MazePainter painter;
  private static int INITIAL_MILLISECOND_DELAY = 5000;
  private static int REPAINT_MILLISECOND_DELAY = 10;

  
  public void run(){
    //get random maze
    Maze maze = MazeGenerator.generate();
    System.out.println(maze);
    initializeWindow(maze);

    //small delay so that the user can see the start/end point before the bfs begins
    Utils.delay(INITIAL_MILLISECOND_DELAY);

    //run bfs
    Point endPoint = runVisualizationOfBreadthFirstSearch(maze);

    //paint path to end point
    if(endPoint != null){
      markPathToEndPointInMaze(maze, endPoint);
      Utils.delay(2000);
      painter.repaint();
    }
  }



  private void initializeWindow(Maze maze){
    painter = new MazePainter(maze);
    painter.delayedRepaint(0);
    frame = new JFrame();
    frame.setSize( ((maze.numOfColumns()+2)*10)-3, ((maze.numOfRows()+4)*10));
    frame.add(painter);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }



  //returns the end point if found, else returns null
  private Point runVisualizationOfBreadthFirstSearch(Maze maze){
    Point endPoint = null;
    Queue<Point> queue = new LinkedList<Point>();
    queue.add(maze.getStartPoint());

    //start bfs
    while(!queue.isEmpty()){
      Point p = queue.remove();
      
      //check if end point was found
      if(maze.getTileStatus(p.getRow(), p.getCol()) == MazeTileStatus.END){
        endPoint = p;
        break;
      }

      //queue children
      for(int i=p.getRow()-1; i<=p.getRow()+1; i++){
        for(int k=p.getCol()-1; k<=p.getCol()+1; k++){
          
          if(maze.isTileWithinBounds(i,k) && maze.tileCanBeEntered(i,k)){
            queue.add(new Point(i,k,p));
            if(maze.getTileStatus(i,k) != MazeTileStatus.END){
              maze.setTileStatus(i, k, MazeTileStatus.CHILD_OF_CURRENT_NODE);
            }
          }
          
        }
      }

      //update the current tile's status
      if(maze.getTileStatus(p.getRow(), p.getCol()) != MazeTileStatus.START){
        maze.setTileStatus(p.getRow(), p.getCol(), MazeTileStatus.VISITED);
      }

      painter.delayedRepaint(REPAINT_MILLISECOND_DELAY);
    }

    return endPoint;
  }



  private void markPathToEndPointInMaze(Maze maze, Point endPoint){
    if(endPoint != null){
      Point temp = endPoint.getParent();
      while(temp!=null && (maze.getTileStatus(temp.getRow(), temp.getCol()) != MazeTileStatus.START)){
        maze.setTileStatus(temp.getRow(), temp.getCol(), MazeTileStatus.PATH);
        temp = temp.getParent();
      }
    }
  }

}
