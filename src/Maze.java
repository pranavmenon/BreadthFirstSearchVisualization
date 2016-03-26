import lombok.Getter;



public class Maze {
  private MazeTileStatus tiles[][];
  @Getter private Point startPoint;


  public Maze(int rowSize, int colSize){
    tiles = new MazeTileStatus[rowSize][colSize];
    for(int i=0; i<rowSize; i++){
      for(int k=0; k<colSize; k++){
        tiles[i][k] = MazeTileStatus.EMPTY;
      }
    }
    startPoint = null;
  }



  public void setStartPoint(int row, int col){
    startPoint = new Point(row, col, null);
  }



  public void setTileStatus(int row, int col, MazeTileStatus status){
    if(!isTileWithinBounds(row, col)){
      throw new IllegalArgumentException("Row and column not within bounds of maze!");
    }
    else if(status == null){
      throw new IllegalArgumentException("Paramter 'status' cannot be null!");
    }
    else{
      tiles[row][col] = status;
    }
  }



  public MazeTileStatus getTileStatus(int row, int col){
    if(isTileWithinBounds(row, col)){
      return tiles[row][col];
    }
    else{
      throw new IllegalArgumentException("Row and column not within bounds of maze!");
    }
  }



  public int numOfRows(){
    return tiles.length;
  }



  public int numOfColumns(){
    return tiles[0].length;
  }



  public boolean isTileWithinBounds(int row, int col){
    if(row<0 || row>tiles.length || col<0 || col>tiles[0].length){
      return false;
    }
    else{
      return true;
    }
  }


  
  public boolean tileCanBeEntered(int row, int col){
    if( (getTileStatus(row, col) != MazeTileStatus.EMPTY) && (getTileStatus(row, col) != MazeTileStatus.END) ){
      return false;
    } 
    else{
      return true;
    }
  }
  
  

  @Override
  public String toString(){
    if(tiles == null){
      return null;
    }

    StringBuilder builder = new StringBuilder();
    for(int i=0; i<tiles.length; i++){
      for(int k=0; k<tiles[i].length; k++){
        switch(tiles[i][k]){
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
