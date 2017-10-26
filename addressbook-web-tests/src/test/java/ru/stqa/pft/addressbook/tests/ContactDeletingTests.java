package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletingTests extends TestBase {

  @Test
  public void testContactDeleting() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    }
  }

