package Implementation;

public class Prime {


    public static final int MAX_PRIME = 10007;

    public static final int[] littlePrimeFactors = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public static boolean isLittlePrime( int num ) {
        for( int littlePrimeFactor : littlePrimeFactors )
            if( num % littlePrimeFactor == 0 )
                return true;
        return false;
    }

    public static int nextLittlePrime( int num ) {
        for( int nextNum = num; nextNum <= MAX_PRIME; nextNum++ )
            if( isLittlePrime( nextNum ) )
                return nextNum;
        return -1;
    }


}