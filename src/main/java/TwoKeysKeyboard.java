import java.util.HashMap;
import java.util.Map;

public class TwoKeysKeyboard {
    private final static int MAX_VAL = 1_000;

    public int minSteps(int n) {
        return memoization(n, 1, 0, new HashMap<>());
    }

    private int memoization(final int n, final int characters, final int buffer,
                            final Map<Integer, Map<Integer, Integer>> memo) {
        if (characters > n) {
            return MAX_VAL;
        } else if (characters == n) {
            return 0;
        }
        if (memo.containsKey(characters) && memo.get(characters).containsKey(buffer)) {
            final Map<Integer, Integer> charMemo = memo.get(characters);
            return charMemo.get(buffer);
        }
        int res;
        if (buffer == 0) {
            res = 2 + memoization(n, 2 * characters, characters, memo);
        } else {
            res = Math.min(1 + memoization(n, characters + buffer, buffer, memo),
                    2 + memoization(n, 2 * characters, characters, memo));
        }
        final Map<Integer, Integer> charMemo = memo.getOrDefault(characters, new HashMap<>());
        charMemo.put(buffer, res);
        memo.put(characters, charMemo);
        return res;
    }

    public int dynamicProgramming(final int n) {
        int[][] dp = new int[2 * n + 1][2 * n + 1];
        for (int i = 2 * n; i >= 0; i--) {
            if (i == n)
                continue;
            for (int j = 2 * n; j >= 0; j--) {
                if (i > n || j >= i) {
                    dp[i][j] = MAX_VAL;
                } else if (j == 0){
                    dp[i][j] = 2 + dp[2 * i][i];
                } else {
                    dp[i][j] = Math.min(2 + dp[2 * i][i], 1 + dp[i + j][j]);
                }
            }
        }

        return dp[1][0];
    }
}
