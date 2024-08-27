package Weekly._405;
import java.io.BufferedInputStream;import java.io.File;import java.io.FileInputStream;import java.io.InputStream;import java.util.*;import static java.lang.System.out;import java.io.*;import java.util.Arrays;
@SuppressWarnings("unused")
public class    A {
    static String INPUT = """
            [["X","Y","."],["Y",".","."]]
            """;
    
    static boolean oj = System.getProperty("ONLINE_JUDGE") != null;    static InputStream is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());    static FastScanner sc = new FastScanner(is);




    public static void main(String[] args) {
          char[][] s = {{'X','Y','.'},{'Y','.','.'}};

        out.println(minimumCost("abcdef", new String[]{"abdef","abc","d","def","ef"}, new int[]{100,1,
      1,10,5}));
    }

    public static long minimumCost(String target, String[] words, int[] costs) {

        Trie t = new Trie();
        for (int i = 0; i < words.length; i++) {
            t.insert(words[i], costs[i]);
        }
        long answer = go(target, 0, t);
        return answer;
    }

    private static long go(String target, int ind, Trie root) {
        if (ind == target.length() ) return 0;

        Node temp = root.root;
        long min = Integer.MAX_VALUE;
        for (int i = ind; i < target.length(); i++) {
            if (temp.arr[target.charAt(i) - 'a'] == null) {
               break;
            }
            temp = temp.arr[target.charAt(i) - 'a'];
            if (temp.isEnd) {
                long val = temp.val + go(target, i + 1, root);
                min = Math.min(min, val);
            }
        }
        return min;
    }

    private static class Trie {
        Node root;
        public Trie() {
            root = new Node();
        }

        public void insert(String word, int val) {
            Node temp = root;
            for(char ch : word.toCharArray()){
                int index=  ch - 'a';
                if(temp.arr[index]==null){
                    temp.arr[index] = new Node();
                }

                temp = temp.arr[index];

            }
            temp.isEnd = true;
            temp.val = val;
        }

        public int search(String word) {
            Node temp = root;
            for(char ch : word.toCharArray()){
                int index=  ch - 'a';
                if(temp.arr[index]==null) return -1;

                temp = temp.arr[index];
            }

            return temp.val;

        }

        public boolean startsWith(String word) {
            Node temp = root;
            for(char ch : word.toCharArray()){
                int index=  ch - 'a';
                if(temp.arr[index]==null)return false;

                temp = temp.arr[index];
            }
            return true;

        }
    }
    private static class Node{
        Node[] arr=  new Node[26];
        boolean isEnd = false;
        int val = Integer.MAX_VALUE;

    }



    public static List<String> validStrings(int n) {
        StringBuilder sb = new StringBuilder();
        List<String> answer = new ArrayList<>();
        go(n, sb, answer);
        return answer;
    }

    private static void go(int n, StringBuilder sb, List<String> answer) {
        if (n <= 0) {
            for(int i = 0; i < sb.length() - 1; i++) {
                if (sb.charAt(i) == '0' && sb.charAt(i) == sb.charAt(i + 1)) return;

            }

                answer.add(sb.toString());

            return;
        }
        sb.append("0");
        go(n - 1, sb, answer) ;
        sb.deleteCharAt(sb.length() - 1);

        sb.append("1");
        go(n - 1, sb, answer) ;
        sb.deleteCharAt(sb.length() - 1);

    }

    public static int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] x = new int[n][m];
        int[][] y = new int[n][m];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'X') x[i][j] = 1;
                if (grid[i][j] == 'Y') y[i][j] = 1;
            }
        }
        NumMatrix xx = new NumMatrix(x);
        NumMatrix yy = new NumMatrix(y);

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < m; j++) {
                int countx = xx.sumRegion(0, 0, i, j);
                int county = yy.sumRegion(0, 0, i, j);
                if (countx >= 1 && countx == county) count++;

            }
        }


        return count;

    }

    private static class NumMatrix {
        int matrix[][];
        public NumMatrix(int[][] matrix)
        {
            int r = matrix.length;
            int c = matrix[0].length;
            this.matrix = matrix;
            // col wise sum
            for(int i=1; i<r; i++)
            {
                for(int j=0; j<c; j++) matrix[i][j] = matrix[i][j] + matrix[i-1][j];
            }
            for(int i=0; i<r; i++)
            {
                for(int j=1; j<c; j++) matrix[i][j] = matrix[i][j] + matrix[i][j-1];
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2)
        {
            int totalArea = matrix[row2][col2];
            int above = (row1 == 0) ? 0 : matrix[row1-1][col2];
            int sidePart = (col1 == 0) ? 0 : matrix[row2][col1-1];
            int duplicatePart = (col1 == 0 || row1 == 0) ? 0 : matrix[row1-1][col1-1];
            int ans = (totalArea - above) - (sidePart - duplicatePart);
            return ans;
        }
    }
    
    
    
    private static int[] readIntArray(FastScanner sc, int n) {        int[] arr = new int[n];        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();        return arr;    }    private static long[] readLongArray(FastScanner sc, int n) {        long[] arr = new long[n];        for (int i = 0; i < n; i++) arr[i] = sc.nextLong();        return arr;    } private static String[] readStringArray(FastScanner sc, int n) {        String[] arr = new String[n];        for (int i = 0; i < n; i++) arr[i] = sc.next();        return arr;    } private static int[][] read2dIntArray(FastScanner sc, int n, int m) { int[][] arr = new int[n][m]; for (int i = 0; i < n; i++) { arr[i] = readIntArray(sc, m); } return arr; } private static long[][] read2dLongArray(FastScanner sc, int n, int m) { long[][] arr = new long[n][m]; for (int i = 0; i < n; i++) { arr[i] = readLongArray(sc, m); } return arr; } private static String[][] read2dStringArray(FastScanner sc, int n, int m) { String[][] arr = new String[n][m]; for (int i = 0; i < n; i++) { arr[i] = readStringArray(sc, m); } return arr; }
    private static void yes() {out.println("YES");}private static void no() {out.println("NO");}   
    public static void print(int[] arr) {        for (int x : arr)            out.print(x + " ");        out.println();    }    public static void print(long[] arr) {        for (long x : arr)            out.print(x + " ");        out.println();    }    public static void print(String[] arr) {        for (String x : arr)            out.print(x + " ");        out.println();    }
    public static long totient(long n) {        long result = n;        for (int p = 2; (long) p * p <= n; ++p)            if (n % p == 0) {                while (n % p == 0)                    n /= p;                result -= result / p;            }        if (n > 1)            result -= result / n;        return result;        /*        find phi(i) from 1 to N fast        O(N*loglogN)        long[] arr = new long[N+1];        for(int i=1; i <= N; i++)            arr[i] = i;        for(int v=2; v <= N; v++)            if(arr[v] == v)                for(int a=v; a <= N; a+=v)                    arr[a] -= arr[a]/v;         */    }
    public static ArrayList<Integer> findDiv(int N) {                ArrayList<Integer> ls1 = new ArrayList<Integer>();        ArrayList<Integer> ls2 = new ArrayList<Integer>();        for (int i = 1; i <= (int) (Math.sqrt(N) + 0.00000001); i++)            if (N % i == 0) {                ls1.add(i);                ls2.add(N / i);            }        Collections.reverse(ls2);        for (int b : ls2)            if (b != ls1.get(ls1.size() - 1))                ls1.add(b);        return ls1;    } public static ArrayList<Long> findDiv(long N) {        ArrayList<Long> ls1 = new ArrayList<Long>();        ArrayList<Long> ls2 = new ArrayList<Long>();        for (long i = 1; i <= (long) (Math.sqrt(N) + 0.00000001); i++)            if (N % i == 0) {                ls1.add(i);                ls2.add(N / i);            }        Collections.reverse(ls2);        for (long b : ls2)            if (b != ls1.get(ls1.size() - 1))                ls1.add(b);        return ls1;    }
    public static ArrayList<Integer> findPrimeFactors(int N) {    ArrayList<Integer> ls1 = new ArrayList<Integer>();    int n = N;    if (n % 2 == 0) {      ls1.add(2);    }    while (n%2==0)    {      n /= 2;    }    for (int i = 3; i <= Math.sqrt(n); i+= 2)    {      if (n % i == 0) {        ls1.add(i);      }      while (n%i == 0)      {        n /= i;      }    }    if (n > 2)      ls1.add(n);    return ls1;  }  public static ArrayList<Long> findPrimeFactors(long N) {    ArrayList<Long> ls1 = new ArrayList<>();    long n = N;    if (n % 2 == 0) {      ls1.add(2L);    }    while (n%2==0)    {      n /= 2;    }    for (long i = 3; i <= Math.sqrt(n); i+= 2)    {      if (n % i == 0) {        ls1.add(i);      }      while (n%i == 0)      {        n /= i;      }    }    if (n > 2)      ls1.add(n);    return ls1;  }
    public static void sort(int[] arr) {         ArrayList<Integer> ls = new ArrayList<Integer>();        for (int x : arr)            ls.add(x);        Collections.sort(ls);        for (int i = 0; i < arr.length; i++)            arr[i] = ls.get(i);    }        public static void sort(long[] arr) {          ArrayList<Long> ls = new ArrayList<Long>();        for (long x : arr)            ls.add(x);        Collections.sort(ls);        for (int i = 0; i < arr.length; i++)            arr[i] = ls.get(i);    }    public static void sort(String[] arr) {        ArrayList<String> ls = new ArrayList<String>();        for (String x : arr)            ls.add(x);        Collections.sort(ls);        for (int i = 0; i < arr.length; i++)            arr[i] = ls.get(i);    } public static void reverseSort(int[] arr) { ArrayList<Integer> ls = new ArrayList<Integer>(); for (int x : arr) ls.add(x); Collections.sort(ls, Collections.reverseOrder()); for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i); } public static void reverseSort(long[] arr) { ArrayList<Long> ls = new ArrayList<Long>(); for (long x : arr) ls.add(x); Collections.sort(ls, Collections.reverseOrder()); for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i); } public static void reverseSort(String[] arr) { ArrayList<String> ls = new ArrayList<String>(); for (String x : arr) ls.add(x); Collections.sort(ls, Collections.reverseOrder()); for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i); }
    public static long power(long x, long y, long p) {        long res = 1L;        x = x % p;        while (y > 0) {            if ((y & 1) == 1)                res = (res * x) % p;            y >>= 1;            x = (x * x) % p;        }        return res;    }
    public static void push(HashMap<Integer, Integer> map, int k, int v) {        if (!map.containsKey(k))            map.put(k, v);        else            map.put(k, map.get(k) + v);    }    public static void pull(HashMap<Integer, Integer> map, int k, int v) {        int lol = map.get(k);        if (lol == v)            map.remove(k);        else            map.put(k, lol - v);    }    public static void push(HashMap<String, Integer> map, String k, int v) {        if (!map.containsKey(k))            map.put(k, v);        else            map.put(k, map.get(k) + v);    }    public static void pull(HashMap<String, Integer> map, String k, int v) {        int lol = map.get(k);        if (lol == v)            map.remove(k);        else            map.put(k, lol - v);    }    public static void push(HashMap<Long, Integer> map, Long k, int v) {        if (!map.containsKey(k))            map.put(k, v);        else            map.put(k, map.get(k) + v);    }    public static void pull(HashMap<Long, Integer> map, Long k, int v) {        int lol = map.get(k);        if (lol == v)            map.remove(k);        else            map.put(k, lol - v);    }
    public static int[] compress(int[] arr) {        ArrayList<Integer> ls = new ArrayList<Integer>();        for (int x : arr)            ls.add(x);        Collections.sort(ls);        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();        int boof = 1; /* min value */        for (int x : ls)            if (!map.containsKey(x))                map.put(x, boof++);        int[] brr = new int[arr.length];        for (int i = 0; i < arr.length; i++)            brr[i] = map.get(arr[i]);        return brr;    }
    public static long[][] multiply(long[][] left, long[][] right) {        long MOD = 1000000007L;        int N = left.length;        int M = right[0].length;        long[][] res = new long[N][M];        for (int a = 0; a < N; a++)            for (int b = 0; b < M; b++)                for (int c = 0; c < left[0].length; c++) {                    res[a][b] += (left[a][c] * right[c][b]) % MOD;                    if (res[a][b] >= MOD)                        res[a][b] -= MOD;                }        return res;    }    public static long[][] power(long[][] grid, long pow) {        long[][] res = new long[grid.length][grid[0].length];        for (int i = 0; i < res.length; i++)            res[i][i] = 1L;        long[][] curr = grid.clone();        while (pow > 0) {            if ((pow & 1L) == 1L)                res = multiply(curr, res);            pow >>= 1;            curr = multiply(curr, curr);        }        return res;    }
    public static long sqrt(long x) {long start = 0, end = (long) 3e9, ans = 1; while (start <= end) {long mid = (start + end) / 2; if (mid * mid <= x) {ans = mid;start = mid + 1;} else end = mid - 1;} return ans;}
    private static class FastScanner {              private final int BS = 1 << 16;        private final char NC = (char) 0;        private final byte[] buf = new byte[BS];        private int bId = 0, size = 0;        private char c = NC;        private double cnt = 1;        private BufferedInputStream in;        public FastScanner() {            in = new BufferedInputStream(System.in, BS);        }  public FastScanner(InputStream ff) {            in = new BufferedInputStream(ff);        }        public FastScanner(String s) {            try {                in = new BufferedInputStream(new FileInputStream(new File(s)), BS);            } catch (Exception e) {                in = new BufferedInputStream(System.in, BS);            }        }        private char getChar() {            while (bId == size) {                try {                    size = in.read(buf);                } catch (Exception e) {                    return NC;                }                if (size == -1) return NC;                bId = 0;            }            return (char) buf[bId++];        }        public int nextInt() {            return (int) nextLong();        }        public int[] nextInts(int N) {            int[] res = new int[N];            for (int i = 0; i < N; i++) {                res[i] = (int) nextLong();            }            return res;        }        public long[] nextLongs(int N) {            long[] res = new long[N];            for (int i = 0; i < N; i++) {                res[i] = nextLong();            }            return res;        }        public long nextLong() {            cnt = 1;            boolean neg = false;            if (c == NC) c = getChar();            for (; (c < '0' || c > '9'); c = getChar()) {                if (c == '-') neg = true;            }            long res = 0;            for (; c >= '0' && c <= '9'; c = getChar()) {                res = (res << 3) + (res << 1) + c - '0';                cnt *= 10;            }            return neg ? -res : res;        }        public double nextDouble() {            double cur = nextLong();            return c != '.' ? cur : cur + nextLong() / cnt;        }        public double[] nextDoubles(int N) {            double[] res = new double[N];            for (int i = 0; i < N; i++) {                res[i] = nextDouble();            }            return res;        }        public String next() {            StringBuilder res = new StringBuilder();            while (c <= 32) c = getChar();            while (c > 32) {                res.append(c);                c = getChar();            }            return res.toString();        }        public String nextLine() {            StringBuilder res = new StringBuilder();            while (c <= 32) c = getChar();            while (c != '\n') {                res.append(c);                c = getChar();            }            return res.toString();        }        public boolean hasNext() {            if (c > 32) return true;            while (true) {                c = getChar();                if (c == NC) return false;                else if (c > 32) return true;            }        }    }private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); 
    }
  }