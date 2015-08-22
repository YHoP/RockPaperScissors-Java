import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.*;
import java.lang.*;

public class RockPaperScissors {
  public static void main(String[] args){
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/input.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/output", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/output.vtl");

      //get the usernames from the forms
      String playerOne = request.queryParams("playerOne");
      int playerOneMove = Integer.parseInt(request.queryParams("playerOneMove"));

      String playerTwo = request.queryParams("playerTwo");
      int playerTwoMove = Integer.parseInt(request.queryParams("playerTwoMove"));

      //if the user two field is left blank, the computer plays.
      if (playerTwo == ""){
        playerTwo = "computer";
      }

      if (playerTwoMove == 3){
        Random move = new Random();
        playerTwoMove = move.nextInt(3);
      }

      // run the main code
      Integer gameResults = gameResult(playerOneMove, playerTwoMove);
      String gameResultsFinal;

      switch(gameResults){
        case 0:
        gameResultsFinal = String.format("It's a tie! Both %s and %s win :)", playerOne, playerTwo);
          break;

        case 1:
        gameResultsFinal = String.format("%s wins!" , playerOne);
          break;

        case 2:
        gameResultsFinal = String.format("%s wins!" , playerTwo);
          break;

        default:
        gameResultsFinal = "Your code is broken!";
          break;
      }
      //output the winning result to the page
      model.put("playerOne", playerOne);
      model.put("playerOneMove", moveResult(playerOneMove));
      model.put("playerTwo", playerTwo);
      model.put("playerTwoMove", moveResult(playerTwoMove));
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
    public static Integer gameResult (int playerOneMove, int playerTwoMove){
      Integer result;

      if (playerOneMove == playerTwoMove){
        result = 0; // It's a tie!
      }else if(playerOneMove == 0 && playerTwoMove == 2){
        result = 1; //Player One wins!
      }else if(playerOneMove == 1 && playerTwoMove == 0){
        result = 1;
      }else if(playerOneMove == 2 && playerTwoMove == 1){
        result = 1;
      }else {
        result = 2; // Player Two wins!
      }
      return result;
    }
}
