package Falcorny;

import java.util.Arrays;

public class finals {
    public static void main(String[] args) {


    }
    public static String ArrayChallenge(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();

        if (totalSum % 2 != 0) {
            return "-1";
        }

        int halfSum = totalSum / 2;

        boolean[][] dp = new boolean[arr.length + 1][halfSum + 1];
        dp[0][0] = true;

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j <= halfSum; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= arr[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        if (!dp[arr.length][halfSum]) {
            return "-1";
        }

        int[] set1 = new int[arr.length / 2];
        int[] set2 = new int[arr.length / 2];
        int set1Index = 0;
        int set2Index = 0;

        int i = arr.length;
        int currentSum = halfSum;

        while (i > 0 && currentSum > 0) {
            if (currentSum >= arr[i - 1] && dp[i - 1][currentSum - arr[i - 1]]) {
                set1[set1Index++] = arr[i - 1];
                currentSum -= arr[i - 1];
            } else {
                set2[set2Index++] = arr[i - 1];
            }
            i--;
        }


        Arrays.sort(set1, 0, set1Index);
        Arrays.sort(set2, 0, set2Index);

        StringBuilder result = new StringBuilder();
        for (i = 0; i < set1Index; i++) {
            result.append(set1[i]);
            if (i < set1Index - 1 || set2Index > 0) {
                result.append(",");
            }
        }

        for (i = 0; i < set2Index; i++) {
            result.append(set2[i]);
            if (i < set2Index - 1) {
                result.append(",");
            }
        }

         result.toString();
        String challengeToken = "asr8z6wc50";

        StringBuilder answer = new StringBuilder(result + challengeToken);

        for (int ii = 3; ii < answer.length(); ii += 4) {
            answer.setCharAt(ii, '_');
        }

        return answer.toString();
    }
}
