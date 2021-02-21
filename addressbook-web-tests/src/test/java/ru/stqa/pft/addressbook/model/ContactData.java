package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String surname;
  private final String address;
  private final String mobile;
  private final String bday;
  private final String bmonth;
  private String group;

  public ContactData(String firstname, String surname,
                     String address, String mobile,
                     String bday, String bmonth,
                     String group) {
    this.firstname = firstname;
    this.surname = surname;
    this.address = address;
    this.mobile = mobile;
    this.bday = bday;
    this.bmonth = bmonth;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getSurname() {
    return surname;
  }

  public String getAddress() {
    return address;
  }

  public String getMobile() {
    return mobile;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getGroup() {
    return group;
  }

}
