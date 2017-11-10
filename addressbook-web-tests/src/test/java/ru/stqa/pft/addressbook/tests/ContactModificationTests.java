package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAnyContact()){
      app.getContactHelper().createContact(new ContactData("Ефросинья","Афанасьевна","Павов","СССР","ПочтаРоссии"));
      app.getNavigationHelper().returnToHomePage();
      before = app.getContactHelper().getContactCount();
    }
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().modificationContact();
    app.getContactHelper().fillContactForm(new ContactData("Павел", "И.", "Павов","СССР","ПочтаРоссии"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
