package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanceNew() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,0);
        assert (p1.distance(p2)) == 3.0;
    }

    @Test
    public void testDistance() {
        Point p1 = new Point(-3,3);
        Point p2 = new Point(4,-2);
        Assert.assertEquals(p1.distance(p2),8.602325267042627);
    }
}
