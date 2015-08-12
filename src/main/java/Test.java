
import java.util.*;


public class Test {
  public static void main(String[] args){

    ArrayList<String> results = twoPlayerResults();
    String gameResults = gameResult(results);
    System.out.println(gameResults);

}

  public static ArrayList<String> twoPlayerResults(){
    ArrayList<String> playerNResult = new ArrayList<String>();

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
    // come back for it later...?
    return playerNResult;
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

  public static String gameResult (ArrayList<String> results){  //<-- how do we get these variables from out code above
    String printOut;
    String player1Result = results.get(0);
    String player2Result = results.get(1);

    if (player1Result == player2Result){
      printOut = "It's a tie!";
    }else if(player1Result== "Rock" && player2Result == "Scissors"){
      printOut = "Player1 wins!";
    }else if(player1Result== "Paper" && player2Result == "Rock"){
      printOut = "Player1 wins!";
    }else if(player1Result== "Scissors" && player2Result == "Paper"){
      printOut = "Player1 wins!";
    }else {
      printOut = "Player2 wins!";
    }

    return printOut;
  }

}
