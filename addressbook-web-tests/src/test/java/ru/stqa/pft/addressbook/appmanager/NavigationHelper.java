package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

  NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }
  public void goToHomePage() {
    click(By.linkText("home"));
  }
  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }
}
