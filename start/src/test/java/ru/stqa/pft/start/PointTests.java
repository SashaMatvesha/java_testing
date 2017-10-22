package ru.stqa.pft.start;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDist() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(2,0);
    Assert.assertEquals(p1.distance(p2),2.0);
  }

  @Test
  public void testDist1() {
    Point p1 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p1),0.0);
  }

  @Test
  public void testDist2() {
    Point p1 = new Point(-1, -1);
    Point p2 = new Point(-2, -2);
    Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
  }

  @Test
  public void testDist3() {
    Point p1 = new Point(-1, 0);
    Point p2 = new Point(-2, 0);
    Assert.assertEquals(p1.distance(p2), 1.0);
  }
}




