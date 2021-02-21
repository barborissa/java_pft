package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() throws Exception {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Altynai", "Kanatpaeva",
            "Almaty, Kazakhstan", "+77057773931",
            "27", "May", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHome();
  }
}
