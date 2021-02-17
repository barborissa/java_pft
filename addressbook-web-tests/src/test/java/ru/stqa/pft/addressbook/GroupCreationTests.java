package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Test 1", "Test 2", "Test 3"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
