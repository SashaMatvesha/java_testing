package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size()==0){
      app.contact().create(new ContactData().withName("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
    assertThat(contact.getAllNumbers(), equalTo(MergeNumbers(contactInfoFormEditForm)));
  }

  public String MergeNumbers(ContactData contact) {
    return Arrays.asList(contact.getHomeNumber(), contact.getMobileNumber(),contact.getWorkNumber())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String number) {
    return number.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
