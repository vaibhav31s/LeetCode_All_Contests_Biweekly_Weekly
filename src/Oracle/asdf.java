package Oracle;

public class asdf {
    public static void main(String[] args) {
//        C obj = new C();
//        A obj1 = new C();
        B obj2 = new C();
        obj2.print();




    }
}

class A{
    A(){
        System.out.println("Inside Constructor A");
    }

    void print(){
        System.out.println("Inside A");
    }
}
class B extends A{
    B(){
        System.out.println("Inside Constructor B");
    }
    @Override
    void print(){
        System.out.println("Inside B");
    }
}

class C extends B{
    C(){
        System.out.println("Inside Constructor C");
    }
    void printC(){
        System.out.println("Inside C");
    }
}
