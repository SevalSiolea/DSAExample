package Algorithm.Classic;

import static java.util.Arrays.sort;

public class SelectionProblem {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public static <AnyType extends Comparable<? super AnyType>>
    AnyType selectionProblem_1( AnyType[] arr, int idx ) {
        sort( arr );
        return arr[ arr.length - idx ];
    }

    public static <AnyType extends Comparable<? super AnyType>>
    AnyType selectionProblem_2( AnyType[] arr, int idx ) {
        AnyType[] resultArr = (AnyType[]) new Object[ idx ];
        for( int i=0; i < idx; i++ )
            resultArr[ i ] = arr[ i ];
        sort( resultArr );

        for( int i = idx + 1; i < arr.length; i++ ) {
            AnyType num = arr[ i ];
            if( resultArr[ 0 ].compareTo( num ) >= 0 )
                continue;

            for( int j=1; j < resultArr.length; j++ )
                if( resultArr[ j ].compareTo( num ) < 0 ) {
                    resultArr[ j-1 ] = resultArr[ j ];
                    if( j == resultArr.length - 1 )
                        resultArr[ j ] = num;
                } else {
                    resultArr[ j-1 ] = num;
                    break;
                }
        }

        return resultArr[ idx ];
    }


}