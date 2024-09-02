package Algorithm.Classic;

import Implementation.List.ArrayStack;

public class PostfixCalculate {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public static int postfixCalculate( String expression ) {
        String[] strs = expression.split( "" );
        ArrayStack<String> stack = new ArrayStack<>();
        for( String symbol : strs ) {
            if( !symbol.equals( "+" ) && !symbol.equals( "-" ) && !symbol.equals( "*" ) && !symbol.equals( "/" ) )
                stack.push( symbol );

            int num1 = Integer.parseInt( stack.pop() );
            int num2 = Integer.parseInt( stack.pop() );
            if( symbol.equals( "+" ) )
                stack.push( String.valueOf( num1 + num2 ) );
            else if( symbol.equals( "-" ) )
                stack.push( String.valueOf( num1 - num2 ) );
            else if( symbol.equals( "*" ) )
                stack.push( String.valueOf( num1 * num2 ) );
            else if( symbol.equals( "/" ) )
                stack.push( String.valueOf( num1 / num2 ) );
        }
        return Integer.parseInt( stack.pop() );
    }


}