
import java.util.*;


public class Test {
  public static void main(String[] args){

    ArrayList<String> playerNResult= new ArrayList<String>();
    for (Integer i =0; i<2; i++){
    Random move = new Random();
    Integer eachMove = move.nextInt(3);
    // print out the random number
    System.out.println("Random number" + eachMove);
    // print out the move
    playerNResult.add(moveResult(eachMove));
    System.out.println("Index: "+ i);
    System.out.println("Player Result: "+ playerNResult.get(i));
  }

}
    public static String moveResult (Integer number){
      String playResult;

      switch(number){
        case 0:
        // output Rock
        playResult = "Rock";
          break;

        case 1:
        // output Paper
        playResult = "Paper";
          break;

        default:
        // output Secissors
        playResult = "Scissors";
          break;
      }
      return playResult;
  }

  // public static Boolean gameResult ()

}
