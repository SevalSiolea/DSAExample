package Algorithm.Classic;

public class BinarySearch {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public static <AnyType extends Comparable<? super AnyType>>
    int binarySearch( AnyType[] arr, AnyType num ) {

        int low = 0;
        int high = arr.length - 1;
        while( low <= high ) {
            int mid = ( low + high ) / 2;
            if( num.compareTo( arr[ mid ] ) < 0 )
                high = mid - 1;
            else if( num.compareTo( arr[ mid ] ) > 0 )
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }


}