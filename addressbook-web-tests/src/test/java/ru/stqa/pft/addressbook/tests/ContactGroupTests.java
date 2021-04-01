package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
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
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Altynai").withSurname("Kanatpaeva")
              .withAddress("Almaty, Kazakhstan\nPanfilov street")
              .withHomePhone("11-3").withMobile("+7 (111)").withWorkPhone("321 3 3")
              .withEmail("a.test@test.com").withEmail2("a.test@test.ru").withEmail3("a.test@test.kz")
              .withBday("27").withBmonth("May"), true);
    }
    app.goTo().home();
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().home();
    ContactData before = app.db().contactWithoutGroup();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.goTo().home();
    app.contact().addContactToGroup(before, group);
    ContactData after = app.db().contactById(before.getId());
    Assert.assertTrue(after.getGroups().contains(group));
  }

  @Test
  public void testRemoveContactFromGroup() {
    app.goTo().home();
    ContactData before = app.db().contactWithoutGroup();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.goTo().home();
    app.contact().removeContactFromGroup(before, group);
    ContactData after = app.db().contactById(before.getId());
    Assert.assertFalse(after.getGroups().contains(group));
  }

}
