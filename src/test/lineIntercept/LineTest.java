package test.lineIntercept;

import lineIntercept.LineIntercept;
import lineIntercept.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {
    @Test
    public void SameSegmentVertical(){
        var s1 = new Point(0, 0);
        var e1 = new Point(0,5);
        var s2 = new Point(0, 0);
        var e2 = new Point(0,5);

        var intersection = LineIntercept.calculateInterception(s1, e1, s2, e2);
        assertNotNull(intersection);
        assertEquals(s1, intersection);
    }

    @Test
    public void MidSegmentVertical(){
        var s1 = new Point(0, 0);
        var e1 = new Point(0,5);
        var s2 = new Point(0, 3);
        var e2 = new Point(0,8);
        var intersection = LineIntercept.calculateInterception(s1, e1, s2, e2);
        assertNotNull(intersection);
        assertEquals(s2, intersection);
    }

    @Test
    public void EndSegmentVertical(){
        var s1 = new Point(0, 0);
        var e1 = new Point(0,5);
        var s2 = new Point(0, 5);
        var e2 = new Point(0,8);
        var intersection = LineIntercept.calculateInterception(s1, e1, s2, e2);
        assertNotNull(intersection);
        assertEquals(s2, intersection);
    }

    @Test
    public void EasyIntersection() {
        var s1 = new Point(0, 0);
        var e1 = new Point(3,3);
        var s2 = new Point(3, 0);
        var e2 = new Point(0,3);
        var intersection = LineIntercept.calculateInterception(s1, e1, s2, e2);
        assertNotNull(intersection);
        assertEquals(new Point(1.5,1.5), intersection);
    }

    @Test
    public void EasyFailure() {
        var s1 = new Point(0, 0);
        var e1 = new Point(3,3);
        var s2 = new Point(10, 0);
        var e2 = new Point(0,10);
        var intersection = LineIntercept.calculateInterception(s1, e1, s2, e2);
        assertNull(intersection);
    }
}
