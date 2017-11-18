package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstName());
    type(By.name("middlename"),contactData.getMiddleName());
    type(By.name("lastname"),contactData.getLastName());
    type(By.name("address"),contactData.getAddress());
    type(By.name("email"),contactData.getEmail());
  }

  public void initUserCreation() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id +  "']")).click();
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
  }

  public void modify(ContactData contact) {
    modificationContact(contact.getId());
    fillContactForm(contact);
    submitContactModification();
  }

  public void modificationContact(int id) {
    click(By.xpath("//td[@class='center']/a[@href='edit.php?id=" + id +"']"));
    //tr[@class='odd']
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    initUserCreation();
    fillContactForm(contact);
    submitContactCreation();
  }

  public boolean isThereAnyContact() {
    return isElementExist(By.name("selected[]"));
  }

  public int getContactCount(){
    return wd.findElements(By.name("selected[]")).size();
  }


  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement e:elements){
      List<WebElement> name = e.findElements(By.tagName("td")); // лист с объектами с тэгом td
            String lastName = name.get(1).getText();
      String firstName = name.get(2).getText();
      int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withLastName(lastName).withName(firstName);
      contacts.add(contact);
    }
    return contacts;
  }
}
