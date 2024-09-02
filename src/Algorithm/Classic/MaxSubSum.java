package Algorithm.Classic;

public class MaxSubSum {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public static int maxSubSum_1( int[] arr ) {
        int maxSum = 0;
        for( int i=0; i < arr.length; i++ )
            for( int j=i; j < arr.length; j++ ) {
                int currentSum = 0;
                for( int k = i; k <= j; k++ )
                    currentSum += arr[ k ];
                if( currentSum > maxSum )
                    maxSum = currentSum;
            }
        return maxSum;
    }

    public static int maxSubSum_2( int[] arr ) {
        int maxSum = 0;
        for( int i=0; i < arr.length; i++ ) {
            int currentSum = 0;
            for( int j = i; j < arr.length; j++ ) {
                currentSum += arr[ j ];
                if( currentSum > maxSum )
                    maxSum = currentSum;
            }
        }
        return maxSum;
    }

    public static int maxSubSum_3( int[] arr ) {
        return maxSubSum_3( arr, 0, arr.length - 1 );
    }

    public static int maxSubSum_4( int[] arr ) {
        int maxSum = 0;
        int currentSum = 0;
        for( int i=0; i < arr.length; i++ ) {
            currentSum += arr[ i ];
            if( currentSum > 0 )
                maxSum = Math.max( maxSum, currentSum );
            else
                currentSum = 0;
        }
        return maxSum;
    }


    /*--------------------------------------------------------------------------------*/
    /*================================ private method ================================*/
    /*--------------------------------------------------------------------------------*/


    private static int maxSubSum_3( int[] arr, int low, int high ) {
        if( low == high ) return Math.max( arr[ low ], 0 );

        int mid = ( low + high ) / 2;
        int maxLeftSum = maxSubSum_3( arr, low, mid );
        int maxRightSum = maxSubSum_3( arr, mid + 1, high );

        int maxLeftBorderSum = 0;
        int currentLeftBorderSum = 0;
        for( int i = mid; i >= low; i-- ) {
            currentLeftBorderSum += arr[ i ];
            if( currentLeftBorderSum > maxLeftBorderSum )
                maxLeftBorderSum = currentLeftBorderSum;
        }

        int maxRightBorderSum = 0;
        int currentRightBorderSum = 0;
        for( int i = mid + 1; i <= high; i++ ) {
            currentRightBorderSum += arr[ i ];
            if( currentRightBorderSum > maxRightBorderSum )
                maxRightBorderSum = currentRightBorderSum;
        }

        return Math.max( Math.max( maxLeftSum, maxRightSum ), maxLeftBorderSum + maxRightBorderSum );
    }


}