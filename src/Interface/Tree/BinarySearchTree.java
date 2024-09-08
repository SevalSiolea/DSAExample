package Interface.Tree;

public interface BinarySearchTree<AnyType extends Comparable<? super AnyType>> {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    boolean contains( AnyType element );

    void insert( AnyType element );

    void remove( AnyType element );

    /*-------------------------------------------------------------------------------*/

    AnyType findMin();

    AnyType findMax();

    void printTree();

    /*-------------------------------------------------------------------------------*/

    void clear();

    boolean isEmpty();


}