package Implementation.List;

import Interface.List.Queue;

public class ArrayQueue<AnyType> implements Queue<AnyType> {


    private static final int DEFAULT_CAPACITY = 11;


    /*-------------------------------------------------------------------------------*/
    /*================================ private field ================================*/
    /*-------------------------------------------------------------------------------*/


    private AnyType[] elements;
    private int head;
    private int tail;
    private int size;


    /*-----------------------------------------------------------------------------*/
    /*================================ constructor ================================*/
    /*-----------------------------------------------------------------------------*/


    public ArrayQueue() { clear(); }


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public void enQueue( AnyType element ) {
        if( size() >= this.elements.length )
            ensureCapacity( size() * 2 + 1 );

        this.tail = ( this.tail + 1 ) % this.elements.length;
        this.elements[ this.tail ] = element;
        this.size++;
    }

    public AnyType deQueue() {
        if( size() == 0 )
            throw new java.util.NoSuchElementException();

        AnyType element = this.elements[ this.head ];
        this.head = ( this.head + 1 ) % this.elements.length;
        this.size--;
        return element;
    }

    /*-------------------------------------------------------------------------------*/

    public int size() { return this.size; }

    public boolean isEmpty() { return size() == 0; }

    public void clear() {
        this.elements = (AnyType[]) new Object[ DEFAULT_CAPACITY ];
        this.head = 0;
        this.tail = this.elements.length - 1;
        this.size = 0;
    }

    /*-------------------------------------------------------------------------------*/

    public void ensureCapacity( int capacity ) {
        if( capacity < size() ) return;

        AnyType[] old = this.elements;
        this.elements = (AnyType[]) new Object[ capacity ];
        for( int i=0; i < size(); i++ )
            this.elements[ i ] = old[ ( this.head + i ) % old.length ];

        this.head = 0;
        this.tail = size() - 1;
    }

    public void trimToSize() { ensureCapacity( size() ); }


}