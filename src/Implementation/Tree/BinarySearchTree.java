package Implementation.Tree;

import Interface.Tree.BinarySearchTreeInterface;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
        implements BinarySearchTreeInterface<AnyType> {


    /*-------------------------------------------------------------------------------*/
    /*================================ private field ================================*/
    /*-------------------------------------------------------------------------------*/


    private BinaryNode<AnyType> root;
    private boolean removeRight;


    /*-----------------------------------------------------------------------------*/
    /*================================ constructor ================================*/
    /*-----------------------------------------------------------------------------*/


    public BinarySearchTree() { clear(); }


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public boolean contains( AnyType element ) { return contains( this.root, element ); }

    public void insert( AnyType element ) { this.root = insert( this.root, element ); }

    public void remove( AnyType element ) { this.root = remove( this.root, element ); }

    /*-------------------------------------------------------------------------------*/

    public AnyType findMin() {
        if( isEmpty() ) throw new java.util.NoSuchElementException();
        return findMin( this.root );
    }

    public AnyType findMax() {
        if( isEmpty() ) throw new java.util.NoSuchElementException();
        return findMax( this.root );
    }

    public void printTree() {
        if( isEmpty() )
            System.out.println( "Empty Tree" );
        else
            printTree( this.root, 0 );
    }

    /*-------------------------------------------------------------------------------*/

    public void clear() { this.root = null; this.removeRight = true; }

    public boolean isEmpty() { return this.root == null; }


    /*--------------------------------------------------------------------------------*/
    /*================================ private method ================================*/
    /*--------------------------------------------------------------------------------*/


    private boolean contains( BinaryNode<AnyType> node, AnyType element ) {
        if( node == null ) return false;

        int compare = element.compareTo( node.element );
        if( compare < 0 )
            return contains( node.left, element );
        else if( compare > 0 )
            return contains( node.right, element );
        else
            return true;
    }

    private BinaryNode<AnyType> insert( BinaryNode<AnyType> node, AnyType element ) {
        if( node == null ) return new BinaryNode<>( element );

        int compare = element.compareTo( node.element );
        if( compare < 0 )
            node.left = insert( node.left, element );
        else if( compare > 0 )
            node.right = insert( node.right, element );

        return node;
    }

    private BinaryNode<AnyType> remove( BinaryNode<AnyType> node, AnyType element ) {
        if( node == null ) return null;

        int compare = element.compareTo( node.element );
        if( compare < 0 )
            node.left = remove( node.left, element );
        else if( compare > 0 )
            node.right = remove( node.right, element );
        else {
            if( node.left != null && node.right != null )
                if( this.removeRight ) {
                    node.element = findMin( node.right );
                    node.right = remove( node.right, node.element );
                } else {
                    node.element = findMax( node.left );
                    node.left = remove( node.left, node.element );
                }
            else
                node = node.left != null ? node.left : node.right;
        }

        return node;
    }

    /*--------------------------------------------------------------------------------*/

    private AnyType findMin( BinaryNode<AnyType> node ) {
        return node.left == null ? node.element : findMin( node.left );
    }

    private AnyType findMax( BinaryNode<AnyType> node ) {
        while( node.right != null ) node = node.right ;
        return node.element;
    }

    private void printTree( BinaryNode<AnyType> node, int depth ) {
        if( node == null ) return;

        printTree( node.left, depth + 1 );
        System.out.println( String.format( "%" + depth + "s", "    " ) + node.element );
        printTree( node.right, depth + 1 );
    }


    /*-----------------------------------------------------------------------------*/
    /*================================ inner class ================================*/
    /*-----------------------------------------------------------------------------*/


    private static class BinaryNode<AnyType> {

        public AnyType element;
        public BinaryNode<AnyType> left;
        public BinaryNode<AnyType> right;

        public BinaryNode( AnyType element ) { this( element, null, null ); }
        public BinaryNode( AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right ) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

    }


}