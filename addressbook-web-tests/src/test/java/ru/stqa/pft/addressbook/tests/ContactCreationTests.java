package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().initUserCreation();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "I.", "Ivanov", "adress", "e-mail"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
  }

}
