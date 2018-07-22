package packagecompile;


public class Main {

    public static void main(String[] args) {

        System.out.println("TEST 1 : " + test1());
        System.out.println("TEST 2 : " + test2());
        System.out.println("TEST 3 : " + test3());



        }
        public static boolean test1(){ ArrayUtil arrayu = new ArrayUtil() ; int[] array = arrayu.insert(new int[]{7,8,1,14,15},1); if( array.length ==6 && array[5] ==1){ return true;}else{return false ;}}

        public static boolean test2(){ ArrayUtil arrayu = new ArrayUtil() ; int[] array = arrayu.insert(new int[]{7,8,1,14,15},16) ; if( array.length ==6 && array[5] == 16 ){ return true;}else{return false ;}}

        public static boolean test3(){ ArrayUtil arrayu = new ArrayUtil() ; int[] array = arrayu.insert(new int[]{},0) ; if( array.length==1 && array[0] ==0 ){ return true;}else{return false ;}}
}
