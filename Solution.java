class Solution {
    public static void main(String[] args) {
        int[] A = { 4, 2, 2, 3, 1, 4, 7, 8, 6, 9 };
        Solution solver = new Solution();
        int result = solver.solution(A);
        System.out.println(result);
    }

    public int solution(int[] A) {
        int n = A.length;
        if (n == 0) {
            return -1;
        }

        int[] rightMin = new int[n];
        int currentMin = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            currentMin = Math.min(currentMin, A[i]);
            rightMin[i] = currentMin;
        }

        int maxLeft = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxLeft = Math.max(maxLeft, A[i]);
            if (maxLeft <= A[i] && A[i] <= rightMin[i]) {
                return i;
            }
        }

        return -1;
    }
}