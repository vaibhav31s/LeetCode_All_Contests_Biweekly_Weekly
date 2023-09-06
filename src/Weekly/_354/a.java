package Weekly._354;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import static java.lang.System.out;

public class a {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();

        out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));

//        out.println(maximumBeauty(new int[]{10,71}, 10));
    }
    public static int[] productExceptSelf(int[] nums) {
        SegmentTree seg = new SegmentTree(0, nums.length);
        for(int i = 0; i < nums.length; i++){
            seg.update(i, nums[i]);
        }

        int[] answer = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            answer[i] = seg.query(0, i - 1)  * seg.query(i+1 , nums.length);
        }
        answer[0] =  seg.query(1, nums.length);
        return answer;

    }
    public  static int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);

        int max = 0;
        TreeSet<Integer> set= new TreeSet<>();
        for(int x : nums) set.add(x);
        for(int x :  set){
            Integer  a = set.contains(x-k) ? x : set.higher(x-k);
            Integer b = set.contains(x+k) ? x : set.lower(x+k);
            if(a == null){
                a = x;
            }
            if(b == null){
                b=  x;
            }
            max = Math.max( upper_bound(nums, b) - lower_bound(nums,a) , max);

        }


        return max;
    }

    private static class SegmentTree
    {
        //Tlatoani's segment tree
        //iterative implementation = low constant runtime factor
        //range query, non lazy
        final int[] val;
        final int treeFrom;
        final int length;

        public SegmentTree(int treeFrom, int treeTo)
        {
            this.treeFrom = treeFrom;
            int length = treeTo - treeFrom + 1;
            int l;
            for (l = 0; (1 << l) < length; l++);
            val = new int[1 << (l + 1)];
            Arrays.fill(val, 1);
            this.length = 1 << l;
        }
        public void update(int index, int delta)
        {
            //replaces value
            int node = index - treeFrom + length;
            val[node] = delta;
            for (node >>= 1; node > 0; node >>= 1)
                val[node] = comb(val[node << 1], val[(node << 1) + 1]);
        }
        public int query(int from, int to)
        {
            //inclusive bounds
            if (to < from)
                return 0; //0 or 1?
            from += length - treeFrom;
            to += length - treeFrom + 1;
            //0 or 1?
            int res = 1;
            for (; from + (from & -from) <= to; from += from & -from)
                res = comb(res, val[from / (from & -from)]);
            for (; to - (to & -to) >= from; to -= to & -to)
                res = comb(res, val[(to - (to & -to)) / (to & -to)]);
            return res;
        }
        public int comb(int a, int b)
        {
            //change this
            return  a * b;
        }
    }
   public static int upper_bound(int[] ar,int k)
       {
           int s=0;
           int e=ar.length;
           while (s !=e)
           {
               int mid = s+e>>1;
               if (ar[mid] <=k)
               {
                   s=mid+1;
               }
               else
               {
                   e=mid;
               }
           }

           return s;
       }

   public static int lower_bound(int[] ar,int k)
   {
       int s=0;
       int e=ar.length;
       while (s !=e)
       {
           int mid = s+e>>1;
           if (ar[mid] <k)
           {
               s=mid+1;
           }
           else
           {
               e=mid;
           }
       }

       return s;
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
        //I don't understand how this works lmao
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