package Implementation.List;

import java.util.Iterator;

import Interface.List.List;

public class LinkedList<AnyType> implements List<AnyType> {


    /*-------------------------------------------------------------------------------*/
    /*================================ private field ================================*/
    /*-------------------------------------------------------------------------------*/


    private Node<AnyType> head;
    private Node<AnyType> tail;
    private int size;

    private int modifyCount;


    /*-----------------------------------------------------------------------------*/
    /*================================ constructor ================================*/
    /*-----------------------------------------------------------------------------*/


    public LinkedList() { this.modifyCount = 0; clear(); }


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public AnyType get( int idx ) {
        if( idx < 0 || idx >= size() )
            throw new IndexOutOfBoundsException();

        return getNode( idx ).element;
    }

    public AnyType set( int idx, AnyType element ) {
        if( idx < 0 || idx >= size() )
            throw new IndexOutOfBoundsException();

        Node<AnyType> node = getNode( idx );
        AnyType old = node.element;
        node.element = element;
        return old;
    }

    public void insert( int idx, AnyType element ) {
        if( idx < 0 || idx >= size() )
            throw new IndexOutOfBoundsException();

        Node<AnyType> node = getNode( idx );
        Node<AnyType> inserted = new Node<>( element, node.prev, node );
        node.prev.next = inserted;
        node.prev = inserted;

        this.size++;
        this.modifyCount++;
    }

    public AnyType removeAt( int idx ) {
        if( idx < 0 || idx >= size() )
            throw new IndexOutOfBoundsException();

        return removeNode( getNode( idx ) );
    }

    /*-------------------------------------------------------------------------------*/

    public Iterator<AnyType> iterator() { return new LinkedListIterator(); }

    /*-------------------------------------------------------------------------------*/

    public boolean contains( AnyType element ) {
        for( AnyType match : this )
            if( element.equals( match ) )
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
        this.head = new Node<>( null );
        this.tail = new Node<>( null );

        this.head.next = this.tail;
        this.tail.prev = this.head;

        this.size = 0;
        this.modifyCount++;
    }


    /*--------------------------------------------------------------------------------*/
    /*================================ private method ================================*/
    /*--------------------------------------------------------------------------------*/


    private Node<AnyType> getNode( int idx ) {
        if( idx < 0 || idx >= size() )
            throw new IndexOutOfBoundsException();

        Node<AnyType> node;
        if( idx < size() / 2 ) {
            node = this.head.next;
            for( int i=0; i < idx; i++ )
                node = node.next;
        } else {
            node = this.tail;
            for( int i = size(); i > idx; i-- )
                node = node.prev;
        }

        return node;
    }

    private AnyType removeNode( Node<AnyType> node ) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        this.size--;
        this.modifyCount++;
        return node.element;
    }


    /*-----------------------------------------------------------------------------*/
    /*================================ inner class ================================*/
    /*-----------------------------------------------------------------------------*/


    private static class Node<AnyType> {

        public AnyType element;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node( AnyType element, Node<AnyType> prev, Node<AnyType> next ) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public Node( AnyType element ) { this( element, null, null ); }

    }

    /*-----------------------------------------------------------------------------*/

    private class LinkedListIterator implements Iterator<AnyType> {

        public Node<AnyType> current;
        public boolean canRemove;
        public int expectedModifyCount;

        public LinkedListIterator() {
            current = head.next;
            canRemove = false;
            expectedModifyCount = 0;
        }

        public boolean hasNext() {
            if( expectedModifyCount != modifyCount )
                throw new java.util.ConcurrentModificationException();

            return current != tail;
        }

        public AnyType next() {
            if( expectedModifyCount != modifyCount )
                throw new java.util.ConcurrentModificationException();
            if( !hasNext() )
                throw new java.util.NoSuchElementException();

            current = current.next;

            canRemove = true;
            return current.prev.element;
        }

        public void remove() {
            if( expectedModifyCount != modifyCount )
                throw new java.util.ConcurrentModificationException();
            if( !canRemove )
                throw new IllegalStateException();

            LinkedList.this.removeNode( current.prev );

            canRemove = false;
            expectedModifyCount++;
        }

    }


}