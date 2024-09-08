package Implementation.Tree;

import Interface.Tree.BinarySearchTreeInterface;

public class BalancedTree<AnyType extends Comparable<? super AnyType>>
        implements BinarySearchTreeInterface<AnyType> {


    /*-------------------------------------------------------------------------------*/
    /*================================ private field ================================*/
    /*-------------------------------------------------------------------------------*/


    private BinaryNode<AnyType> root;
    private boolean removeRight;


    /*-----------------------------------------------------------------------------*/
    /*================================ constructor ================================*/
    /*-----------------------------------------------------------------------------*/


    public BalancedTree() { clear(); }


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

        return balance( node );
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

        return balance( node );
    }

    /*--------------------------------------------------------------------------------*/

    private BinaryNode<AnyType> balance( BinaryNode<AnyType> node ) {
        if( node == null ) return null;

        if( height( node.left ) - height( node.right ) > 1 ) {
            if( height( node.left.left ) >= height( node.left.right ) )
                node = leftRotate( node );
            else
                node = doubleLeftRotate( node );
        } else if( height( node.right ) - height( node.left ) > 1 ) {
            if( height( node.right.right ) >= height( node.right.left ) )
                node = rightRotate( node );
            else
                node = doubleRightRotate( node );
        }

        countHeight( node );
        return node;
    }

    private BinaryNode<AnyType> leftRotate( BinaryNode<AnyType> node ) {
        BinaryNode<AnyType> child = node.left;
        node.left = child.right;
        child.right = node;
        countHeight( node );
        countHeight( child );
        return child;
    }

    private BinaryNode<AnyType> rightRotate( BinaryNode<AnyType> node ) {
        BinaryNode<AnyType> child = node.right;
        node.right = child.left;
        child.left = node;
        countHeight( node );
        countHeight( child );
        return child;
    }

    private BinaryNode<AnyType> doubleLeftRotate( BinaryNode<AnyType> node ) {
        node.left = rightRotate( node.left );
        return leftRotate( node );
    }

    private BinaryNode<AnyType> doubleRightRotate( BinaryNode<AnyType> node ) {
        node.right = leftRotate( node.right );
        return rightRotate( node );
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

    /*--------------------------------------------------------------------------------*/

    private void countHeight( BinaryNode<AnyType> node ) {
        int leftHeight = height( node.left );
        int rightHeight = height( node.right );
        node.height = Math.max( leftHeight, rightHeight ) + 1;
    }

    private int height( BinaryNode<AnyType> node ) { return node == null ? -1 : node.height; }


    /*-----------------------------------------------------------------------------*/
    /*================================ inner class ================================*/
    /*-----------------------------------------------------------------------------*/


    private static class BinaryNode<AnyType> {

        public AnyType element;
        public BinaryNode<AnyType> left;
        public BinaryNode<AnyType> right;
        public int height;

        public BinaryNode( AnyType element ) { this( element, null, null ); }
        public BinaryNode( AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right ) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }

    }


}