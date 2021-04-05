package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test 1"));
    }
    app.goTo().home();
    if (app.db().contacts().size() == 0 || app.db().contactsInGroup().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Altynai").withSurname("Kanatpaeva")
              .withAddress("Almaty, Kazakhstan\nPanfilov street")
              .withHomePhone("11-3").withMobile("+7 (111)").withWorkPhone("321 3 3")
              .withEmail("a.test@test.com").withEmail2("a.test@test.ru").withEmail3("a.test@test.kz")
              .withBday("27").withBmonth("May").inGroup(new GroupData().withName("Test 1")), true);
    }
  }

  @Test
  public void testAddAnyContactToGroup() {
    app.goTo().home();
    Contacts contacts = app.db().contacts();
    ContactData contact = contacts.iterator().next();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.goTo().home();
    app.contact().addedContactToGroup(contact, group);
    ContactData addedContact = app.db().contactById(contact.getId());
    Assert.assertTrue(addedContact.getGroups().contains(group));
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
