package Implementation.HashTable;

import Interface.HashTable.HashTable;
import Implementation.Prime;

public class QuadraticProbingHashTable<AnyType> implements HashTable<AnyType> {


    private static final int DEFAULT_CAPACITY = 101;


    /*-------------------------------------------------------------------------------*/
    /*================================ private field ================================*/
    /*-------------------------------------------------------------------------------*/


    private HashItem<AnyType>[] table;
    private int size;


    /*-----------------------------------------------------------------------------*/
    /*================================ constructor ================================*/
    /*-----------------------------------------------------------------------------*/


    public QuadraticProbingHashTable() { this( DEFAULT_CAPACITY ); }

    public QuadraticProbingHashTable( int capacity ) { clear( capacity );}


    /*-------------------------------------------------------------------------------*/
    /*================================ public method ================================*/
    /*-------------------------------------------------------------------------------*/


    public boolean insert( AnyType element ) {
        int pos = findPos( element );
        if( active( pos ) ) return false;

        this.table[ pos ] = new HashItem<>( element );
        if( ++this.size > this.table.length / 2 )
            rehash( this.table.length * 2 );
        return true;
    }

    public boolean contains( AnyType element ) {
        int pos = findPos( element );
        return active( pos );
    }

    public boolean remove( AnyType element ) {
        int pos = findPos( element );
        if( !active( pos ) ) return false;
        this.table[ pos ].removed = true;
        this.size--;
        return true;
    }

    public void clear() { clear( DEFAULT_CAPACITY ); }


    /*--------------------------------------------------------------------------------*/
    /*================================ private method ================================*/
    /*--------------------------------------------------------------------------------*/


    private int findPos( AnyType element ) {
        int hashCount = hashCount( element );

        int offset = 1;
        int pos = hashCount;
        while( this.table[ pos ] != null
                && !this.table[ pos ].element.equals( element ) ) {
            pos += offset;
            offset += 2;
            pos -= pos >= this.table.length ? this.table.length : 0;
            if( pos == hashCount ) break;
        }

        if( pos == hashCount ) {
            rehash( this.table.length );
            return findPos( element );
        } else
            return pos;
    }

    private int hashCount( AnyType element ) {
        int hashCode = element.hashCode();
        hashCode %= this.table.length;
        hashCode += hashCode < 0 ? this.table.length : 0;
        return hashCode;
    }

    private void rehash( int capacity ) {
        HashItem<AnyType>[] oldTable = this.table;
        clear( Prime.nextLittlePrime( capacity ) );

        for( HashItem<AnyType> hashItem : oldTable )
            if( hashItem != null && !hashItem.removed )
                insert( hashItem.element );
    }

    private void clear( int capacity ) {
        this.table = new HashItem[ Prime.nextLittlePrime( capacity ) ];
        java.util.Arrays.fill( this.table, null );
        this.size = 0;
    }

    private boolean active( int pos ) {
        return this.table[ pos ] != null && !this.table[ pos ].removed;
    }


    /*-----------------------------------------------------------------------------*/
    /*================================ inner class ================================*/
    /*-----------------------------------------------------------------------------*/


    private static class HashItem<AnyType> {
        public AnyType element;
        public boolean removed;

        public HashItem( AnyType element ) { this( element, false );}
        public HashItem( AnyType element, boolean removed ) {
            this.element = element;
            this.removed = removed;
        }
    }


}