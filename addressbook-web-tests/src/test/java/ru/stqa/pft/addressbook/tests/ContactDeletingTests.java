package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletingTests extends TestBase {

  @Test
  public void testContactDeleting() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAnyContact()){
      app.getContactHelper().createContact(new ContactData("Ефросинья","Афанасьевна",null,null,null));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    }
  }

