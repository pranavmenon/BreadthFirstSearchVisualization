import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JPanel;

public class MazePainter extends JPanel {

  private Maze maze;
  private LinkedList<Rectangle> cells;
  private final int cellSize = 10;
  private long lastRepaintTime = 0;


  public MazePainter(Maze m){
    maze = m;
    cells = new LinkedList<Rectangle>();
    lastRepaintTime = System.currentTimeMillis();
  }

  
  //only paint after a certain amount of time has elapsed since the last repaint
  public void delayedRepaint(int timeDelay){
    while(true){
      boolean canTick = (System.currentTimeMillis() - lastRepaintTime) >= timeDelay;
      if (canTick) {
        lastRepaintTime = System.currentTimeMillis();
        repaint();
        break;
      }
    }
  }
  

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();

    g2d.setColor(Color.BLACK);
    for (int row = 0; row < maze.numOfRows(); row++) {
      for (int col = 0; col < maze.numOfColumns(); col++) {
        Rectangle cell = new Rectangle((col * cellSize), (row * cellSize), cellSize, cellSize);
        cells.add(cell);
        Color requiredTileColor = getRequiredTileColor(row, col);
        g2d.setColor(requiredTileColor);
        g2d.fill(cell);
      }
    }

    g2d.setColor(Color.BLACK);
    for(Rectangle cell : cells){
      g2d.draw(cell);
    }
    setVisible(true);
    g2d.dispose();
  }


  private Color getRequiredTileColor(int row, int col){
    if(maze.isTileWithinBounds(row, col) == false){
      throw new IllegalArgumentException("Row and column not within bounds of maze!");
    }
    else{
      switch(maze.getTileStatus(row, col)){
        case EMPTY:
          return Color.WHITE;
        case START:
          return Color.GREEN;
        case END:
          return Color.RED;
        case WALL:
          return Color.BLACK;
        case VISITED:
          return Color.BLUE;
        case CURRENT:
          return Color.PINK;
        case CHILD_OF_CURRENT_NODE:
          return Color.YELLOW;
        case PATH:
          return Color.GREEN;
        default:
          throw new IllegalStateException("Invalid tile status '" + maze.getTileStatus(row, col) + "'!");
      }
    }
  }

}