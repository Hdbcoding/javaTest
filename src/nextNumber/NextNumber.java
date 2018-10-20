package nextNumber;

public class NextNumber {
    public static int next(int n){
        System.out.println("n: " + Integer.toString(n, 2));
        int c = n;
        int c0 = 0;
        int c1 = 0;

        //while the rightmost bit is 0, shift right
        //effectively, get the index of the leftmost 0 in the first group of 0s
        while ((c & 1) == 0 && c != 0){
            c0++;
            c >>= 1;
        }

        //while the rightmost bit is 1, shift right
        //effectively, get the leftmost 1 in the first group of 1s
        while ((c & 1) == 1){
            c1++;
            c >>= 1;
        }

        if (c0 + c1 == 31) throw new IllegalArgumentException("Next biggest number will cause overflow");
        if (c0 + c1 == 0) throw new IllegalArgumentException("No bigger number has zero 1s in its binary representation");

        int next = n + (1 << c0) + (1 << (c1 - 1)) - 1;

        System.out.println("c0: " + c0);
        System.out.println("1 << c0: " + Integer.toString(1 << c0, 2));
        System.out.println("n + that: " + Integer.toString(n + (1 << c0), 2));
        System.out.println("c1: " + c1);
        System.out.println("(1 << (c1 - 1)) - 1: " + Integer.toString((1 << (c1 - 1)) - 1, 2));

        System.out.println("next: " + Integer.toString(next, 2));

        return next;
    }

    public static int prev(int n){
        System.out.println("n: " + Integer.toString(n, 2));
        int c = n;
        int c0 = 0;
        int c1 = 0;

        while ((c & 1) == 1){
            c1++;
            c >>=1;
        }

        if (c == 0) throw new IllegalArgumentException("No smaller number has the same number of 1s");

        while ((c & 1) == 0 && c!=0){
            c0++;
            c>>=1;
        }

        int prev = n - (1 << c1) - (1 << (c0 - 1)) + 1;

        System.out.println("c1: " + c1);
        System.out.println("1 << c1: " + Integer.toString(1 << c1, 2));
        System.out.println("n - that: " + Integer.toString(n - (1 << c1), 2));
        System.out.println("c0: " + c0);
        System.out.println("(1 << (c0 - 1)) - 1: " + Integer.toString((1 << (c0 - 1)) - 1, 2));

        System.out.println("prev: " + Integer.toString(prev, 2));

        return prev;
    }
}
