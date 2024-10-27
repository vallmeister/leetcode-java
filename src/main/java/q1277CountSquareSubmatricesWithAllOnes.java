public class q1277CountSquareSubmatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                } else if (i == 0 || j == 0) {
                    ans += 1;
                } else {
                    matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1])) + 1;
                    ans += matrix[i][j];
                }
            }
        }
        return ans;
    }
}
