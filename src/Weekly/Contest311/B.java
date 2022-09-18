package Weekly.Contest311;

import java.util.Stack;

public class B {
    public static void main(String[] args) {

    }
    public int longestContinuousSubstring(String s) {
        Stack<Character> stack = new Stack<>();
        int max =0;
        for(char ch : s.toCharArray()){
            if(stack.isEmpty()){
                stack.push(ch);
                max = Math.max(max, stack.size());

            }else if(ch - stack.peek()==1){
                stack.push(ch);
                max = Math.max(max, stack.size());
            }else{
                stack.clear();
                stack.push(ch);
            }
        }
        return max;
    }
}
