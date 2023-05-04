package Weekly._338;

import java.util.*;

class Solution {
    private static class Pair<K, V> implements Map.Entry<K, V>
    {
        private K key;
        private V value;

        public Pair(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        public K getKey()
        {
            return this.key;
        }

        public V getValue()
        {
            return this.value;
        }

        public K setKey(K key)
        {
            return this.key = key;
        }

        public V setValue(V value)
        {
            return this.value = value;
        }
    }
    public static void main(String[] args) {
        long ans = countOperationsToEmptyArray(new int[]{3,4,-1});
        System.out.println(ans  );
    }
    public static long countOperationsToEmptyArray(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        Deque<Integer> dq = new LinkedList<>();
        AVLTreePBDS avlTreePBDS = new AVLTreePBDS(false);
        for(int x : nums) {dq.add(x); avlTreePBDS.add(x);}

        long answer =0;

        for(int i = 0; i < nums.length; i++){
            int curElem = nums[i];
//            int j = new ArrayList<>(set).indexOf(curElem);
            // System.out.println(j);

            set.remove(curElem);
        }

return answer;

    }

    public static int search(int[] nums, int target,int start,int end) {

        while(start <= end){
            int mid = (start + end)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target){
                end = mid -1;
            }else{
                start = mid +1;
            }
        }
        return -1;
    }
    public static int firstCompleteIndex(int[] arr, int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        HashMap<Integer,Integer> row = new HashMap<>(); // if row == i yes
        HashMap<Integer,Integer> col = new HashMap<>(); // if col == j  yes

        HashMap<Integer,Pair<Integer,Integer>> pair = new HashMap<>();
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < m; j++){
                pair.put(mat[i][j], new Pair<>(i,j));
            }
        }

        for(int i = 0; i < n; i++){
            Pair<Integer,Integer> p = pair.get(arr[i]);
            int x = p.getKey();
            int y = p.getValue();
            row.put(x, row.getOrDefault(x, 0)+1);
            col.put(y, col.getOrDefault(y, 0) + 1);

            if(row.get(x) == n || col.get(y) == m) return i;
        }
        return -1;
    }
    private static class AVLTreePBDS {
        private Node root;
        private boolean multi;

        AVLTreePBDS(boolean multi) {
            this.root = null;
            this.multi = multi;
        }

        int size() {
            return size(root);
        }

        boolean isEmpty() {
            return size(root) == 0;
        }

        boolean contains(int key) {
            return contains(root, key);
        }

        void add(int key) {
            root = add(root, key);
        }

        void remove(int key) {
            root = remove(root, key);
        }

        Integer first() {
            Node min = findMin(root);
            return min != null ? min.key : null;
        }

        Integer last() {
            Node max = findMax(root);
            return max != null ? max.key : null;
        }

        Integer poolFirst() {
            Node min = findMin(root);
            if (min != null) {
                remove(min.key);
                return min.key;
            }
            return null;
        }

        Integer poolLast() {
            Node max = findMax(root);
            if (max != null) {
                remove(max.key);
                return max.key;
            }
            return null;
        }

        // min >= key
        Integer ceiling(int key) {
            return contains(key) ? key : higher(key);
        }

        // max <= key
        Integer floor(int key) {
            return contains(key) ? key : lower(key);
        }

        // min > key
        Integer higher(int key) {
            Node min = higher(root, key);
            return min == null ? null : min.key;
        }

        private Node higher(Node cur, int key) {
            if (cur == null)
                return null;

            if (cur.key <= key)
                return higher(cur.right, key);

            // cur.key > key
            Node left = higher(cur.left, key);
            return left == null ? cur : left;
        }

        // max < key
        Integer lower(int key) {
            Node max = lower(root, key);
            return max == null ? null : max.key;
        }

        private Node lower(Node cur, int key) {
            if (cur == null)
                return null;

            if (cur.key >= key)
                return lower(cur.left, key);

            // cur.key < key
            Node right = lower(cur.right, key);
            return right == null ? cur : right;
        }

        private class Node {
            int key, height, size;
            Node left, right;

            Node(int key) {
                this.key = key;
                height = size = 1;
                left = right = null;
            }

            private void preOrder(Node root, StringBuilder ans) {
                if (root == null) return;
                if (root.left != null) preOrder(root.left, ans);
                ans.append(root.key + ",");
                if (root.right != null) preOrder(root.right, ans);
            }

            public String toString() {
                StringBuilder res = new StringBuilder();
                preOrder(root, res);
                return "[" + String.valueOf(res.substring(0, res.length() - 1)) + "]";
            }
        }

        private int height(Node cur) {
            return cur == null ? 0 : cur.height;
        }

        private int balanceFactor(Node cur) {
            return height(cur.right) - height(cur.left);
        }

        private int size(Node cur) {
            return cur == null ? 0 : cur.size;
        }

        // fixVertex
        private void fixHeightAndSize(Node cur) {
            cur.height = Math.max(height(cur.left), height(cur.right)) + 1;
            cur.size = size(cur.left) + size(cur.right) + 1;
        }

        private Node rotateRight(Node cur) {
            Node prevLeft = cur.left;
            cur.left = prevLeft.right;
            prevLeft.right = cur;
            fixHeightAndSize(cur);
            fixHeightAndSize(prevLeft);
            return prevLeft;
        }

        private Node rotateLeft(Node cur) {
            Node prevRight = cur.right;
            cur.right = prevRight.left;
            prevRight.left = cur;
            fixHeightAndSize(cur);
            fixHeightAndSize(prevRight);
            return prevRight;
        }

        private Node balance(Node cur) {
            fixHeightAndSize(cur);
            if (balanceFactor(cur) == 2) {
                if (balanceFactor(cur.right) < 0)
                    cur.right = rotateRight(cur.right);
                return rotateLeft(cur);
            }
            if (balanceFactor(cur) == -2) {
                if (balanceFactor(cur.left) > 0)
                    cur.left = rotateLeft(cur.left);
                return rotateRight(cur);
            }
            return cur;
        }

        private boolean contains(Node cur, int key) {
            if (cur == null)
                return false;
            else if (key < cur.key)
                return contains(cur.left, key);
            else if (key > cur.key)
                return contains(cur.right, key);
            else
                return true;
        }

        private Node add(Node cur, int key) {
            if (cur == null)
                return new Node(key);

            if (key < cur.key)
                cur.left = add(cur.left, key);
            else if (key > cur.key || multi)
                cur.right = add(cur.right, key);

            return balance(cur);
        }

        private Node findMin(Node cur) {
            return cur.left != null ? findMin(cur.left) : cur;
        }

        private Node findMax(Node cur) {
            return cur.right != null ? findMax(cur.right) : cur;
        }

        private Node removeMin(Node cur) {
            if (cur.left == null)
                return cur.right;

            cur.left = removeMin(cur.left);
            return balance(cur);
        }

        private Node removeMax(Node cur) {
            if (cur.right == null)
                return cur.left;

            cur.right = removeMax(cur.right);
            return balance(cur);
        }

        private Node remove(Node cur, int key) {
            if (cur == null)
                return null;

            if (key < cur.key)
                cur.left = remove(cur.left, key);
            else if (key > cur.key)
                cur.right = remove(cur.right, key);
            else { //  k == cur.key
                Node prevLeft = cur.left;
                Node prevRight = cur.right;

                if (prevRight == null)
                    return prevLeft;

                Node min = findMin(prevRight);
                min.right = removeMin(prevRight);
                min.left = prevLeft;

                return balance(min);
            }

            return balance(cur);
        }

        int orderOfKey(int key) {
            return orderOfKey(root, key);
        }

        // count < key
        private int orderOfKey(Node cur, int key) {
            if (cur == null)
                return 0;

            if (cur.key < key)
                return size(cur.left) + 1 + orderOfKey(cur.right, key);

            if (cur.key > key || (multi && cur.left != null && cur.left.key == key))
                return orderOfKey(cur.left, key);
// cur.key == key return size(cur.left);
            return cur.key;
        }


        Integer findByOrder(int pos) {
            return size(root) > pos ? findByOrder(root, pos) : null;
        }

        // get i-th
        private int findByOrder(Node cur, int pos) {
            if (size(cur.left) > pos)
                return findByOrder(cur.left, pos);

            if (size(cur.left) == pos)
                return cur.key;

            // size(cur.left) < pos
            return findByOrder(cur.right, pos - 1 - size(cur.left));
        }
        public String toString() {
            return String.valueOf(this.root) ;
        }
    }
}