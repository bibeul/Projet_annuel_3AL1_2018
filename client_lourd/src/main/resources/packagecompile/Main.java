package packagecompile;


public class Main {

    public static void main(String[] args) {

        System.out.println("TEST 1 : " + test1());
        System.out.println("TEST 2 : " + test2());
        



        }
        public static boolean test1(){ Math math = new Math(); if(math.sum(7,8)==15){ return true;}else{return false ;}}

        public static boolean test2(){ Math math = new Math(); if(math.sum(17,8)==25){ return true;}else{return false ;}}

        
}
