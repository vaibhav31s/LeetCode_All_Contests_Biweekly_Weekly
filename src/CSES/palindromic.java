package CSES;

import java.lang.reflect.Array;
import java.util.*;

class Solution {
    static ArrayList<Integer> list = generatePalindromes(1000000001);

    public static void main(String[] args) {



        generatePalindromes(1000000001);
        Collections.sort(list);
//        System.out.println(minimumCost(new int[] {1,2,3,4,5}));
//        System.out.println(minimumCost(new int[] {10,12,13,14,15}));
//        System.out.println(minimumCost(new int[] {22,33,22,33,22}));
//        System.out.println(minimumCost(new int[] {5,    2,1})); //4answer
//        System.out.println(minimumCost(new int[] {201, 202, 204, 218, 223, 224}));
//          System.out.println(minimumCost(new int[] {239,238,449,950,613,398,995,748,195,770,395}));//2616\
        //26754699997
        System.out.println(minimumCost(new int[]{493394,291777192,2521252,443787344,152959251,531656135,144868441,563777365,3527253,227020722,250868052,5762675,899191998,4694964,358838853,641747146,231404132,85155158,371141173,656191656,722060227,877474778,324878423,414848414,92029,940282049,95844859,50511505,997919799,827565728,731373137,654222456,790151097,5121215,733424337,3600063,945252549,140626041,563252365,249717942,930585039,363151363,905797509,447090744,622020226,303616303,411525114,68222286,900272009,642949246,21244212,321878123,908373809,951434159,645070546,292878292,430151034,56277265,824212428,395373593,845454548,468202864,2038302,542202245,168111861,260969062,203656302,8138318,625909526,448858844,318030813,7821287,120666021,825303528,217848712,51199115,639585936,79397,556383655,730434037,1272721,1321231,431777134,304333403,148515841,69844896,786434687,778717877,754242457,446323644,462141264,991626199,402939204,85866858,692464296,685585586,502373205,611828116,631727136,236767632}));
    }

    public static long minimumCost(int[] nums) {
        long answer = Long.MAX_VALUE;
        long start = 0;
        long end = Long.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int x : nums) {
            min = Math.min(x, min);
            max = Math.max(x, max);
        }

        int first = BinarySearch.bsearchFloor(list, 0, list.size() - 1, min);
        int second = BinarySearch.bsearchCeil(list, 0, list.size() - 1, max);

        int mid = first + (second - first) /2;
        int dif= max - min;
//        System.out.println(list.get(mid));
        for(int i = mid; i >= (mid - 100000) && i >= 0;i--) {
            answer = Math.min( getDiff(nums, list.get(i)), answer);
        }

        for(int i = mid; i <= (mid + 100000) && (i) < list.size(); i++) {
            answer = Math.min( getDiff(nums, list.get(i)), answer);
        }
//            System.out.println(list.get(mid) + " " + getDiff(nums, list.get(mid)));
        return answer;
    }

    private static class BinarySearch {
      public static int bsearch(int[] a, int lo, int hi, int val) {
        while (lo <= hi) {
          int mid = lo + ((hi - lo) >> 1);
          if (a[mid] == val)
            return mid;
          else if (a[mid] < val)
            lo = mid + 1;
          else
            hi = mid - 1;
        }
        return -1;
      }

      public static int bsearchCeil(ArrayList<Integer> a, int lo, int hi, int val) {
        while (lo <= hi) {
          int mid = lo + ((hi - lo) >> 1);
          if (list.get(mid) < val)
            lo = mid + 1;
          else
            hi = mid - 1;
        }
        return lo;
      }

      public static int bsearchFloor(ArrayList<Integer> a, int lo, int hi, int val) {
        while (lo <= hi) {
          int mid = lo + ((hi - lo) >> 1);
          if (list.get(mid) <= val)
            lo = mid + 1;
          else
            hi = mid - 1;
        }
        return hi;
      }


    }
    private static long getDiff(int[] nums, int i) {
        long dif = 0;
        for(int x : nums) {
            dif += Math.abs(x- i);
        }
        return dif;
    }
    static ArrayList<Integer> generatePalindromes(int n) {
        int number;


        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < 2; j++) {

            int i = 1;
            while ((number = createPalindrome(i, 10, j % 2)) < n) {
                list.add(number);
                i++;
            }
        }
        return list;
    }
    static int createPalindrome(int input, int b, int isOdd) {
        int n = input;
        int palin = input;
        if (isOdd == 1)
            n /= b;

        while (n > 0) {
            palin = palin * b + (n % b);
            n /= b;
        }
        return palin;
    }


}

class FoodRatings {
    HashMap<String, TreeMap<Integer, TreeSet<String>>> map;
    HashMap<String, String> foodToCountry = new HashMap<>();
    public FoodRatings(String[] foods, String[] cuisines, int[] rating) {
        map = new HashMap<>();
        foodToCountry = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {
            map.putIfAbsent(cuisines[i], new TreeMap<>());
            map.get(cuisines[i]).putIfAbsent(rating[i], new TreeSet<>());
            map.get(cuisines[i]).get(rating[i]).add(foods[i]);
            foodToCountry.put(foods[i], cuisines[i]);
        }
    }

    public void changeRating(String food, int newRating) {
        int rating = 0;
        String country = foodToCountry.get(food);
        int curRating = 0;
        TreeMap<Integer, TreeSet<String>>  value = map.get(country);
        boolean isRemove = false;
        for(int rate : value.keySet()) {
            value.get(rate).remove(food);
            if(value.get(rate).isEmpty()) {
                isRemove = true;
                rating = rate;
            }
        }
        if(isRemove)        map.get(country).remove(rating);



        map.get(country).putIfAbsent(newRating, new TreeSet<>());
        map.get(country).get(newRating).add(food);


    }


    public String highestRated(String country) {
        TreeMap<Integer, TreeSet<String>>  value = map.get(country);
        int last = value.lastEntry().getKey();
        TreeSet<String> foods = value.lastEntry().getValue();

        return foods.first();

    }


}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */