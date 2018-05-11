package com.kms_quebec.rtl;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.IOException;

public class MainActivity extends android.app.ListActivity implements View.OnClickListener {

    // Public
    /////////////////////////////////////////////////////////////////////////

    // ===== View.OnClickListener ===========================================

    @Override
    public void onClick(View aView) {
        assert( null != aView );

        assert( null != mDialog               );
        assert( null != mDialog_AfterDoneDate );
        assert( null != mDialog_Name          );
        assert( null != mDialog_Period        );

        mDialog.dismiss();

        switch ( aView.getId() )
        {
            case R.id.dialogCancel :              break;
            case R.id.dialogOK     : Dialog_OK(); break;

            default : assert( false );
        }

        mDialog               = null;
        mDialog_AfterDoneDate = null;
        mDialog_Name          = null;
        mDialog_Period        = null;
        mTask                 = null;
    }

    // ===== ListActivity ===================================================

    @Override
    public boolean onCreateOptionsMenu(Menu aMenu) {
        assert( null != aMenu );

        aMenu.add( 0, ACTION_ADD         , 0, "Add..."       );
        aMenu.add( 0, ACTION_DONE        , 0, "Done"         );
        aMenu.add( 0, ACTION_MODIFY      , 0, "Modify..."    );
        aMenu.add( 0, ACTION_REMOVE      , 0, "Remove"       );
        aMenu.add( 0, ACTION_SET_DUE_DATE, 0, "Set due date" );

        return super.onCreateOptionsMenu( aMenu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem aItem) {
        assert( null != aItem );

        switch ( aItem.getItemId() ){
            case ACTION_ADD          : Action_Add       (); return true;
            case ACTION_DONE         : Action_Done      (); return true;
            case ACTION_MODIFY       : Action_Modify    (); return true;
            case ACTION_REMOVE       : Action_Remove    (); return true;
            case ACTION_SET_DUE_DATE : Action_SetDueDate(); return true;

            default : assert( false );
        }

        return super.onOptionsItemSelected(aItem);
    }

    // Protected
    /////////////////////////////////////////////////////////////////////////

    // ===== ListActivity ===================================================

    @Override
    protected void onCreate(Bundle aSavedInstanceState) {
        assert( null == mAdapter );
        assert( null == mList    );

        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new Adapter( this );
        mList    = new RecurrentTaskList();

        assert( null != mAdapter );
        assert( null != mList    );

        mAdapter.SetList( mList );

        setActionBar( (Toolbar)( findViewById( R.id.toolbar ) ) ); // reinterpret_cast
        setListAdapter( mAdapter );
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            mList.Save( this );
        }
        catch ( IOException eE ) {
            Toast.makeText( this, eE.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            mList.Load( this );

            mAdapter.notifyDataSetInvalidated();
        }
        catch ( ClassNotFoundException eE ) {
            Toast.makeText( this, eE.getMessage(), Toast.LENGTH_LONG ).show();
        }
        catch ( IOException eE ) {
            Toast.makeText( this, eE.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    // Private
    /////////////////////////////////////////////////////////////////////////

    static final int ACTION_ADD          = 1;
    static final int ACTION_DONE         = 2;
    static final int ACTION_MODIFY       = 3;
    static final int ACTION_REMOVE       = 4;
    static final int ACTION_SET_DUE_DATE = 5;

    void Action_Add() {
        assert( null == mTask );

        mTask     = new RecurrentTask();
        mTask_New = true;

        assert( null != mTask );

        Dialog_Create();
    }

    void Action_Done() {
        assert( null != mAdapter );
        assert( null == mTask    );

        mTask = mAdapter.GetSelected();
        if ( null != mTask ) {
            mTask.MarkAsDone();

            mTask = null;

            mAdapter.notifyDataSetChanged();
        }
    }

    void Action_Modify() {
        assert( null != mAdapter );
        assert( null == mTask    );

        mTask = mAdapter.GetSelected();
        if ( null != mTask ) {
            mTask_New = false;

            Dialog_Create();
        }
    }

    void Action_Remove() {
        assert( null != mAdapter );
        assert( null != mList    );
        assert( null == mTask    );

        mTask = mAdapter.GetSelected();
        if ( null != mTask ) {
            mAdapter.ResetSelection();

            mList.Remove( mTask );

            mTask = null;

            mAdapter.notifyDataSetInvalidated();
        }
    }

    void Action_SetDueDate() {
        assert( null != mAdapter );
        assert( null == mTask    );

        mTask = mAdapter.GetSelected();
        if ( null != mTask ) {
            mTask.SetDueDate();

            mTask = null;
        }
    }

    void Dialog_Create() {
        assert( null == mDialog );
        assert( null != mTask   );

        mDialog = new Dialog( this );

        assert( null != mDialog );

        mDialog.setContentView( R.layout.dialog );
        mDialog.setTitle( "Task" );

        Button lCancel = ( Button )( mDialog.findViewById( R.id.dialogCancel ) );
        Button lOK     = ( Button )( mDialog.findViewById( R.id.dialogOK     ) );

        assert( null != lCancel );
        assert( null != lOK     );

        lCancel.setOnClickListener( this );
        lOK    .setOnClickListener( this );

        mDialog_AfterDoneDate = ( CheckBox )( mDialog.findViewById( R.id.dialogAfterDoneDate ) );
        mDialog_Name          = ( EditText )( mDialog.findViewById( R.id.dialogName          ) );
        mDialog_Period        = ( EditText )( mDialog.findViewById( R.id.dialogPeriod        ) );

        Dialog_Update();

        mDialog.show();
    }

    void Dialog_OK() {
        assert( null != mAdapter              );
        assert( null != mDialog_AfterDoneDate );
        assert( null != mDialog_Name          );
        assert( null != mDialog_Period        );
        assert( null != mTask                 );

        mTask.mAfterDoneDate = mDialog_AfterDoneDate.isChecked();
        mTask.mName          = mDialog_Name.getText().toString();

        mTask.SetPeriod( mDialog_Period.getText().toString() );

        assert( null != mTask.mName );

        if ( mTask_New ) {
            mList.Add( mTask );
            mAdapter.notifyDataSetInvalidated();
        } else {
            mAdapter.ResetSelection();
            mAdapter.notifyDataSetChanged();
        }
    }

    void Dialog_Update() {
        assert( null != mDialog_AfterDoneDate );
        assert( null != mDialog_Name          );
        assert( null != mDialog_Period        );
        assert( null != mTask                 );
        assert( null != mTask.mName           );

        mDialog_AfterDoneDate.setChecked( mTask.mAfterDoneDate );
        mDialog_Name         .setText   ( mTask.mName          );
        mDialog_Period       .setText   ( mTask.GetPeriod()    );
    }

    Adapter           mAdapter;
    RecurrentTaskList mList   ;

    Dialog   mDialog              ;
    CheckBox mDialog_AfterDoneDate;
    EditText mDialog_Name         ;
    EditText mDialog_Period       ;

    RecurrentTask mTask    ;
    boolean       mTask_New;

}
