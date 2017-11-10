package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletingTests extends TestBase {

  @Test
  public void testContactDeleting() {
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCount();
    if (before==0){
      app.getContactHelper().createContact(new ContactData("Ефросинья","Афанасьевна",null,null,null));
      app.getNavigationHelper().returnToHomePage();
      before = app.getContactHelper().getContactCount();
    }
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before-1);
    }
  }

