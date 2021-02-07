package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point p = new Point(2,3,4,5);

        System.out.println("Расстояние на плоскости между точками " +
                "Point1 " + "(" + p.x1 + "," + p.y1 + ")" + " и Point2 " + "(" + p.x2 + "," + p.y2 + ")" +
                " = " + distance(p));
    }

    public static double distance(Point p) {
        return  Math.sqrt((p.y2 - p.y1) * (p.y2 - p.y1) + (p.x2 - p.x1) *  (p.x2 - p.x1));
    }
}
