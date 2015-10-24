import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JPanel;

public class MazePainter extends JPanel {
	private Maze maze;
	private LinkedList<Rectangle> cells;
	private int numOfRow, numOfCol;
    private final int cellSize = 10;
    private long lastRepaintTime = 0;

	public MazePainter(Maze m){
		maze = m;
		numOfRow = maze.tile.length;
		numOfCol = maze.tile[0].length;
		cells = new LinkedList<Rectangle>();
		lastRepaintTime = System.currentTimeMillis();
	}
	
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
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.BLACK);
        for (int row = 0; row < numOfRow; row++) {
        	for (int col = 0; col < numOfCol; col++) {
        		Rectangle cell = new Rectangle((col * cellSize), (row * cellSize), cellSize, cellSize);
        		cells.add(cell);

        		switch(maze.tile[row][col]){
        			case EMPTY:
        				g2d.setColor(Color.WHITE);
        				g2d.fill(cell);
        				break;
        			case START:
        				g2d.setColor(Color.GREEN);
        				g2d.fill(cell);
        				break;
        			case END:
        				g2d.setColor(Color.RED);
        				g2d.fill(cell);
        				break;
        			case WALL:
        				g2d.setColor(Color.BLACK);
        				g2d.fill(cell);
        				break;
        			case VISITED:
        				g2d.setColor(Color.BLUE);
        				g2d.fill(cell);
        				break;
        			case CURRENT:
        				g2d.setColor(Color.PINK);
        				g2d.fill(cell);
        				break;
        			case CHILD_OF_CURRENT_NODE:
        				g2d.setColor(Color.YELLOW);
        				g2d.fill(cell);
        				break;
        			case PATH:
        				g2d.setColor(Color.GREEN);
        				g2d.fill(cell);
        				break;
        		}

        	}
        }

        g2d.setColor(Color.BLACK);
        for(Rectangle cell : cells){
        	g2d.draw(cell);
        }

        setVisible(true);
        g2d.dispose();
    }
}