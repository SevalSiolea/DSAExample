package Implementation.List;

import Interface.List.Stack;

public class LinkedStack<AnyType> implements Stack<AnyType> {


    /*-------------------------------------------------------------------------------*/
    /*================================ private field ================================*/
    /*-------------------------------------------------------------------------------*/


     private Node<AnyType> topOfStack;


    /*-----------------------------------------------------------------------------*/
    /*================================ constructor ================================*/
    /*-----------------------------------------------------------------------------*/


    public LinkedStack() { clear(); }


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public void push( AnyType element ) {
        Node<AnyType> node = new Node<>( element, this.topOfStack );
        this.topOfStack = node;
    }

    public AnyType pop() {
        if( isEmpty() )
            throw new java.util.EmptyStackException();

        AnyType element = this.topOfStack.element;
        this.topOfStack = this.topOfStack.next;
        return element;
    }

    public AnyType top() {
        return this.topOfStack.element;
    }

    /*-------------------------------------------------------------------------------*/

    public boolean isEmpty() { return this.topOfStack == null; }

    public void clear() { this.topOfStack = null; }


    /*-----------------------------------------------------------------------------*/
    /*================================ inner class ================================*/
    /*-----------------------------------------------------------------------------*/


    private static class Node<AnyType> {

        public AnyType element;
        public Node<AnyType> next;

        public Node( AnyType element, Node<AnyType> next ) {
            this.element = element;
            this.next = next;
        }

    }


}