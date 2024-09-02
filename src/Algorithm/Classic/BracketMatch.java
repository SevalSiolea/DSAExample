package Algorithm.Classic;

import Implementation.List.ArrayStack;
import java.util.HashMap;

public class BracketMatch {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public boolean bracketMatch( String str ) {
        String[] strs = str.split( "" );
        ArrayStack<String> stack = new ArrayStack<>();
        HashMap<String, String> brackets = new HashMap<>();
        brackets.put( ")", "(" );
        brackets.put( "]", "[" );
        brackets.put( "}", "{" );

        for( String symbol : strs ) {
            if( brackets.containsValue( symbol ) )
                stack.push( symbol );
            if( brackets.containsKey( symbol ) )
                if( stack.isEmpty() || !stack.pop().equals( brackets.get( symbol ) ) )
                    return false;
        }
        return stack.isEmpty();
    }


}