package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void managePage() {

    if (isElementPresent(By.xpath("///h4[@innertext='Site Information']"))) {
      return;
    }
    click(By.cssSelector("i.fa.fa-gears.menu-icon"));
  }

  public void manageUserPage() {
    if (isElementPresent(By.xpath("///h4[@innertext='Manage Accounts']"))) {
      return;
    }
    click(By.linkText("Manage Users"));
  }
}
