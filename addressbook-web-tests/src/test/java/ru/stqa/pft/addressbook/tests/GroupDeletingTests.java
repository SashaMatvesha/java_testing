package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletingTests extends TestBase {
  @Test
  public void testGroupDeleting() {
    app.getNavigationHelper().gotoGroupPage();
    //int before = app.getGroupHelper().getGroupCount();
    if (!app.getGroupHelper().isThereAnyGroup()){
      app.getGroupHelper().createGroup(new GroupData("Group1","G1","G2"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(before.size()-1);
    Assert.assertEquals(before, after);
  }
}
