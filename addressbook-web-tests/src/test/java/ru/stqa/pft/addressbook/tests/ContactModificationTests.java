package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAnyContact()){
      app.getContactHelper().createContact(new ContactData("Ефросинья","Афанасьевна","Павов","СССР","ПочтаРоссии"));
      app.getNavigationHelper().returnToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    //app.getContactHelper().selectContact(before.get(before.size()-1).getId());
    app.getContactHelper().modificationContact(before.get(before.size()-1).getId());
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Павов", "И.", "Семен","СССР","ПочтаРоссии");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
