package ru.stqa.pft.addressbook.model;

public class ContactData {
  private  int id;
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String address;
  private final String email;

  public void setId(int id) {

    this.id = id;
  }

  public int getId() {

    return id;
  }

  public ContactData(int id, String lastName, String middleName, String firstName, String address, String email) {
    this.id=id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.address = address;
    this.email = email;
  }

  public ContactData( String lastName, String middleName, String firstName, String address, String email) {
    this.id=Integer.MAX_VALUE;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.address = address;
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }
}
