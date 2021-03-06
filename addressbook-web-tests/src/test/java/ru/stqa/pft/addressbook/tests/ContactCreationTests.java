package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/pic.jpg");
    ContactData contact = new ContactData()
            .withFirstname("Altynai").withSurname("Kanatpaeva").withAddress("Almaty, Kazakhstan")
            .withBday("27").withBmonth("May").withHomePhone("11-3").withMobile("+7 (111)").withWorkPhone("321 3 3")
            .withPhoto(photo).withGroup("Test 1");
    app.contact().create(contact, true);
    app.goTo().home();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Altynai '").withSurname("Kanatpaeva").withAddress("Almaty, Kazakhstan")
            .withBday("27").withBmonth("May").withHomePhone("11-3").withMobile("+7 (111)").withWorkPhone("321 3 3")
            .withGroup("Test 1");
    app.contact().create(contact, true);
    app.goTo().home();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
