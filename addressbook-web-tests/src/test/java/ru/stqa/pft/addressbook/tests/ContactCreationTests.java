package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.initContactCreation();
    app.fillContactForm(new ContactData("Altynai", "Kanatpaeva",
            "Almaty, Kazakhstan", "+77057773931",
            "27", "May"));
    app.submitContactCreation();
    app.returnToHomePage();
  }

}
