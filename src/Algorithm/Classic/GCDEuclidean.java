package Algorithm.Classic;

public class GCDEuclidean {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public static int gcdEuclidean( int m, int n ) {
        while( n != 0 ) {
            int r = m % n;
            m = n;
            n = r;
        }
        return m;
    }


}