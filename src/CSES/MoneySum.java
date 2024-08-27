//package CSES;

import java.io.BufferedInputStream;import java.io.File;import java.io.FileInputStream;import java.util.*;import static java.lang.System.out;

class MoneySum {


    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int t = 1;
        outer:
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = readIntArray(sc, n);
            sort(arr);
            HashSet<Integer> vis = new HashSet<>();
            TreeSet<Long> answer = new TreeSet<>();

//            for(int i = 0; i < arr.length; i++) {
//                vis.add(i);
//                answer.add((long) arr[i]);
//
//                vis.remove(i);
//            }
            int sum = Arrays.stream(arr).sum();
            long[][] dp = new long[Arrays.stream(arr).sum() +11][n + 1];
            go(arr, answer, vis, sum, -1, dp);
            out.println(answer.size());
            for(long x :  answer) {
                out.print(x +  " ");
            }
        }
    }

    public static void recursiveMergeSort(int[] seq) {
        recursiveMergeSort(seq, new int[seq.length], 0, seq.length - 1);
    }

    private static void recursiveMergeSort(int[] seq, int[] aux, int l, int r) {
        if (r - l <= 0)
            return;
        int mid = (r + l) >> 1;
        recursiveMergeSort(seq, aux, l, mid);
        recursiveMergeSort(seq, aux, mid + 1, r);
        merge(seq, aux, l, mid, r);
    }

    public static void iterativeMergeSort(int[] seq) {
        int length = seq.length;
        int[] aux = new int[length];
        for (int gap = 1; gap < length; gap *= 2)
            for (int l = 0; l < length - gap; l += gap * 2)
                merge(seq, aux, l, l + gap - 1, Math.min(length - 1, l + gap + gap - 1));
    }

    private static void merge(int[] seq, int[] aux, int l, int m, int r) {
        for (int i = l; i <= m; i++)
            aux[i] = seq[i];
        for (int i = l, j = m + 1, k = l; k <= r; k++) {
            if (j == r + 1 || (i <= m && aux[i] < seq[j]))
                seq[k] = aux[i++];
            else
                seq[k] = seq[j++];
        }
    }




    private static long go(int[] arr, TreeSet<Long> answer, HashSet<Integer> vis, int sum, int index, long[][] dp) {


        if(index != - 1 && dp[sum][index] != 0) return  dp[sum][index];

        for (int i= index + 1; i < arr.length; i++) {

                answer.add((long) (sum + arr[i]));
                dp[(int) (sum + arr[i])][i] = go(arr, answer, vis, sum + arr[i], i, dp);

        }

        return sum;
    }
//    4 2 2 5
//    2 2 4 5
//    2 4 8 13
//    6 11
//    9
//    5
//    2 4 5 6 8 9 11 13
//
    
    
    
    //reads Int[] and Long[] and String[]
    private static int[] readIntArray(FastScanner sc, int n) {        int[] arr = new int[n];        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();        return arr;    }    private static long[] readLongArray(FastScanner sc, int n) {        long[] arr = new long[n];        for (int i = 0; i < n; i++) arr[i] = sc.nextLong();        return arr;    } private static String[] readStringArray(FastScanner sc, int n) {        String[] arr = new String[n];        for (int i = 0; i < n; i++) arr[i] = sc.next();        return arr;    }
    //read Int[][] Long[][] String[][]
    private static int[][] read2dIntArray(FastScanner sc, int n, int m) { int[][] arr = new int[n][m]; for (int i = 0; i < n; i++) { arr[i] = readIntArray(sc, m); } return arr; } private static long[][] read2dLongArray(FastScanner sc, int n, int m) { long[][] arr = new long[n][m]; for (int i = 0; i < n; i++) { arr[i] = readLongArray(sc, m); } return arr; } private static String[][] read2dStringArray(FastScanner sc, int n, int m) { String[][] arr = new String[n][m]; for (int i = 0; i < n; i++) { arr[i] = readStringArray(sc, m); } return arr; }
    
    private static void yes() {out.println("YES");}private static void no() {out.println("NO");}   
    
    //prints Array Int, Long, String
    public static void print(int[] arr) {        for (int x : arr)            out.print(x + " ");        out.println();    }    public static void print(long[] arr) {        for (long x : arr)            out.print(x + " ");        out.println();    }    public static void print(String[] arr) {        for (String x : arr)            out.print(x + " ");        out.println();    }
    
    public static long totient(long n) {        long result = n;        for (int p = 2; (long) p * p <= n; ++p)            if (n % p == 0) {                while (n % p == 0)                    n /= p;                result -= result / p;            }        if (n > 1)            result -= result / n;        return result;        /*        find phi(i) from 1 to N fast        O(N*loglogN)        long[] arr = new long[N+1];        for(int i=1; i <= N; i++)            arr[i] = i;        for(int v=2; v <= N; v++)            if(arr[v] == v)                for(int a=v; a <= N; a+=v)                    arr[a] -= arr[a]/v;         */    }
    
    //gens all divisors of N
    public static ArrayList<Integer> findDiv(int N) {                ArrayList<Integer> ls1 = new ArrayList<Integer>();        ArrayList<Integer> ls2 = new ArrayList<Integer>();        for (int i = 1; i <= (int) (Math.sqrt(N) + 0.00000001); i++)            if (N % i == 0) {                ls1.add(i);                ls2.add(N / i);            }        Collections.reverse(ls2);        for (int b : ls2)            if (b != ls1.get(ls1.size() - 1))                ls1.add(b);        return ls1;    }
    //gens all divisors of N (Long k liye mostly used for finding patterns)
    public static ArrayList<Long> findDiv(long N) {        ArrayList<Long> ls1 = new ArrayList<Long>();        ArrayList<Long> ls2 = new ArrayList<Long>();        for (long i = 1; i <= (long) (Math.sqrt(N) + 0.00000001); i++)            if (N % i == 0) {                ls1.add(i);                ls2.add(N / i);            }        Collections.reverse(ls2);        for (long b : ls2)            if (b != ls1.get(ls1.size() - 1))                ls1.add(b);        return ls1;    }
    
    //because Arrays.sort() uses quicksort which is dumb
    //Collections.sort() uses merge sort
    public static void sort(int[] arr) {         ArrayList<Integer> ls = new ArrayList<Integer>();        for (int x : arr)            ls.add(x);        Collections.sort(ls);        for (int i = 0; i < arr.length; i++)            arr[i] = ls.get(i);    }        public static void sort(long[] arr) {          ArrayList<Long> ls = new ArrayList<Long>();        for (long x : arr)            ls.add(x);        Collections.sort(ls);        for (int i = 0; i < arr.length; i++)            arr[i] = ls.get(i);    }    public static void sort(String[] arr) {        ArrayList<String> ls = new ArrayList<String>();        for (String x : arr)            ls.add(x);        Collections.sort(ls);        for (int i = 0; i < arr.length; i++)            arr[i] = ls.get(i);    }
    public static void reverseSort(int[] arr) { ArrayList<Integer> ls = new ArrayList<Integer>(); for (int x : arr) ls.add(x); Collections.sort(ls, Collections.reverseOrder()); for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i); } public static void reverseSort(long[] arr) { ArrayList<Long> ls = new ArrayList<Long>(); for (long x : arr) ls.add(x); Collections.sort(ls, Collections.reverseOrder()); for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i); } public static void reverseSort(String[] arr) { ArrayList<String> ls = new ArrayList<String>(); for (String x : arr) ls.add(x); Collections.sort(ls, Collections.reverseOrder()); for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i); }
    
    //0^0 = 1
    public static long power(long x, long y, long p) {        long res = 1L;        x = x % p;        while (y > 0) {            if ((y & 1) == 1)                res = (res * x) % p;            y >>= 1;            x = (x * x) % p;        }        return res;    }

    //custom multiset (replace with HashMap if needed)
    public static void push(HashMap<Integer, Integer> map, int k, int v) {        if (!map.containsKey(k))            map.put(k, v);        else            map.put(k, map.get(k) + v);    }    public static void pull(HashMap<Integer, Integer> map, int k, int v) {        int lol = map.get(k);        if (lol == v)            map.remove(k);        else            map.put(k, lol - v);    }    public static void push(HashMap<String, Integer> map, String k, int v) {        if (!map.containsKey(k))            map.put(k, v);        else            map.put(k, map.get(k) + v);    }    public static void pull(HashMap<String, Integer> map, String k, int v) {        int lol = map.get(k);        if (lol == v)            map.remove(k);        else            map.put(k, lol - v);    }    public static void push(HashMap<Long, Integer> map, Long k, int v) {        if (!map.containsKey(k))            map.put(k, v);        else            map.put(k, map.get(k) + v);    }    public static void pull(HashMap<Long, Integer> map, Long k, int v) {        int lol = map.get(k);        if (lol == v)            map.remove(k);        else            map.put(k, lol - v);    }
    
    //convert big Integer array to 1 to distinct N values vala array.(smaller to larget 5 10 6 -> 1 3 2)
    public static int[] compress(int[] arr) {        ArrayList<Integer> ls = new ArrayList<Integer>();        for (int x : arr)            ls.add(x);        Collections.sort(ls);        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();        int boof = 1; /* min value */        for (int x : ls)            if (!map.containsKey(x))                map.put(x, boof++);        int[] brr = new int[arr.length];        for (int i = 0; i < arr.length; i++)            brr[i] = map.get(arr[i]);        return brr;    }
    // multiply 2d matrix with each other
    public static long[][] multiply(long[][] left, long[][] right) {        long MOD = 1000000007L;        int N = left.length;        int M = right[0].length;        long[][] res = new long[N][M];        for (int a = 0; a < N; a++)            for (int b = 0; b < M; b++)                for (int c = 0; c < left[0].length; c++) {                    res[a][b] += (left[a][c] * right[c][b]) % MOD;                    if (res[a][b] >= MOD)                        res[a][b] -= MOD;                }        return res;    }    public static long[][] power(long[][] grid, long pow) {        long[][] res = new long[grid.length][grid[0].length];        for (int i = 0; i < res.length; i++)            res[i][i] = 1L;        long[][] curr = grid.clone();        while (pow > 0) {            if ((pow & 1L) == 1L)                res = multiply(curr, res);            pow >>= 1;            curr = multiply(curr, curr);        }        return res;    }
    
    //fastscanner custome
    private static class FastScanner {              private final int BS = 1 << 16;        private final char NC = (char) 0;        private final byte[] buf = new byte[BS];        private int bId = 0, size = 0;        private char c = NC;        private double cnt = 1;        private BufferedInputStream in;        public FastScanner() {            in = new BufferedInputStream(System.in, BS);        }        public FastScanner(String s) {            try {                in = new BufferedInputStream(new FileInputStream(new File(s)), BS);            } catch (Exception e) {                in = new BufferedInputStream(System.in, BS);            }        }        private char getChar() {            while (bId == size) {                try {                    size = in.read(buf);                } catch (Exception e) {                    return NC;                }                if (size == -1) return NC;                bId = 0;            }            return (char) buf[bId++];        }        public int nextInt() {            return (int) nextLong();        }        public int[] nextInts(int N) {            int[] res = new int[N];            for (int i = 0; i < N; i++) {                res[i] = (int) nextLong();            }            return res;        }        public long[] nextLongs(int N) {            long[] res = new long[N];            for (int i = 0; i < N; i++) {                res[i] = nextLong();            }            return res;        }        public long nextLong() {            cnt = 1;            boolean neg = false;            if (c == NC) c = getChar();            for (; (c < '0' || c > '9'); c = getChar()) {                if (c == '-') neg = true;            }            long res = 0;            for (; c >= '0' && c <= '9'; c = getChar()) {                res = (res << 3) + (res << 1) + c - '0';                cnt *= 10;            }            return neg ? -res : res;        }        public double nextDouble() {            double cur = nextLong();            return c != '.' ? cur : cur + nextLong() / cnt;        }        public double[] nextDoubles(int N) {            double[] res = new double[N];            for (int i = 0; i < N; i++) {                res[i] = nextDouble();            }            return res;        }        public String next() {            StringBuilder res = new StringBuilder();            while (c <= 32) c = getChar();            while (c > 32) {                res.append(c);                c = getChar();            }            return res.toString();        }        public String nextLine() {            StringBuilder res = new StringBuilder();            while (c <= 32) c = getChar();            while (c != '\n') {                res.append(c);                c = getChar();            }            return res.toString();        }        public boolean hasNext() {            if (c > 32) return true;            while (true) {                c = getChar();                if (c == NC) return false;                else if (c > 32) return true;            }        }    }

  }