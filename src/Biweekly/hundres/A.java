package Biweekly.hundres;

import com.sun.source.tree.Tree;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import static java.lang.System.out;

public class A {
    public static void main(String[] args) {
       long answer = distMoney(9,2);


    } public static long findScore(int[] nums) {
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        for(int i = 0; i < nums.length; i++){
            int x = nums[i];
            map.putIfAbsent(x, new TreeSet<>());
            map.get(x).add(i);
        }

        int n = nums.length;
        long answer= 0;
        while(!map.isEmpty()){
            Integer first = map.firstKey();
            TreeSet<Integer> set = map.get(first);
            Integer index = set.pollFirst();

            if(set.isEmpty()) map.remove(first);
            if(index== null) continue;
            answer += first;
            if(index - 1 >= 0 ){
                int left = nums[index-1];
                TreeSet<Integer> leftset = map.getOrDefault(left,null);
                if(leftset != null) {
                    leftset.remove(index - 1);
                    if (leftset.isEmpty()) map.remove(left);
                }
            }
            if(index  + 1 < n){
                int right = nums[index+1];

                TreeSet<Integer> leftset = map.getOrDefault(right,null);

                if(leftset != null) {
                    leftset.remove(index + 1);
                    if (leftset.isEmpty()) map.remove(right);
                }
            }

        }
        return answer;
    }
    public static int distMoney(int money, int children) {
        int div = money/8;
        if(div > children) return children-1;
        if(div == children ) return children;
        if(money < children) return -1;
        if(money == children) return 0;
        int answer = 0;
        while(children>= 0){
            if(money - 8 >= 0 && money - 8 != 4 ){
                answer++;
                money-=8;
            }else return answer;
            children-=1;
        }

        return 0;

    }
    private static int allFourMatrix(char[][] arr, int i, int j) {
        int n = arr.length;
        int left = (i - 1) < 0 ? 0 : (arr[i - 1][j] == 'o' ? 1 : 0);
        int right = (i + 1) >= n ? 0 : (arr[i + 1][j] == 'o' ? 1 : 0);

        int up = (j - 1) < 0 ? 0 : (arr[i][j - 1] == 'o' ? 1 : 0);
        int down = (j + 1) >= n ? 0 : (arr[i][j + 1] == 'o' ? 1 : 0);
        return left + right + up + down;
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

    private static void readarr(int[] arr, FastScanner sc, int n) {


    }

    static long Ceil(long a, long b) {
        if (a % b != 0) {
            return (a / b) + 1;
        } else {
            return (a / b);
        }
    }

    static int intCeil(int a, int b) {
        if (a % b != 0) {
            return (a / b) + 1;
        } else {
            return (a / b);
        }
    }

    private static int[] sieveEratosthenes(int n) {
        if (n <= 32) {
            int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
            for (int i = 0; i < primes.length; i++) {
                if (n < primes[i]) {
                    return Arrays.copyOf(primes, i);
                }
            }
            return primes;
        }

        int u = n + 32;
        double lu = Math.log(u);
        int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
        ret[0] = 2;
        int pos = 1;

        int[] isp = new int[(n + 1) / 32 / 2 + 1];
        int sup = (n + 1) / 32 / 2 + 1;

        int[] tprimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        for (int tp : tprimes) {
            ret[pos++] = tp;
            int[] ptn = new int[tp];
            for (int i = (tp - 3) / 2; i < tp << 5; i += tp)
                ptn[i >> 5] |= 1 << (i & 31);
            for (int i = 0; i < tp; i++) {
                for (int j = i; j < sup; j += tp)
                    isp[j] |= ptn[i];
            }
        }

        // 3,5,7
        // 2x+3=n
        int[] magic = {0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4,
                13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14};
        int h = n / 2;
        for (int i = 0; i < sup; i++) {
            for (int j = ~isp[i]; j != 0; j &= j - 1) {
                int pp = i << 5 | magic[(j & -j) * 0x076be629 >>> 27];
                int p = 2 * pp + 3;
                if (p > n)
                    break;
                ret[pos++] = p;
                for (int q = pp; q <= h; q += p)
                    isp[q >> 5] |= 1 << (q & 31);
            }
        }

        return Arrays.copyOf(ret, pos);
    }

    private static void input(int[] arr, int n, FastScanner sc) {
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
    }

    private static void no() {
        out.println("No");
    }

    private static void yes() {
        out.println("Yes");
    }


    public static int[] readArr(int N, BufferedReader infile, StringTokenizer st) throws Exception {
        int[] arr = new int[N];
        st = new StringTokenizer(infile.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        return arr;
    }

    public static void print(int[] arr) {
        //for debugging only
        for (int x : arr)
            out.print(x + " ");
        out.println();
    }

    //input shenanigans
      /*
         Random stuff to try when stuck:
            -if it's 2C then it's dp
            -for combo/probability problems, expand the given form we're interested in
            -make everything the same then build an answer (constructive, make everything 0 then do something)
            -something appears in parts of 2 --> model as graph
            -assume a greedy then try to show why it works
            -find way to simplify into one variable if multiple exist
            -treat it like fmc (note any passing thoughts/algo that could be used so you can revisit them)
            -find lower and upper bounds on answer
            -figure out what ur trying to find and isolate it
            -see what observations you have and come up with more continuations
            -work backwards (in constructive, go from the goal to the start)
            -turn into prefix/suffix sum argument (often works if problem revolves around adjacent array elements)
            -instead of solving for answer, try solving for complement (ex, find n-(min) instead of max)
            -draw something
            -simulate a process
            -dont implement something unless if ur fairly confident its correct
            -after 3 bad submissions move on to next problem if applicable
            -do something instead of nothing and stay organized
            -write stuff down
         Random stuff to check when wa:
            -if code is way too long/cancer then reassess
            -switched N/M
            -int overflow
            -switched variables
            -wrong MOD
            -hardcoded edge case incorrectly
         Random stuff to check when tle:
            -continue instead of break
            -condition in for/while loop bad
         Random stuff to check when rte:
            -switched N/M
            -long to int/int overflow
            -division by 0
            -edge case for empty list/data structure/N=1
      */
    public static long[] readArr2(int N, BufferedReader infile, StringTokenizer st) throws Exception {
        long[] arr = new long[N];
        st = new StringTokenizer(infile.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Long.parseLong(st.nextToken());
        return arr;
    }

    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) return false;
        }
        return true;
    }

    public static long gcd(long a, long b) {
        if (a > b)
            a = (a + b) - (b = a);
        if (a == 0L)
            return b;
        return gcd(b % a, a);
    }

    public static long lcm(long x, long y) {
        return (x * (y / gcd(x, y)));
    }

    public static long totient(long n) {
        long result = n;
        for (int p = 2; p * p <= n; ++p)
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

    //****CLASS PAIR ************************************************
    private static class Pair implements Comparable<Pair> {
        int src;
        long a;
        long b;

        Pair(int src, long a, long b) {
            this.src = src;
            this.a = a;
            this.b = b;
        }

        public int compareTo(Pair o) {
            return (int) (this.b - o.b);

        }

    }
//****CLASS PAIR **************************************************


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
        private int BS = 1 << 16;
        private char NC = (char) 0;
        private byte[] buf = new byte[BS];
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



