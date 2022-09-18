package Biweekly.Contest87;

import java.util.Arrays;

public class A {
    public static void main(String[] args) {
        int a = countDaysTogether("10-01",
                "10-31",
                "11-01",
                "12-31");
        System.out.println(a);
    }
    public static int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] prefix = new int[13];
        for(int i = 1; i <= 12; i++){
            prefix[i] =  prefix[i-1] + months[i-1];
        }
        System.out.println(Arrays.toString(prefix));
        int[] days = new int[365];
        int  aliceStartMonth  = Integer.parseInt(arriveAlice.substring(0,2));
        int  aliceStartDay = Integer.parseInt(arriveAlice.substring(3,5));
        int aliceEndMonth =  Integer.parseInt(leaveAlice.substring(0,2));
        int  aliceEndDay =  Integer.parseInt(leaveAlice.substring(3,5));
        int   bobStartMonth = Integer.parseInt(arriveBob.substring(0,2));
        int bobStartDay = Integer.parseInt(arriveBob.substring(3,5));
        int  bobEndMonth=  Integer.parseInt(leaveBob.substring(0,2));
        int  bobEndDay=  Integer.parseInt(leaveBob.substring(3,5));

        int starta = prefix[aliceStartMonth-1] +  aliceStartDay ;
        int enda = prefix[aliceEndMonth-1] + aliceEndDay;

        int startb = prefix[bobStartMonth-1] +  bobStartDay ;
        int endb = prefix[bobEndMonth - 1] + bobEndDay;
        if(enda < startb){
            return 0;
        }else{
            int first = Math.max(starta, startb);
            int second = Math.min(enda, endb);
            if(second- first < 0 ) return 0;
            return second-first + 1;


        }


    }
}
