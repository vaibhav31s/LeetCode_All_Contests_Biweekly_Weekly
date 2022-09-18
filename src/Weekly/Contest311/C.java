package Weekly.Contest311;

import java.util.HashMap;
import java.util.Stack;

public class C {
    public static void main(String[] args) {

    }
    class Solution {
        public TreeNode reverseOddLevels(TreeNode root) {
            HashMap<Integer,Stack<Integer>> map = new HashMap<>();
            add(root, map, 0);
            replace(root, map, 0);
            return root;

        }
        void replace(TreeNode root,  HashMap<Integer,Stack<Integer>> map , int height){
            if(root == null) return;

            if(height%2 == 1)
                root.val = map.get(height).pop();
            replace(root.left, map, height+1);
            replace(root.right, map, height+1);

        }
        void add(TreeNode root, HashMap<Integer,Stack<Integer>> map , int height){
            if(root == null) return;

            map.putIfAbsent(height, new Stack<>());
            map.get(height).add(root.val);
            add(root.left, map, height+1);
            add(root.right, map, height+1);


        }
    }

}

   class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
        this.right = right;
      }
 }

