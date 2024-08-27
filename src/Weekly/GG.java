package Weekly;

import java.io.BufferedInputStream;import java.io.File;import java.io.FileInputStream;import java.io.InputStream;import java.util.*;import static java.lang.System.out;import java.io.*;import java.util.Arrays;

class GG {
    static String INPUT = """
      8
      3
      S
      3C 9S 4C 6D 3S 7S
      2
      C
      3S 5D 9S 6H
      1
      H
      6C 5D
      1
      S
      7S 3S
      1
      H
      9S 9H
      1
      S
      9S 9H
      1
      C
      9D 8H
      2
      C
      9C 9S 6H 8C
                  
            """;
    
    static boolean oj = System.getProperty("ONLINE_JUDGE") != null;    static InputStream is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());    static FastScanner sc = new FastScanner(is);
    
    public static void main(String[] args) {
        int t = sc.nextInt();
        outer:
        while(t-- > 0){
            int n = sc.nextInt();
            char ch = sc.next().charAt(0);
            String[] arr = readStringArray(sc, 2 * n);
            HashMap<Character, TreeSet<Integer>> map = new HashMap<>();
            map.put('D', new TreeSet<>());
            map.put('H', new TreeSet<>());
            map.put('S', new TreeSet<>());
            map.put('C', new TreeSet<>());
            for (String s : arr) {
                map.putIfAbsent(s.charAt(1), new TreeSet<>());
                map.get(s.charAt(1)).add(s.charAt(0) - '0');
            }
            KeyValue[] pairs = new KeyValue[n];
//            for (int i = 0; i)


            int nn = n;
            TreeSet<Integer> C = map.get('C');
            TreeSet<Integer> H = map.get('H');
            TreeSet<Integer> D = map.get('D');
            TreeSet<Integer> S = map.get('S');

            int i = 0;

            fill(pairs, n, S, H, D, C,  ch, map);

            for (TreeSet<Integer> set : map.values()) {
                if (!set.isEmpty()) {
                    impossible(n);
                    continue outer;
                }
            }

            for (KeyValue<String, String> k : pairs) {
                out.println(k.getKey() + " " + k.getValue());
            }

        }
    }

    public static void fill(KeyValue<String, String>[] pairs, int n, TreeSet<Integer> S,
                            TreeSet<Integer> H, TreeSet<Integer> D, TreeSet<Integer> C,
                            char wild, HashMap<Character, TreeSet<Integer>> map) {

        TreeSet<Integer> wildd = map.get(wild);
        if (wildd== null) wildd = new TreeSet<>();
        int[] i = new int[1];

        if (wild == 'S') {
            operations(map, pairs, i, wild, 'H', wildd, n);
            operations(map, pairs, i, wild, 'D', wildd, n);
            operations(map, pairs, i, wild, 'C', wildd, n);
            operations(map, pairs, i, wild, 'S', wildd, n);
        } else if (wild == 'H') {
            operations(map, pairs, i, wild, 'D', wildd, n);
            operations(map, pairs, i, wild, 'C', wildd, n);
            operations(map, pairs, i, wild, 'S', wildd, n);
            operations(map, pairs, i, wild, 'H', wildd, n);
        } else if(wild == 'D') {
            operations(map, pairs, i, wild, 'H', wildd, n);
            operations(map, pairs, i, wild, 'C', wildd, n);
            operations(map, pairs, i, wild, 'S', wildd, n);
            operations(map, pairs, i, wild, 'D', wildd, n);
        } else {
            operations(map, pairs, i, wild, 'H', wildd, n);
            operations(map, pairs, i, wild, 'D', wildd, n);
            operations(map, pairs, i, wild, 'S', wildd, n);
            operations(map, pairs, i, wild, 'C', wildd, n);
        }




    }
    public static void impossible (int n) {
                for (int i = 0; i < 1; i++)
                    out.println("IMPOSSIBLE");
    }

    public static void operations(HashMap<Character, TreeSet<Integer>> map,KeyValue<String, String>[] pairs, int[] i,
                                  char wild,char operation,  TreeSet<Integer> wildd, int n) {

        TreeSet<Integer> op = map.get(operation);

        if (op.isEmpty()) return ;

        while (!op.isEmpty()) {
            if (op.size() >= 2) {
                pairs[i[0]++] = new KeyValue<>(op.pollFirst()+""+ operation,op.pollFirst()+""+operation);
            } else {
                if (wildd.isEmpty()) {

                    return;
                }
                pairs[i[0]++] = new KeyValue<>(op.pollFirst()+""+operation,
                  wildd.pollLast()+""+wild);
            }
        }
    }
    private static class KeyValue<K, V> implements Map.Entry<K, V>
    {
        private K key;
        private V value;

        public KeyValue(K key, V value)
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

        @Override
        public String toString(){

            return this.key + " " + this.value;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyValue<?, ?> keyValue = (KeyValue<?, ?>) o;
            return Objects.equals(key, keyValue.key) && Objects.equals(value, keyValue.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
    
    
    private static int[] readIntArray(FastScanner sc, int n) {        int[] arr = new int[n];        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();        return arr;    }    private static long[] readLongArray(FastScanner sc, int n) {        long[] arr = new long[n];        for (int i = 0; i < n; i++) arr[i] = sc.nextLong();        return arr;    } private static String[] readStringArray(FastScanner sc, int n) {        String[] arr = new String[n];        for (int i = 0; i < n; i++) arr[i] = sc.next();        return arr;    } private static int[][] read2dIntArray(FastScanner sc, int n, int m) { int[][] arr = new int[n][m]; for (int i = 0; i < n; i++) { arr[i] = readIntArray(sc, m); } return arr; } private static long[][] read2dLongArray(FastScanner sc, int n, int m) { long[][] arr = new long[n][m]; for (int i = 0; i < n; i++) { arr[i] = readLongArray(sc, m); } return arr; } private static String[][] read2dStringArray(FastScanner sc, int n, int m) { String[][] arr = new String[n][m]; for (int i = 0; i < n; i++) { arr[i] = readStringArray(sc, m); } return arr; }
    private static void yes() {out.println("YES");}private static void no() {out.println("NO");}   
    public static void print(int[] arr) {        for (int x : arr)            out.print(x + " ");        out.println();    }    public static void print(long[] arr) {        for (long x : arr)            out.print(x + " ");        out.println();    }    public static void print(String[] arr) {        for (String x : arr)            out.print(x + " ");        out.println();    }
    public static long totient(long n) {        long result = n;        for (int p = 2; (long) p * p <= n; ++p)            if (n % p == 0) {                while (n % p == 0)                    n /= p;                result -= result / p;            }        if (n > 1)            result -= result / n;        return result;        /*        find phi(i) from 1 to N fast        O(N*loglogN)        long[] arr = new long[N+1];        for(int i=1; i <= N; i++)            arr[i] = i;        for(int v=2; v <= N; v++)            if(arr[v] == v)                for(int a=v; a <= N; a+=v)                    arr[a] -= arr[a]/v;         */    }
    public static ArrayList<Integer> findDiv(int N) {                ArrayList<Integer> ls1 = new ArrayList<Integer>();        ArrayList<Integer> ls2 = new ArrayList<Integer>();        for (int i = 1; i <= (int) (Math.sqrt(N) + 0.00000001); i++)            if (N % i == 0) {                ls1.add(i);                ls2.add(N / i);            }        Collections.reverse(ls2);        for (int b : ls2)            if (b != ls1.get(ls1.size() - 1))                ls1.add(b);        return ls1;    } public static ArrayList<Long> findDiv(long N) {        ArrayList<Long> ls1 = new ArrayList<Long>();        ArrayList<Long> ls2 = new ArrayList<Long>();        for (long i = 1; i <= (long) (Math.sqrt(N) + 0.00000001); i++)            if (N % i == 0) {                ls1.add(i);                ls2.add(N / i);            }        Collections.reverse(ls2);        for (long b : ls2)            if (b != ls1.get(ls1.size() - 1))                ls1.add(b);        return ls1;    }
    public static void sort(int[] arr) {         ArrayList<Integer> ls = new ArrayList<Integer>();        for (int x : arr)            ls.add(x);        Collections.sort(ls);        for (int i = 0; i < arr.length; i++)            arr[i] = ls.get(i);    }        public static void sort(long[] arr) {          ArrayList<Long> ls = new ArrayList<Long>();        for (long x : arr)            ls.add(x);        Collections.sort(ls);        for (int i = 0; i < arr.length; i++)            arr[i] = ls.get(i);    }    public static void sort(String[] arr) {        ArrayList<String> ls = new ArrayList<String>();        for (String x : arr)            ls.add(x);        Collections.sort(ls);        for (int i = 0; i < arr.length; i++)            arr[i] = ls.get(i);    } public static void reverseSort(int[] arr) { ArrayList<Integer> ls = new ArrayList<Integer>(); for (int x : arr) ls.add(x); Collections.sort(ls, Collections.reverseOrder()); for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i); } public static void reverseSort(long[] arr) { ArrayList<Long> ls = new ArrayList<Long>(); for (long x : arr) ls.add(x); Collections.sort(ls, Collections.reverseOrder()); for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i); } public static void reverseSort(String[] arr) { ArrayList<String> ls = new ArrayList<String>(); for (String x : arr) ls.add(x); Collections.sort(ls, Collections.reverseOrder()); for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i); }
    public static long power(long x, long y, long p) {        long res = 1L;        x = x % p;        while (y > 0) {            if ((y & 1) == 1)                res = (res * x) % p;            y >>= 1;            x = (x * x) % p;        }        return res;    }
    public static void push(HashMap<Integer, Integer> map, int k, int v) {        if (!map.containsKey(k))            map.put(k, v);        else            map.put(k, map.get(k) + v);    }    public static void pull(HashMap<Integer, Integer> map, int k, int v) {        int lol = map.get(k);        if (lol == v)            map.remove(k);        else            map.put(k, lol - v);    }    public static void push(HashMap<String, Integer> map, String k, int v) {        if (!map.containsKey(k))            map.put(k, v);        else            map.put(k, map.get(k) + v);    }    public static void pull(HashMap<String, Integer> map, String k, int v) {        int lol = map.get(k);        if (lol == v)            map.remove(k);        else            map.put(k, lol - v);    }    public static void push(HashMap<Long, Integer> map, Long k, int v) {        if (!map.containsKey(k))            map.put(k, v);        else            map.put(k, map.get(k) + v);    }    public static void pull(HashMap<Long, Integer> map, Long k, int v) {        int lol = map.get(k);        if (lol == v)            map.remove(k);        else            map.put(k, lol - v);    }
    public static int[] compress(int[] arr) {        ArrayList<Integer> ls = new ArrayList<Integer>();        for (int x : arr)            ls.add(x);        Collections.sort(ls);        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();        int boof = 1; /* min value */        for (int x : ls)            if (!map.containsKey(x))                map.put(x, boof++);        int[] brr = new int[arr.length];        for (int i = 0; i < arr.length; i++)            brr[i] = map.get(arr[i]);        return brr;    }
    public static long[][] multiply(long[][] left, long[][] right) {        long MOD = 1000000007L;        int N = left.length;        int M = right[0].length;        long[][] res = new long[N][M];        for (int a = 0; a < N; a++)            for (int b = 0; b < M; b++)                for (int c = 0; c < left[0].length; c++) {                    res[a][b] += (left[a][c] * right[c][b]) % MOD;                    if (res[a][b] >= MOD)                        res[a][b] -= MOD;                }        return res;    }    public static long[][] power(long[][] grid, long pow) {        long[][] res = new long[grid.length][grid[0].length];        for (int i = 0; i < res.length; i++)            res[i][i] = 1L;        long[][] curr = grid.clone();        while (pow > 0) {            if ((pow & 1L) == 1L)                res = multiply(curr, res);            pow >>= 1;            curr = multiply(curr, curr);        }        return res;    }
    public static long sqrt(long x) {long start = 0, end = (long) 3e9, ans = 1; while (start <= end) {long mid = (start + end) / 2; if (mid * mid <= x) {ans = mid;start = mid + 1;} else end = mid - 1;} return ans;}
    private static class FastScanner {              private final int BS = 1 << 16;        private final char NC = (char) 0;        private final byte[] buf = new byte[BS];        private int bId = 0, size = 0;        private char c = NC;        private double cnt = 1;        private BufferedInputStream in;        public FastScanner() {            in = new BufferedInputStream(System.in, BS);        }  public FastScanner(InputStream ff) {            in = new BufferedInputStream(ff);        }        public FastScanner(String s) {            try {                in = new BufferedInputStream(new FileInputStream(new File(s)), BS);            } catch (Exception e) {                in = new BufferedInputStream(System.in, BS);            }        }        private char getChar() {            while (bId == size) {                try {                    size = in.read(buf);                } catch (Exception e) {                    return NC;                }                if (size == -1) return NC;                bId = 0;            }            return (char) buf[bId++];        }        public int nextInt() {            return (int) nextLong();        }        public int[] nextInts(int N) {            int[] res = new int[N];            for (int i = 0; i < N; i++) {                res[i] = (int) nextLong();            }            return res;        }        public long[] nextLongs(int N) {            long[] res = new long[N];            for (int i = 0; i < N; i++) {                res[i] = nextLong();            }            return res;        }        public long nextLong() {            cnt = 1;            boolean neg = false;            if (c == NC) c = getChar();            for (; (c < '0' || c > '9'); c = getChar()) {                if (c == '-') neg = true;            }            long res = 0;            for (; c >= '0' && c <= '9'; c = getChar()) {                res = (res << 3) + (res << 1) + c - '0';                cnt *= 10;            }            return neg ? -res : res;        }        public double nextDouble() {            double cur = nextLong();            return c != '.' ? cur : cur + nextLong() / cnt;        }        public double[] nextDoubles(int N) {            double[] res = new double[N];            for (int i = 0; i < N; i++) {                res[i] = nextDouble();            }            return res;        }        public String next() {            StringBuilder res = new StringBuilder();            while (c <= 32) c = getChar();            while (c > 32) {                res.append(c);                c = getChar();            }            return res.toString();        }        public String nextLine() {            StringBuilder res = new StringBuilder();            while (c <= 32) c = getChar();            while (c != '\n') {                res.append(c);                c = getChar();            }            return res.toString();        }        public boolean hasNext() {            if (c > 32) return true;            while (true) {                c = getChar();                if (c == NC) return false;                else if (c > 32) return true;            }        }    }private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); 
    }
  }