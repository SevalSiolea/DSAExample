package Interface.HashTable;

public interface HashTable<AnyType> {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    boolean insert( AnyType element );

    boolean contains( AnyType element );

    boolean remove( AnyType element );

    void clear();


}