package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.interactions.SourceType;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import java.sql.*;

public class DbConnection {

  @Test
  public void testDbConnection() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select * from Group_List");
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(groups);

    } catch (SQLException ex) {
      System.out.println("SQLException:" + ex.getMessage());
      System.out.println("SQLState:" + ex.getSQLState());
      System.out.println("VendorError:" + ex.getErrorCode());
    }
  }
}
