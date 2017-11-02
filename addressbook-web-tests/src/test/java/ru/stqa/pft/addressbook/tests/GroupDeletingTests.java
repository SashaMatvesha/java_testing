package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletingTests extends TestBase {
  @Test
  public void testGroupDeleting() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAnyGroup()){
      app.getGroupHelper().creatGroup(new GroupData("Group1","G1","G2"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }
}
