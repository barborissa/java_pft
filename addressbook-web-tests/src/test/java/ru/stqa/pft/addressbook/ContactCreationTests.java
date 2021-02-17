package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("Altynai", "Kanatpaeva",
            "Almaty, Kazakhstan", "+77057773931",
            "27", "May"));
    submitContactCreation();
    returnToHomePage();
  }

}
