package Biweekly.Contest87;

import java.util.Arrays;

public class C {
    public static void main(String[] args) {
        int[] nums = {1,2};
        int[] answer = new int[nums.length];
        int max = 0;
        for(int x : nums){
          max = Math.max(max, x);
        }
        for(int i = 0; i < nums.length; i++){
            int count = 1;
            int xor = nums[i];
            for(int j = i+1; j < nums.length; j++){
                count++;
                xor |= nums[j];
                if(xor == max) break;
            }
            answer[i] =  count;
        }
        System.out.println(2 | 1);

        System.out.println(Arrays.toString(answer));
    }
}
