import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Random;

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

      // Set up random result for each move
      Random move = new Random();
      Integer eachMove = move.nextInt(3);

      

      //get the usernames from the forms
      String userOne = request.queryParams("userOne");
      model.put("userOne", request.queryParams("userOne"));

      String userTwo = request.queryParams("userTwo");
      model.put("userTwo", request.queryParams("userTwo"));

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

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

 public static Boolean gameResult (){

 }


}
