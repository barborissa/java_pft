package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {


  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, IOException, MessagingException {
    HttpSession session = app.newSession();
    app.session().login("administrator", "root");
    assertTrue(session.login("administrator", "root"));
    app.goTo().managePage();
    app.goTo().manageUserPage();
    Users users = app.db().users();
    UserData user = users.iterator().next();
    app.userEditor().resetPassword(user);
    app.session().logout();

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());

    String newPassword = "newpass";
    app.userEditor().finishPasswordReset(confirmationLink, user.getRealname(), newPassword);
    app.session().login(user.getUsername(), newPassword);
    assertTrue(app.newSession().login(user.getUsername(), newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
