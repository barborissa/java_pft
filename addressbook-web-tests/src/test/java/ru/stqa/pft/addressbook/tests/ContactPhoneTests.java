package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Altynai").withSurname("Kanatpaeva").withAddress("Almaty, Kazakhstan")
              .withBday("27").withBmonth("May").withMobile("123").withHomePhone("123").withWorkPhone("321")
              .withGroup("Test 1"), true);
    }
    app.goTo().home();
  }

  @Test
  public void testContactPhones() {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }
}
