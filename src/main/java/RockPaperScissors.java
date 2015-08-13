import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.*;

public class RockPaperScissors {
  public static void main(String[] args){
    String layout = "templates/layout.vtl";

    // set up input page for player's names
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/input.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/output", (request, response) -> {
      //set up the hashmap and set the output
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/output.vtl");

      //get the usernames from the forms
      String userOne = request.queryParams("userOne");
      String playerOneHand = request.queryParams("userOneRadio");

      String userTwo = request.queryParams("userTwo");
      String playerTwoHand = request.queryParams("userTwoRadio");

      //if the user two field is left blank, the computer plays.
      if (userTwo == ""){
        userTwo = "Computer";
      }

      Random move = new Random();
      if (playerTwoHand == "Random"){
        playerTwoHand = moveResult(move.nextInt(3)); // not sure why it doesn't assign the result
      }

      // run the main code
      Integer gameResults = gameResult(playerOneHand, playerTwoHand);
      String gameResultsFinal;

      if (gameResults == 0){
        gameResultsFinal = String.format("It's a tie! Both %s and %s win :)", userOne, userTwo);
      }else if(gameResults == 1){
        gameResultsFinal = String.format("%s wins!" , userOne);
      }
      else{
        gameResultsFinal = String.format("%s wins!" , userTwo);
      }

      //output the winning result to the page
      model.put("userOne", userOne);
      model.put("userOneRadio", playerOneHand);
      model.put("userTwo", userTwo);
      model.put("userTwoRadio", playerTwoHand);
      model.put("gameResultsFinal", gameResultsFinal);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }


   //set up the cases for player moves
    public static String moveResult (Integer number){
      String playResult;

      switch(number){
        case 0:
        playResult = "Rock";
          break;

        case 1:
        playResult = "Paper";
          break;

        case 2:
        playResult = "Scissors";
          break;

        default:
        playResult = "Other";
          break;
      }
      return playResult;
  }

    //compare the game moves and determine a winner
    public static Integer gameResult (String playerOne, String playerTwo){
      Integer printOut;

      if (playerOne == playerTwo){
        printOut = 0; // It's a tie!
      }else if(playerOne == "Rock" && playerTwo == "Scissors"){
        printOut = 1; //Player One wins!
      }else if(playerOne == "Paper" && playerTwo == "Rock"){
        printOut = 1;
      }else if(playerOne == "Scissors" && playerTwo == "Paper"){
        printOut = 1;
      }else {
        printOut = 2; // Player Two wins!
      }
      return printOut;
    }

}
