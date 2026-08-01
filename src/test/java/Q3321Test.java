import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Q3321Test {
    private final Q3321 solution = new Q3321();

    @Test
    void case1() {
        final long[] expected = new long[]{6, 10, 12};
        final long[] actual = solution.findXSum(new int[]{1, 1, 2, 2, 3, 4, 2, 3}, 6, 2);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void case2() {
        final long[] expected = new long[]{11, 15, 15, 15, 12};
        final long[] actual = solution.findXSum(new int[]{3, 8, 7, 8, 7, 5}, 2, 2);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void case3() {
        final long[] expected = new long[]{6_000_000_000l};
        final long[] actual = solution.findXSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000}, 6, 1);
        Assertions.assertArrayEquals(expected, actual);
    }
}
