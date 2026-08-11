package step2026_08_11;

public class Q1 {

    public long[] Q1(int[] arr, long l, long r) {
        int n = arr.length;

        long[] lengthPrefix = new long[n + 1];
        long[] sumPrefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            lengthPrefix[i + 1] = lengthPrefix[i] + arr[i];
            sumPrefix[i + 1]
                    = sumPrefix[i] + (long) arr[i] * arr[i];
        }

        long windowLength = r - l + 1;

        long targetSum
                = prefixSum(arr, lengthPrefix, sumPrefix, r)
                - prefixSum(arr, lengthPrefix, sumPrefix, l - 1);

        long count
                = countWindows(
                arr,
                lengthPrefix,
                sumPrefix,
                windowLength,
                targetSum
        );

        return new long[]{targetSum, count};
    }

    private long countWindows(
            int[] arr,
            long[] lengthPrefix,
            long[] sumPrefix,
            long windowLength,
            long targetSum
    ) {
        int n = arr.length;

        long totalLength = lengthPrefix[n];
        long maxStart = totalLength - windowLength;

        long currentSum
                = prefixSum(arr, lengthPrefix, sumPrefix, windowLength);

        if (maxStart == 0) {
            return currentSum == targetSum ? 1 : 0;
        }

        long start = 0;
        long count = 0;

        int leavingRun = 0;
        int enteringRun = findRun(lengthPrefix, windowLength);

        while (start < maxStart) {
            while (start >= lengthPrefix[leavingRun + 1]) {
                leavingRun++;
            }

            while (start + windowLength
                    >= lengthPrefix[enteringRun + 1]) {
                enteringRun++;
            }

            long leavingBoundary
                    = lengthPrefix[leavingRun + 1];

            long enteringBoundary
                    = lengthPrefix[enteringRun + 1] - windowLength;

            long nextStart
                    = Math.min(
                    maxStart,
                    Math.min(leavingBoundary, enteringBoundary)
            );

            long steps = nextStart - start;

            long delta
                    = (long) arr[enteringRun] - arr[leavingRun];

            count += countMatches(
                    currentSum,
                    delta,
                    steps,
                    targetSum
            );

            currentSum += steps * delta;
            start = nextStart;
        }

        if (currentSum == targetSum) {
            count++;
        }

        return count;
    }

    private long countMatches(
            long currentSum,
            long delta,
            long steps,
            long targetSum
    ) {
        if (delta == 0) {
            return currentSum == targetSum ? steps : 0;
        }

        long difference = targetSum - currentSum;

        if (difference % delta != 0) {
            return 0;
        }

        long moveCount = difference / delta;

        if (0 <= moveCount && moveCount < steps) {
            return 1;
        }

        return 0;
    }

    private long prefixSum(
            int[] arr,
            long[] lengthPrefix,
            long[] sumPrefix,
            long elementCount
    ) {
        if (elementCount == 0) {
            return 0;
        }

        int run = findRun(lengthPrefix, elementCount - 1);

        long countInCurrentRun
                = elementCount - lengthPrefix[run];

        return sumPrefix[run]
                + countInCurrentRun * arr[run];
    }

    private int findRun(
            long[] lengthPrefix,
            long position
    ) {
        int left = 0;
        int right = lengthPrefix.length - 2;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (position < lengthPrefix[mid]) {
                right = mid - 1;
            } else if (position >= lengthPrefix[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}