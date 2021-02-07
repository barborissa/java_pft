package ru.stqa.pft.sandbox;

public class Point {
    public static void main(String[] args) {

        double x1 = 3;
        double y1 = 8;
        double x2 = 4;
        double y2 = 9;
        System.out.println("Расстояние на плоскости между точками " +
                "Point1 " + "(" + x1 + "," + y1 + ")" + " и Point2 " + "(" + x2 + "," + y2 + ")" +
                " = " + distance(x1, y1, x2, y2));
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return  Math.sqrt((y2 - y1) *  (y2 - y1) + (x2 - x1) *  (x2 - x1));
    }
}
