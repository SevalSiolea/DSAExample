package Interface.List;

import java.util.Iterator;

public interface List<AnyType> extends Iterable<AnyType> {


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    int size();

    boolean isEmpty();

    void clear();

    /*-------------------------------------------------------------------------------*/

    boolean contains( AnyType element );

    boolean add( AnyType element );

    boolean remove( AnyType element );

    /*-------------------------------------------------------------------------------*/

    AnyType get( int idx );

    AnyType set( int idx, AnyType element );

    void insert( int idx, AnyType element );

    AnyType removeAt( int idx );

    /*-------------------------------------------------------------------------------*/

    Iterator<AnyType> iterator();


}