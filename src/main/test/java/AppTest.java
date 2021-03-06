import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Let's Play Rock Paper Scissors!");
  }

  @Test
  public void computerPlaysIfUserTwoBlank_Istrue(){
    goTo("http://localhost:4567");
    fill("#userOne").with("Testing");
    submit(".btn");
    assertThat(pageSource()).contains("Player two's name is : Computer");
  }

  @Test
  public void computerPlaysIfUserTwoBlank_IstrueToo(){
    goTo("http://localhost:4567");
    fill("#userTwo").with("");
    submit(".btn");
    assertThat(pageSource()).contains("Player two's name is : Computer");
  }
}
