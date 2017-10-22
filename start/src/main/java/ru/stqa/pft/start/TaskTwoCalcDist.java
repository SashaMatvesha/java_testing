package ru.stqa.pft.start;

public class TaskTwoCalcDist {
  public static void main(String[] args){
    Point p1 = new Point(0,0);
    Point p2 = new Point(2,2);
    double dist = distance(p1,p2);
    double dist2 = p1.distance(p2);
    System.out.println("Расстояние между точками p1 и p2 равно " + dist);
    System.out.println("Расстояние о точки p1 до точки p2 равно " + dist2);

  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt((p1.abs-p2.abs)*(p1.abs-p2.abs)+(p1.ord-p2.ord)*(p1.ord-p2.ord));
  }
}
