import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class q1277Test {
    @Test
    void case1() {
        int[][] matrix = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        q1277CountSquareSubmatricesWithAllOnes testClass = new q1277CountSquareSubmatricesWithAllOnes();
        Assertions.assertEquals(15, testClass.countSquares(matrix));
    }
}
