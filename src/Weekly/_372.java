package Weekly;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import static java.lang.System.out;

public class _372 {
    public static void main(String[] args) {

        out.println(maximumXorProduct(1,6 ,3 ));
        out.println(maximumXorProduct(12,5 ,4 ));
        out.println(maximumXorProduct(6,7 ,5 ));

        //
//        out.println(maxXORInRange(6, 7));
//        out.println();
    }


    public static int maximumXorProduct(long a, long b, int x) {

//          long x =   (findMaxX(a, b, n));
//            long xx -
        long max = 0;
        HashMap<Long, Long> map = new HashMap<>();
        for(long i = 0; i < (1L << x); i++) {
            map.put(i,((a ^ i) * (b ^ i)) );
        }

        out.println(map);
//          max = Math.max(x, ((a ^ x) * (b ^ x)));

        return (int) max;
    }
    public static int reductionOperations(int[] nums) {
        TreeMap<Integer, Integer> tmap = new TreeMap<>();

        for (int x : nums) {
            tmap.putIfAbsent(x, tmap.getOrDefault(x, 0) + 1);
        }

//         TreeMap<Integer, Integer> map = new TreeMap<>();
//         int i = 1;

//         for (int k : tmap.keySet()) {
//             map.put(i++, tmap.get(k));
//         }
        if(tmap.size() == 1) return 0;
        int answer = 0;
        while (tmap.size() >= 2) {
            Integer lastKey = tmap.lastKey();
            int lastVal = tmap.get(lastKey);
            tmap.pollLastEntry();
            Integer secondLastKey = tmap.lastKey();
            answer += lastVal;
            tmap.put(secondLastKey, tmap.get(secondLastKey) + lastVal);
        }
        return nums.length - tmap.get(tmap.firstKey());
    }
    static long maxXORInRange(int L, int R)
    {
        // get xor of limits
        int LXR = L ^ R;

        //  loop to get msb position of L^R
        int msbPos = 0;
        while (LXR > 0)
        {
            msbPos++;
            LXR >>= 1;
        }

        // construct result by adding 1,
        // msbPos times
        int maxXOR = 0;
        int two = 1;
        while (msbPos-- >0)
        {
            maxXOR += two;
            two <<= 1;
        }

        return maxXOR;
    }
    private static long findMaxX(long a, long b, int n) {
        long nn = (a | b);
//        for(int i = 0; i < )
        return( ((1L << n) | nn) | nn);
    }

    public static int findMinimumOperations(String s1, String s2, String s3) {
        if (s1.length() == 1 || s2.length() == 1 || s3.length() == 1) return -1;
        int min = -1;
        for (int i = 0; i <= s1.length() - 2; i++) {
            String s = s1.substring(i, i+2);
            // System.out.println(s);
            if(s2.contains(s) && s3.contains(s)) {
                min += s2.length() - 2;
                min += s3.length() - 2;
                return min;
            }
        }


        return -1;
    }

    public long minimumSteps(String s) {

        long answer = 0;

        char[] arr = s.toCharArray();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0;i < arr.length; i++) {
            if(arr[i] == '1') list.add(i);
        }
        int last = s.length() - 1;
        for(int i = arr.length - 1; i >= 0; i++) {
            answer += last - arr[i];
            last--;
        }


        return answer;
    }
    private static int[] readarr(FastScanner sc, int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        return arr;
    }

    private static long[] readarr1(FastScanner sc, int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        return arr;
    }

    private static void no() {
        out.println("No");
    }

    private static void yes() {
        out.println("Yes");
    }


    public static void print(int[] arr) {
        //for debugging only
        for (int x : arr)
            out.print(x + " ");
        out.println();
    }

    public static long totient(long n) {
        long result = n;
        for (int p = 2; (long) p * p <= n; ++p)
            if (n % p == 0) {
                while (n % p == 0)
                    n /= p;
                result -= result / p;
            }
        if (n > 1)
            result -= result / n;
        return result;
        /*
        find phi(i) from 1 to N fast
        O(N*loglogN)
        long[] arr = new long[N+1];
        for(int i=1; i <= N; i++)
            arr[i] = i;
        for(int v=2; v <= N; v++)
            if(arr[v] == v)
                for(int a=v; a <= N; a+=v)
                    arr[a] -= arr[a]/v;
         */
    }

    public static ArrayList<Integer> findDiv(int N) {
        //gens all divisors of N
        ArrayList<Integer> ls1 = new ArrayList<Integer>();
        ArrayList<Integer> ls2 = new ArrayList<Integer>();
        for (int i = 1; i <= (int) (Math.sqrt(N) + 0.00000001); i++)
            if (N % i == 0) {
                ls1.add(i);
                ls2.add(N / i);
            }
        Collections.reverse(ls2);
        for (int b : ls2)
            if (b != ls1.get(ls1.size() - 1))
                ls1.add(b);
        return ls1;
    }

    public static void sort(int[] arr) {
        //because Arrays.sort() uses quicksort which is dumb
        //Collections.sort() uses merge sort
        ArrayList<Integer> ls = new ArrayList<Integer>();
        for (int x : arr)
            ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < arr.length; i++)
            arr[i] = ls.get(i);
    }

    public static long power(long x, long y, long p) {
        //0^0 = 1
        long res = 1L;
        x = x % p;
        while (y > 0) {
            if ((y & 1) == 1)
                res = (res * x) % p;
            y >>= 1;
            x = (x * x) % p;
        }
        return res;
    }

    //custom multiset (replace with HashMap if needed)
    public static void push(TreeMap<Integer, Integer> map, int k, int v) {
        //map[k] += v;
        if (!map.containsKey(k))
            map.put(k, v);
        else
            map.put(k, map.get(k) + v);
    }

    public static void pull(TreeMap<Integer, Integer> map, int k, int v) {
        //assumes map[k] >= v
        //map[k] -= v
        int lol = map.get(k);
        if (lol == v)
            map.remove(k);
        else
            map.put(k, lol - v);
    }

    public static int[] compress(int[] arr) {
        ArrayList<Integer> ls = new ArrayList<Integer>();
        for (int x : arr)
            ls.add(x);
        Collections.sort(ls);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int boof = 1; //min value
        for (int x : ls)
            if (!map.containsKey(x))
                map.put(x, boof++);
        int[] brr = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            brr[i] = map.get(arr[i]);
        return brr;
    }

    public static long[][] multiply(long[][] left, long[][] right) {
        long MOD = 1000000007L;
        int N = left.length;
        int M = right[0].length;
        long[][] res = new long[N][M];
        for (int a = 0; a < N; a++)
            for (int b = 0; b < M; b++)
                for (int c = 0; c < left[0].length; c++) {
                    res[a][b] += (left[a][c] * right[c][b]) % MOD;
                    if (res[a][b] >= MOD)
                        res[a][b] -= MOD;
                }
        return res;
    }

    public static long[][] power(long[][] grid, long pow) {
        long[][] res = new long[grid.length][grid[0].length];
        for (int i = 0; i < res.length; i++)
            res[i][i] = 1L;
        long[][] curr = grid.clone();
        while (pow > 0) {
            if ((pow & 1L) == 1L)
                res = multiply(curr, res);
            pow >>= 1;
            curr = multiply(curr, curr);
        }
        return res;
    }

    private static class FastScanner {
        private final int BS = 1 << 16;
        private final char NC = (char) 0;
        private final byte[] buf = new byte[BS];
        private int bId = 0, size = 0;
        private char c = NC;
        private double cnt = 1;
        private BufferedInputStream in;

        public FastScanner() {
            in = new BufferedInputStream(System.in, BS);
        }

        public FastScanner(String s) {
            try {
                in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
            } catch (Exception e) {
                in = new BufferedInputStream(System.in, BS);
            }
        }

        private char getChar() {
            while (bId == size) {
                try {
                    size = in.read(buf);
                } catch (Exception e) {
                    return NC;
                }
                if (size == -1) return NC;
                bId = 0;
            }
            return (char) buf[bId++];
        }

        public int nextInt() {
            return (int) nextLong();
        }

        public int[] nextInts(int N) {
            int[] res = new int[N];
            for (int i = 0; i < N; i++) {
                res[i] = (int) nextLong();
            }
            return res;
        }

        public long[] nextLongs(int N) {
            long[] res = new long[N];
            for (int i = 0; i < N; i++) {
                res[i] = nextLong();
            }
            return res;
        }

        public long nextLong() {
            cnt = 1;
            boolean neg = false;
            if (c == NC) c = getChar();
            for (; (c < '0' || c > '9'); c = getChar()) {
                if (c == '-') neg = true;
            }
            long res = 0;
            for (; c >= '0' && c <= '9'; c = getChar()) {
                res = (res << 3) + (res << 1) + c - '0';
                cnt *= 10;
            }
            return neg ? -res : res;
        }

        public double nextDouble() {
            double cur = nextLong();
            return c != '.' ? cur : cur + nextLong() / cnt;
        }

        public double[] nextDoubles(int N) {
            double[] res = new double[N];
            for (int i = 0; i < N; i++) {
                res[i] = nextDouble();
            }
            return res;
        }

        public String next() {
            StringBuilder res = new StringBuilder();
            while (c <= 32) c = getChar();
            while (c > 32) {
                res.append(c);
                c = getChar();
            }
            return res.toString();
        }

        public String nextLine() {
            StringBuilder res = new StringBuilder();
            while (c <= 32) c = getChar();
            while (c != '\n') {
                res.append(c);
                c = getChar();
            }
            return res.toString();
        }

        public boolean hasNext() {
            if (c > 32) return true;
            while (true) {
                c = getChar();
                if (c == NC) return false;
                else if (c > 32) return true;
            }
        }
    }

}