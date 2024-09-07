package Implementation.HashTable;

import java.util.List;
import java.util.LinkedList;

import Interface.HashTable.HashTable;
import Implementation.Prime;

public class SeparateChainingHashTable<AnyType> implements HashTable<AnyType> {


    private static final int DEFAULT_CAPACITY = 101;


    /*-------------------------------------------------------------------------------*/
    /*================================ private field ================================*/
    /*-------------------------------------------------------------------------------*/


    private List<AnyType>[] table;
    private int size;


    /*-----------------------------------------------------------------------------*/
    /*================================ constructor ================================*/
    /*-----------------------------------------------------------------------------*/


    public SeparateChainingHashTable() { this( DEFAULT_CAPACITY ); }

    public SeparateChainingHashTable( int capacity ) { rehash( capacity ); }


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public boolean insert( AnyType element ) {
        List<AnyType> list = this.table[ hashCount( element ) ];
        boolean contains = true;
        if( !list.contains( element ) ) {
            contains = false;
            list.add( element );
            if( ++this.size >= this.table.length )
                rehash( this.table.length * 2 );
        }
        return !contains;
    }

    public boolean contains( AnyType element ) {
        List<AnyType> list = this.table[ hashCount( element ) ];
        return list.contains( element );
    }

    public boolean remove( AnyType element ) {
        List<AnyType> list = this.table[ hashCount( element ) ];
        boolean contains = false;
        if( list.contains( element ) ) {
            contains = true;
            list.remove( element );
            this.size--;
        }
        return !contains;
    }

    public void clear() { clear( DEFAULT_CAPACITY ); }


    /*--------------------------------------------------------------------------------*/
    /*================================ private method ================================*/
    /*--------------------------------------------------------------------------------*/


    private void rehash( int capacity ) {

        List<AnyType>[] oldTable = this.table;
        clear( capacity );

        for( int i=0; i < oldTable.length; i++ )
            for( AnyType element : oldTable[ i ] )
                insert( element );
    }

    private int hashCount( AnyType element ) {
        int hashCode = element.hashCode();
        hashCode %= this.table.length;
        hashCode += hashCode < 0 ? this.table.length : 0;
        return hashCode;
    }

    private void clear( int capacity ) {
        this.table = new LinkedList[ Prime.nextLittlePrime( capacity ) ];
        for( int i=0; i < this.table.length; i++ )
            this.table[ i ] = new LinkedList<>();
        this.size = 0;
    }


}