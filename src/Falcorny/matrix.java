package Falcorny;

public class matrix {
    public static void main(String[] args) {

        String[] str =new String[] {"1011", "0011", "0111", "1111"};
        char[][] matrix = new char[str.length][str[0].length()];
        for(int i = 0; i < str.length; i++) {
            matrix[i] = str[i].toCharArray();
        }

        System.out.println(maximalRectangle());

//
//        String[] answer = new String[str.length];
//        System.out.println(String.valueOf(matrix[0]));
//        for(int i = 0; i < answer.length; i++) {
//            answer[i] = matrix[i].
//        }

    }

    public static String maximalRectangle() {
        String[] str = new String[] {"101", "111", "001"};
        char[][] matrix = new char[str.length][str[0].length()];
        for(int i = 0; i < str.length; i++) {
            matrix[i] = str[i].toCharArray();
        }
        if (matrix.length == 0) return "0";
        int[] line = new int[matrix[0].length];
        int maxArea = 0, area = 0;
        int right = 1, left = 1;
        boolean[] isProcessed = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    isProcessed[j] = false;
                    line[j]++;
                } else {
                    isProcessed[j] = true;
                    line[j] = 0;
                }
            }
            for (int j = 0; j < line.length; j++) {
                if (isProcessed[j]) continue;
                left = right = 1;
                while (right + j < line.length && line[right + j] >= line[j]) {
                    if (line[right + j] == line[j]) isProcessed[j] = true;
                    right++;
                }
                while (j - left >= 0 && line[j - left] >= line[j]) {
                    left++;
                }
                area = (left + right - 1) * line[j];
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return String.valueOf(maxArea);
    }
}
