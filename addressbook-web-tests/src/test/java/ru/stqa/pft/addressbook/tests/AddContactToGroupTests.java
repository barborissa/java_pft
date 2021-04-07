package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class AddContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test 1"));
    }
    app.goTo().home();
    if (app.db().contacts().size() == 0 || app.db().contactsWithoutGroup().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Altynai").withSurname("Kanatpaeva"), true);
      app.goTo().home();
    }
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().home();
    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();
    app.goTo().home();
    app.contact().addContactToGroup(contact, group);
    ContactData addedContact = app.db().contactById(contact.getId());
    Assert.assertTrue(addedContact.getGroups().contains(group));
  }
}
