package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class WebSessionHelper extends HelperBase {

  public WebSessionHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Login']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void logout() {
    click(By.cssSelector("span.user-info"));
    click(By.linkText("Logout"));
  }
}
