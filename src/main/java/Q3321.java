import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Q3321 {

    public long[] findXSum(int[] nums, int k, int x) {
        final int n = nums.length;
        final long[] ans = new long[n - k + 1];
        final Map<Integer, Integer> counter = new HashMap<>();
        final TreeSet<Tuple> include = new TreeSet<>();
        final TreeSet<Tuple> exclude = new TreeSet<>();

        int left = 0;
        long curr = 0;
        for (int right = 0; right < k - 1; right++) {
            curr += addNumber(nums[right], counter, include, exclude, x);
        }
        for (int right = k - 1; right < n; right++) {
            curr += addNumber(nums[right], counter, include, exclude, x);
            ans[left] = curr;
            curr += removeNumber(nums[left], counter, include, exclude, x);
            left += 1;
        }
        return ans;
    }

    private long addNumber(final int num, final Map<Integer, Integer> counter, final TreeSet<Tuple> include, final TreeSet<Tuple> exclude, final int x) {
        int freq = counter.getOrDefault(num, 0);
        final Tuple old = new Tuple(num, freq);
        long res = removeOldTuple(num, include, exclude, old, freq);

        freq += 1;
        counter.put(num, freq);
        final Tuple tuple = new Tuple(num, freq);
        exclude.add(tuple);
        res += fillInclude(include, exclude, x);
        res += order(include, exclude);
        return res;
    }

    private long order(TreeSet<Tuple> include, TreeSet<Tuple> exclude) {
        long res = 0;
        while (include.size() > 0 && exclude.size() > 0 && include.first().compareTo(exclude.last()) < 0) {
            final Tuple smallest = include.pollFirst();
            res -= smallest.num * smallest.freq;
            exclude.add(smallest);
            final Tuple biggest = exclude.pollLast();
            res += biggest.num * biggest.freq;
            include.add(biggest);
        }
        return res;
    }

    private long fillInclude(TreeSet<Tuple> include, TreeSet<Tuple> exclude, int x) {
        long res = 0;
        while (include.size() < x && exclude.size() > 0) {
            final Tuple biggest = exclude.pollLast();
            include.add(biggest);
            res += biggest.num * biggest.freq;
        }
        return res;
    }

    private long removeOldTuple(long num, TreeSet<Tuple> include, TreeSet<Tuple> exclude, Tuple old, int freq) {
        long res = 0;
        if (include.contains(old)) {
            res -= num * freq;
        }
        include.remove(old);
        exclude.remove(old);
        return res;
    }

    private long removeNumber(final int num, final Map<Integer, Integer> counter, final TreeSet<Tuple> include, final TreeSet<Tuple> exclude, final int x) {
        int freq = counter.get(num);
        final Tuple old = new Tuple(num, freq);
        long res = removeOldTuple(num, include, exclude, old, freq);

        freq -= 1;
        counter.put(num, freq);
        if (freq > 0) {
            final Tuple tuple = new Tuple(num, freq);
            exclude.add(tuple);
        }

        res += fillInclude(include, exclude, x);
        res += order(include, exclude);
        return res;
    }

    private class Tuple implements Comparable<Tuple> {
        long num;
        long freq;

        Tuple(final int number, final int occurrence) {
            num = number;
            freq = occurrence;
        }

        @Override
        public int compareTo(Tuple other) {
            if (freq > other.freq) {
                return 1;
            } else if (freq < other.freq) {
                return -1;
            } else {
                return Long.compare(num, other.num);
            }
        }
    }
}
