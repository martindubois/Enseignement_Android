
// Auteur   Martin Dubois, ing.
// Produit  Android
// File     RTL/app/java/com/kms-quebec/rtl/RecurrentTaskList.java

package com.kms_quebec.rtl;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecurrentTaskList {

    // Public
    /////////////////////////////////////////////////////////////////////////

    public RecurrentTaskList() {
        assert( null == mItems );

        mItems = new ArrayList<RecurrentTask>();

        assert( null != mItems );
    }

    public int GetDueCount() {
        assert( null != mItems );

        int lResult = 0;

        for ( int i = 0; i < mItems.size(); i ++ ) {
            if ( mItems.get( i ).IsDue() ) {
                lResult ++;
            }
        }

        return lResult;
    }

    public RecurrentTask GetDueItem( int aIndex ) {
        assert( null != mItems );

        int lIndex = 0;

        for ( int i = 0; i < mItems.size(); i ++ ) {
            RecurrentTask lTask = mItems.get( i );

            assert( null != lTask );

            if ( lTask.IsDue() )
            {
                if ( lIndex == aIndex ) {
                    return lTask;
                }

                lIndex ++;
            }
        }

        return null;
    }

    public int GetDueItemId( int aIndex ) {
        assert( null != mItems );

        int lIndex = 0;

        for ( int i = 0; i < mItems.size(); i ++ ) {
            RecurrentTask lTask = mItems.get( i );

            assert( null != lTask );

            if ( lTask.IsDue() )
            {
                if ( lIndex == aIndex ) {
                    return i;
                }

                lIndex ++;
            }
        }

        return -1;
    }

    // aTask [DK-;RW-]
    public void Add( RecurrentTask aTask ) {
        assert( null != aTask );

        assert( null != mItems );

        mItems.add( aTask );
    }

    // aContext [---;RW-]
    public void Load( Context aContext ) throws ClassNotFoundException, IOException {
        assert( null != aContext );

        assert( null != mItems );

        try {
            FileInputStream   lFIS = aContext.openFileInput( "RTL" );
            ObjectInputStream lOIS = new ObjectInputStream(lFIS );

            assert( null != lFIS );
            assert( null != lFIS );

            mItems = ( List<RecurrentTask> )( lOIS.readObject() ); // reinterpret_cast

            lOIS.close();
            lFIS.close();
        }
        catch ( FileNotFoundException eE ) {
        }
    }

    // aTask [D--;---]
    public void Remove( RecurrentTask aTask ) {
        assert( null != aTask );

        assert( null != mItems );

        mItems.remove( aTask );
    }

    // aContext [---;RW-]
    public void Save( Context aContext ) throws FileNotFoundException, IOException {
        assert( null != aContext );

        assert( null != mItems );

        FileOutputStream   lFOS = aContext.openFileOutput( "RTL", MODE_PRIVATE );
        ObjectOutputStream lOOS = new ObjectOutputStream( lFOS );

        assert( null != lFOS );
        assert( null != lOOS );

        lOOS.writeObject( mItems );

        lOOS.close();
        lFOS.close();
    }

    public List<RecurrentTask> mItems;

}
