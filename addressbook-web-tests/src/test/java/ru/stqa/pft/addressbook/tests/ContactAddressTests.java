package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size()==0){
      app.contact().create(new ContactData().withName("test1").withAddress("Россия г.Норильск ул. Ленина"));
      app.goTo().homePage();
    }
  }
  @Test
  public void testAddress(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFormEditForm.getAddress()));
  }

 /* public String MergeNumbers(ContactData contact) {
    return Arrays.asList(contact.getHomeNumber(), contact.getMobileNumber(),contact.getWorkNumber())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String number) {
    return number.replaceAll("\\s","").replaceAll("[-()]","");
  }*/
}
