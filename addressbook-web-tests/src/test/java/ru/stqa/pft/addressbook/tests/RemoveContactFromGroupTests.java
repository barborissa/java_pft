package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class RemoveContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test 1"));
    }
    app.goTo().home();
    if (app.db().contactsInGroup().size() == 0) {
      if (app.db().contacts().size() == 0) {
        app.contact().create(new ContactData()
                .withFirstname("Altynai").withSurname("Kanatpaeva"), true);
      }
      app.goTo().home();
      ContactData contact = app.db().contacts().iterator().next();
      GroupData group = app.db().groups().iterator().next();
      app.goTo().home();
      app.contact().addContactToGroup(contact, group);
    }
  }

  @Test
  public void testRemoveContactFromGroup() {
    app.goTo().home();
    Contacts contactsInGroups = app.db().contactsInGroup();
    ContactData contactInGroup = contactsInGroups.iterator().next();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.goTo().home();
    app.contact().removeContactFromGroup(contactInGroup, group);
    ContactData removedContactFromGroup = app.db().contactById(contactInGroup.getId());
    Assert.assertFalse(removedContactFromGroup.getGroups().contains(group));
  }
}
