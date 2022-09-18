package Weekly.Contest311;

public class A {
    public static void main(String[] args) {

    }
    public int smallestEvenMultiple(int n1) {


        int n2 = 2;
        int lcm = (n1 > n2) ? n1 : n2;


        while(true) {
            if( lcm % n1 == 0 && lcm % n2 == 0 )
                break;
            lcm++;
        }
        return lcm;
    }
}
