package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

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
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    modificationContactById(contact.getId());
    fillContactForm(contact);
    contactCache = null;
    submitContactModification();
    returnToHomePage();
  }

  public void modificationContactById(int id) {
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
    contactCache = null;
    returnToHomePage();
  }

  public boolean isThereAnyContact() {
    return isElementExist(By.name("selected[]"));
  }

  public int count(){
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement e:elements){
      List<WebElement> name = e.findElements(By.tagName("td")); // лист с объектами с тэгом td
      String lastName = name.get(1).getText();
      String firstName = name.get(2).getText();
      String allPhones = name.get(5).getText();
      String address = name.get(3).getText();
      String allEmails = name.get(4).getText();
      int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withLastName(lastName).withName(firstName)
              .withAllNumbers(allPhones).withAddress(address).withAllEmails(allEmails);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFormEditForm(ContactData contact) {
    modificationContactById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email1 = wd.findElement(By.name("email2")).getAttribute("value");
    String email2 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return  new ContactData().withId(contact.getId()).withName(firstName).withLastName(lastName)
            .withHomeNumber(home).withMobileNumber(mobile).withWorkNumber(work).withAddress(address)
            .withEmail(email).withEmail1(email1).withEmail2(email2);
  }

  public void returnToHomePage() {
    if (isElementExist(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

}
