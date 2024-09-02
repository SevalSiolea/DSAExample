package Algorithm.Classic;

import Implementation.List.ArrayStack;

public class InfixToPostfix {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public static String infixToPostfix( String infix ) {
        String[] strs = infix.split( "" );
        ArrayStack<String> stack = new ArrayStack<>();
        StringBuilder result = new StringBuilder();

        for( String symbol : strs ) {
            if( symbol.equals( "(" ) )
                stack.push( symbol );
            else if( symbol.equals( ")" ) ) {
                while( !stack.top().equals( "(" ) )
                    result.append( stack.pop() );
                stack.pop();
            } else if( symbol.equals( "+" ) || symbol.equals( "-" ) ) {
                while( !stack.isEmpty() && !stack.top().equals( "(" ) )
                    result.append( stack.pop() );
                stack.push( symbol );
            } else if( symbol.equals( "*" ) || symbol.equals( "/" ) ) {
                while( !stack.isEmpty() && stack.top().equals( symbol ) )
                    result.append( stack.pop() );
                stack.push( symbol );
            } else
                result.append( symbol );
        }

        while( !stack.isEmpty() )
            result.append( stack.pop() );
        return result.toString();
    }


}