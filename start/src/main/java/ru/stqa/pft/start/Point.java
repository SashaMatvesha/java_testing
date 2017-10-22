package ru.stqa.pft.start;

public class Point {
  public double abs;
  public double ord;

  public  Point (double abs, double ord) {
    this.abs = abs;
    this.ord = ord;
  }

    public  double distance(Point p2){
      //Point p1 = new Point(abs,ord);
      return Math.sqrt((this.abs-p2.abs)*(this.abs-p2.abs)+(this.ord-p2.ord)*(this.ord-p2.ord));
    }
  }

