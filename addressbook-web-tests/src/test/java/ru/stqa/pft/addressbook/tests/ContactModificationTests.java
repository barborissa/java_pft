package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Altynai").withSurname("Kanatpaeva").withAddress("Almaty, Kazakhstan")
              .withBday("27").withBmonth("May").withHomePhone("11-3").withMobile("+7 (111)").withWorkPhone("321 3 3")
              .withGroup("Test 1"), true);
    }
    app.goTo().home();
  }

  @Test
  public void testContactModification() throws Exception {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("Altynai").withSurname("Kanatpaeva").withAddress("Almaty, Kazakhstan")
            .withBday("27").withBmonth("May").withHomePhone("11-3").withMobile("+7 (111)").withWorkPhone("321 3 3")
            .withGroup("Test 1");
    app.contact().modify(contact);
    app.goTo().home();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
