
public final class Utils {

  private Utils(){}

  public static void delay(int milliseconds){
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
