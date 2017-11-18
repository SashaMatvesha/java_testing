package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

  NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementExist(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementExist(By.name("new"))){
      return;
    }
    click(By.linkText("groups"));
  }

  public void goToHomePage() {
    if (isElementExist(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }
  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }
  public void returnToHomePage() {
    click(By.linkText("home"));
  }
}
