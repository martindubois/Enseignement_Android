
// Auteur   KMS - Martin Dubois, ing.
// Produit  Android
// Fichier  RTL/app/java/com/kms_quebec/rtl/Adapter.java

package com.kms_quebec.rtl;

import android.app.ListActivity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter implements View.OnClickListener {

    // Public
    /////////////////////////////////////////////////////////////////////////

    // aActivity [---;RW-]
    Adapter( ListActivity aActivity )
    {
        assert( null != aActivity );

        assert( null == mInflater );

        mInflater = LayoutInflater.from( aActivity );

        assert( null != mInflater );
    }

    // Retour
    //  null   Pas de tache selectionnee
    //  Autre  La tache selectionnee
    RecurrentTask GetSelected() {
        RecurrentTask lResult;

        if (null == mSelected) {
            lResult = null;
        } else {
            lResult = (RecurrentTask) (mSelected.getTag()); // dynamic_cast

            assert (null != lResult);
        }

        return lResult;
    }

    // aList [-K-;RW-]
    void SetList( RecurrentTaskList aList )
    {
        assert( null != aList );

        assert( null == mList );

        mList = aList;
    }

    void ResetSelection()
    {
        if ( null != mSelected ) {
            mSelected.setTextColor(Color.BLACK);
            mSelected = null;
        }
    }

    // ===== View.OnClickListener ===========================================

    @Override
    public void onClick( View aView ) {
        assert( null != aView );

        TextView lView = ( TextView )( aView );

        if ( null == mSelected ) {
            mSelected = lView;
            mSelected.setTextColor( Color.RED );
        }
        else {
            mSelected.setTextColor( Color.BLACK );
            if ( mSelected == lView ) {
                mSelected = null;
            } else {
                mSelected = lView;
                mSelected.setTextColor( Color.RED );
            }
        }
    }

    // ===== BaseAdapter ====================================================

    @Override
    public int getCount() {
        assert( null != mList );

        return mList.GetDueCount();
    }

    @Override
    public Object getItem( int aPosition ) {
        assert( null != mList );

        return mList.GetDueItem( aPosition );
    }

    @Override
    public long getItemId( int aPosition ) {
        assert( null != mList );

        return mList.GetDueItemId( aPosition );
    }

    @Override
    public View getView( int aPosition, View aConvertView, ViewGroup aParent ) {
        assert( null != mInflater );
        assert( null != mList     );

        RecurrentTask lTask = mList.GetDueItem( aPosition );

        assert( null != lTask );

        if ( null == aConvertView )
        {
            aConvertView = mInflater.inflate( R.layout.task, null );
        }

        assert( null != aConvertView );

        TextView lName = ( TextView )( aConvertView.findViewById( R.id.taskName ) ); // dynamic_cast
        TextView lNote = ( TextView )( aConvertView.findViewById( R.id.taskNote ) ); // dynamic_cast

        assert( null != lName );
        assert( null != lNote );

        lName.setText( lTask.mName );
        lNote.setText( lTask.GetNote() );

        lName.setOnClickListener( this );
        lName.setTag( lTask );

        return aConvertView;
    }

    // Private
    /////////////////////////////////////////////////////////////////////////

    private LayoutInflater    mInflater;
    private RecurrentTaskList mList    ;
    private TextView          mSelected;

}
