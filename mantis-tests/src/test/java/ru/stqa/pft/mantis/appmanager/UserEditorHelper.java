package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class UserEditorHelper extends HelperBase {

  public UserEditorHelper(ApplicationManager app) {
    super(app);
  }

  public void initUserModificationById(UserData user) {
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", user.getId())));
  }

  public void reset() {
    click(By.xpath("//input[@value='Reset Password']"));
  }

  public void resetPassword(UserData user) {
    initUserModificationById(user);
    reset();
  }

  public void finishPasswordReset(String confirmationLink, String realname, String password) {
    wd.get(confirmationLink);
    type(By.name("realname"), realname);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//button[@type='submit']"));
  }
}
