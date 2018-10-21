package test.nextNumber;

import nextNumber.NextNumber;
import org.junit.Test;

import static org.junit.Assert.*;
//Cracking the coding interview, 6th edition, problem 5.4 - Next Number
public class NextNumberTests {
    @Test(expected = IllegalArgumentException.class)
    public void NextZeroFails() {
        NextNumber.next(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NextBigFails() {
        NextNumber.next(Integer.parseInt(
                "1111" + "1111" +
                        "1111" + "1111" +
                        "1111" + "1111" +
                        "1111" + "111", 2));
    }

    @Test
    public void NextSimple() {
        var next = NextNumber.next(1);
        assertEquals("1 -> 10", 2, next);
    }

    @Test
    public void NextComplex() {
        var n = Integer.parseInt("11011001111100", 2);
        var next = NextNumber.next(n);
        var expected = Integer.parseInt("11011010001111", 2);
        assertEquals(expected, next);
    }

    @Test(expected = IllegalArgumentException.class)
    public void PrevZeroFails() {
        NextNumber.prev(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void PrevAllOnesFails() {
        NextNumber.prev(255);
    }

    @Test
    public void PrevSimple() {
        int prev = NextNumber.prev(2);
        assertEquals("10 -> 1", 1, prev);
    }

    @Test
    public void PrevComplex() {
        var n = Integer.parseInt("10011110000011", 2);
        var prev = NextNumber.prev(n);
        var expected = Integer.parseInt("10011101110000", 2);
        assertEquals(expected, prev);
    }
}
