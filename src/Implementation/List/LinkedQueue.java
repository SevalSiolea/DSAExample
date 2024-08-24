package Implementation.List;

import Interface.List.Queue;

public class LinkedQueue<AnyType> implements Queue<AnyType> {


    /*-------------------------------------------------------------------------------*/
    /*================================ private field ================================*/
    /*-------------------------------------------------------------------------------*/


    private Node<AnyType> head;
    private Node<AnyType> tail;
    private int size;


    /*-----------------------------------------------------------------------------*/
    /*================================ constructor ================================*/
    /*-----------------------------------------------------------------------------*/


    public LinkedQueue() { clear(); }


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public void enQueue( AnyType element ) {
        Node<AnyType> node = new Node<>( element, null );
        this.tail.next = node;
        this.tail = node;

        this.size++;
    }

    public AnyType deQueue() {
        if( isEmpty() )
            throw new java.util.NoSuchElementException();

        Node<AnyType> node = this.head.next;
        this.head.next = node.next;

        this.size--;
        return node.element;
    }

    /*-------------------------------------------------------------------------------*/

    public int size() { return this.size; }

    public boolean isEmpty() { return size() == 0; }

    public void clear() {
        this.head = this.tail = new Node<>( null, null );
        this.size = 0;
    }


    /*-----------------------------------------------------------------------------*/
    /*================================ inner class ================================*/
    /*-----------------------------------------------------------------------------*/


    private static class Node<AnyType> {

        public AnyType element;
        public Node<AnyType> next;

        public Node(AnyType element, Node<AnyType> next ) {
            this.element = element;
            this.next = next;
        }

    }


}