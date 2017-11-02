package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAnyContact()){
      app.getContactHelper().createContact(new ContactData("Ефросинья","Афанасьевна","Павов","СССР","ПочтаРоссии"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().modificationContact();
    app.getContactHelper().fillContactForm(new ContactData("Павел", "И.", "Павов","СССР","ПочтаРоссии"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
