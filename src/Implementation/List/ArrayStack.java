package Implementation.List;

import Interface.List.Stack;

public class ArrayStack<AnyType> implements Stack<AnyType> {


    private static final int DEFAULT_CAPACITY = 11;


    /*-------------------------------------------------------------------------------*/
    /*================================ private field ================================*/
    /*-------------------------------------------------------------------------------*/


    private AnyType[] elements;
    private int topOfStack;


    /*-----------------------------------------------------------------------------*/
    /*================================ constructor ================================*/
    /*-----------------------------------------------------------------------------*/


    public ArrayStack() { clear(); }


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public void push( AnyType element ) {
        if( this.topOfStack == this.elements.length - 1 )
            ensureCapacity( this.topOfStack * 2 + 1 );

        this.elements[ ++this.topOfStack ] = element;
    }

    public AnyType pop() {
        if( isEmpty() )
            throw new java.util.EmptyStackException();

        return this.elements[ this.topOfStack-- ];
    }

    public AnyType top() { return this.elements[ this.topOfStack ]; }

    /*-------------------------------------------------------------------------------*/

    public boolean isEmpty() { return this.topOfStack == -1; }

    public void clear() {
        this.topOfStack = -1;
        this.elements = (AnyType[]) new Object[ DEFAULT_CAPACITY ];
    }

    /*-------------------------------------------------------------------------------*/

    public void ensureCapacity( int capacity ) {
        if( capacity < this.topOfStack ) return;

        AnyType[] old = this.elements;
        this.elements = (AnyType[]) new Object[ capacity ];
        for( int i=0; i < this.topOfStack; i++ )
            this.elements[ i ] = old[ i ];
    }

    public void trimToSize() { ensureCapacity( this.topOfStack ); }


}