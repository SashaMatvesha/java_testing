package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

  @BeforeMethod()
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withName("Ефросинья").withMiddleName("У").withLastName("Иванова"));
    }
    app.goTo().groupPage();
    if (app.db().groups().size()==0){
      app.group().create(new GroupData().withName("test1"));
      app.goTo().homePage();
    }
  }


  @Test (dataProvider = "validContacts")
    public void testContactModification(ContactData contact) {
      Contacts allContacts = app.db().contacts();
      Groups allGroups = app.db().groups();
      ContactData modifiedContact = allContacts.iterator().next();
      GroupData addedGroup = allGroups.iterator().next();
      Groups beforeGroups = modifiedContact.getGroups();

      if(modifiedContact.getGroups().size()!= app.db().groups().size())
      {
        for(GroupData g:modifiedContact.getGroups()) {
          if (!modifiedContact.getGroups().contains(g)) {
            addedGroup = g;
            break;
          }
        }
      }else {
        app.group().create(addedGroup.withName("AddedGroup"));
      }
    System.out.println(addedGroup);
      app.contact().fillContactGroups(modifiedContact,addedGroup);
      Groups afterGroups = modifiedContact.getGroups();
    System.out.println("allgroups after added"+afterGroups);
      assertThat(beforeGroups.withAdded(addedGroup).size(),equalTo(afterGroups.size()));
      assertThat(beforeGroups.withAdded(addedGroup),equalTo(afterGroups));
    }
}
