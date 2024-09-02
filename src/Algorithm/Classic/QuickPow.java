package Algorithm.Classic;

import java.math.BigInteger;

public class QuickPow {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public static BigInteger quickPow( BigInteger base, int pow ) {
        if( pow == 0 ) return new BigInteger( "1" );
        if( pow == 1 ) return base;

        if( pow % 2 == 0 )
            return quickPow( base.multiply( base ), pow / 2 );
        else
            return quickPow( base.multiply( base ), pow / 2 ).multiply( base );
    }


}