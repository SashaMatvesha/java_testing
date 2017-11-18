package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size()==0){
      app.contact().create(new ContactData().withName("test1").withEmail("1@.ru").withEmail1("2@.com").withEmail2("3@.com"));
      app.goTo().homePage();
    }
  }
  @Test
  public void testEmail(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(MergeEmails(contactInfoFormEditForm)));
  }

  public String MergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail1(),contact.getEmail2())
            .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }

  /*public static String cleaned(String number) {
    return number.replaceAll("\\s","").replaceAll("[-()]","");
  }*/
}
