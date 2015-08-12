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
      model.put("userOne", userOne);

      String userTwo = request.queryParams("userTwo");
      model.put("userTwo", userTwo);

      // run the main code
      ArrayList<String> results = twoPlayerResults();
      Integer gameResults = gameResult(results);
      String gameResultsFinal;

      if (gameResults == 0){
        gameResultsFinal = String.format("It's a tie! Both %s and %s win :)", userOne, userTwo);
      }else if(gameResults == 1){
        gameResultsFinal = String.format("%s wins!" , userOne);
      }
      else{
        gameResultsFinal = String.format("%s wins!" , userTwo);
      }

      //output the winner to the page
      model.put("gameResultsFinal", gameResultsFinal);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }

  public static ArrayList<String> twoPlayerResults(){
    ArrayList<String> playerNResult = new ArrayList<String>();

    for (Integer i =0; i<2; i++){
      //make a random number for each player
    Random move = new Random();
    Integer eachMove = move.nextInt(3);

    playerNResult.add(moveResult(eachMove));
    }
    return playerNResult;
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

        default:
        playResult = "Scissors";
          break;
      }
      return playResult;
  }

      //compare the game moves and determine a winner
      public static Integer gameResult (ArrayList<String> results){
        Integer printOut;
        String player1Result = results.get(0);
        String player2Result = results.get(1);

        if (player1Result == player2Result){
          // It's a tie!
          printOut = 0;
        }else if(player1Result== "Rock" && player2Result == "Scissors"){
          //Player One wins!
          printOut = 1;
        }else if(player1Result== "Paper" && player2Result == "Rock"){
          printOut = 1;
        }else if(player1Result== "Scissors" && player2Result == "Paper"){
          printOut = 1;
        }else {
          // Player Two wins!
          printOut = 2;
        }
        return printOut;
      }

}
