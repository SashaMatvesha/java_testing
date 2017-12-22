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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteContactFromGroup extends TestBase{

  @BeforeMethod()
  public void ensurePreconditions() {
    if (app.db().contacts().size()==0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withName("Ефросинья").withMiddleName("У").withLastName("Иванова"));
    }
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
      app.goTo().homePage();
    }
    for (ContactData c : app.db().contacts()) {
      if (c.getGroups().size() == 0) {
        app.contact().fillContactGroups(c, app.db().groups().iterator().next());
      }
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    Groups allGroups = app.db().groups();
    GroupData fromGroup = allGroups.iterator().next();
    Contacts contactsInGroupBefore = fromGroup.getContacts();
    ContactData deletedContact = contactsInGroupBefore.iterator().next();
    app.group().deleteContactFromGroup(fromGroup, deletedContact);
    Contacts contactsInGroupAfter = fromGroup.getContacts();
    assertThat(contactsInGroupBefore.size(), equalTo(contactsInGroupAfter.withAdded(deletedContact).size()));
    assertThat(contactsInGroupBefore, equalTo(contactsInGroupAfter.withAdded(deletedContact)));
  }
}
