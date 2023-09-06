package Falcorny;

import java.util.ArrayList;
import java.util.List;

public class DivideArray {

    static int accumulate(int arr[], int first, int last) {
        int init = 0;
        for (int i = first; i < last; i++) {
            init = init + arr[i];
        }
        return init;
    }

    static void printSets(int arr[], int half[], int start, int end, int index, int n, int sum, List<List<Integer>> result) {
        if (index == n / 2) {
            int curr_sum = accumulate(half, 0, n / 2);
            if (curr_sum + curr_sum == sum) {
                List<Integer> set1 = new ArrayList<>();
                List<Integer> set2 = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if (i < n / 2 && half[i] != 0) {
                        set1.add(half[i]);
                    } else if (i >= n / 2 && arr[i] != 0) {
                        set2.add(arr[i]);
                    }
                }
                result.add(set1);
                result.add(set2);
            }
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= n / 2 - index; i++) {
            half[index] = arr[i];
            printSets(arr, half, i + 1, end, index + 1, n, sum, result);
        }
    }

    public static List<List<Integer>> findSets(int arr[]) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;

        // If size of array is not even or sum of array is not even, return empty list
        int sum = accumulate(arr, 0, n);
        if (n % 2 != 0 || sum % 2 != 0) {
            return result;
        }

        int half[] = new int[n / 2];
        printSets(arr, half, 0, n - 1, 0, n, sum, result);
        return result;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 4, 4, 5, 6 };
        List<List<Integer>> sets = findSets(arr);

        if (sets.isEmpty()) {
            System.out.println("No sets found.");
        } else {
            for (List<Integer> set : sets) {
                System.out.println(set);
            }
        }
    }
}
