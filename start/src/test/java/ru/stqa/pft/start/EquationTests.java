package ru.stqa.pft.start;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {

  @Test
  public void test0(){
    Equation e = new Equation(1,1,1);
    Assert.assertEquals(e.rootNumber(),0);
  }

  @Test
  public void test1(){
    Equation e = new Equation(1,2,1);
    Assert.assertEquals(e.rootNumber(),1);
  }

  @Test
  public void test2(){
    Equation e = new Equation(1,3,1);
    Assert.assertEquals(e.rootNumber(),2);
  }
  @Test
  public void testA0(){
    Equation e = new Equation(0,3,1);
    Assert.assertEquals(e.rootNumber(),1);
  }

  @Test
  public void  testB0(){
    Equation e = new Equation(0,0,1);
    Assert.assertEquals(e.rootNumber(),0);
  }

  @Test
  public  void  testC0() {
    Equation e = new Equation(0,0,0);
    Assert.assertEquals(e.rootNumber(),-1);
  }
}
