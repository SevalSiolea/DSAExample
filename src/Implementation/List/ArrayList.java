package Implementation.List;

import java.util.Iterator;

import Interface.List.List;

public class ArrayList<AnyType> implements List<AnyType> {


    private static final int DEFAULT_CAPACITY = 11;


    /*-------------------------------------------------------------------------------*/
    /*================================ private field ================================*/
    /*-------------------------------------------------------------------------------*/


    private AnyType[] elements;
    private int size;

    private int modifyCount;


    /*-----------------------------------------------------------------------------*/
    /*================================ constructor ================================*/
    /*-----------------------------------------------------------------------------*/


    public ArrayList() { this.modifyCount = 0; clear(); }


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public AnyType get( int idx ) {
        if( idx < 0 || idx >= size() )
            throw new IndexOutOfBoundsException();

        return this.elements[ idx ];
    }

    public AnyType set( int idx, AnyType element ) {
        if( idx < 0 || idx >= size() )
            throw new IndexOutOfBoundsException();

        AnyType old = this.elements[ idx ];
        this.elements[ idx ] = element;

        return old;
    }

    public void insert( int idx, AnyType element ) {
        if( idx < 0 || idx >= size() )
            throw new IndexOutOfBoundsException();
        if( size() == this.elements.length )
            ensureCapacity( size() * 2 + 1 );

        for( int i = size(); i > idx; i-- )
            this.elements[ i ] = this.elements[ i-1 ];
        this.elements[ idx ] = element;

        this.size++;
        this.modifyCount++;
    }

    public AnyType removeAt( int idx ) {
        if( idx < 0 || idx >= size() )
            throw new IndexOutOfBoundsException();

        AnyType removed = this.elements[ idx ];
        for( int i = idx; i < size() - 1; i++ )
            this.elements[ i ] = this.elements[ i+1 ];

        this.size--;
        this.modifyCount++;
        return removed;
    }

    /*-------------------------------------------------------------------------------*/

    public Iterator<AnyType> iterator() { return new ArrayListIterator(); }

    /*-------------------------------------------------------------------------------*/

    public boolean contains( AnyType element ) {
        for( AnyType match : this )
            if( match.equals( element ) )
                return true;
        return false;
    }

    public boolean add( AnyType element ) {
        insert( size(), element );
        return true;
    }

    public boolean remove( AnyType element ) {
        boolean removed = false;
        Iterator<AnyType> itr = iterator();
        while( itr.hasNext() )
            if( itr.next().equals( element ) ) {
                itr.remove();
                removed = true;
            }
        return removed;
    }

    /*-------------------------------------------------------------------------------*/

    public int size() { return this.size; }

    public boolean isEmpty() { return size() == 0; }

    public void clear() {
        this.size = 0;
        this.elements = (AnyType[]) new Object[ DEFAULT_CAPACITY ];
        this.modifyCount++;
    }

    /*-------------------------------------------------------------------------------*/

    public void ensureCapacity( int capacity ) {
        if( capacity < size() ) return;

        AnyType[] old = this.elements;
        this.elements = (AnyType[]) new Object[ capacity ];
        for( int i=0; i < size(); i++ )
            this.elements[ i ] = old[ i ];
    }

    public void trimToSize() { ensureCapacity( size() ); }


    /*-----------------------------------------------------------------------------*/
    /*================================ inner class ================================*/
    /*-----------------------------------------------------------------------------*/


    private class ArrayListIterator implements Iterator<AnyType> {

        public int current;
        public boolean canRemove;
        public int expectedModifyCount;

        public ArrayListIterator() {
            this.current = 0;
            this.canRemove = false;
            this.expectedModifyCount = modifyCount;
        }

        public boolean hasNext() {
            if( expectedModifyCount != modifyCount )
                throw new java.util.ConcurrentModificationException();

            return this.current < size();
        }

        public AnyType next() {
            if( expectedModifyCount != modifyCount )
                throw new java.util.ConcurrentModificationException();
            if( !hasNext() )
                throw new java.util.NoSuchElementException();

            this.canRemove = true;
            return elements[ current++ ];
        }

        public void remove() {
            if( expectedModifyCount != modifyCount )
                throw new java.util.ConcurrentModificationException();
            if( !canRemove )
                throw new IllegalStateException();

            ArrayList.this.removeAt( --current );
            this.canRemove = false;
            this.expectedModifyCount++;
        }

    }


}