
// Auteur   KMS - Martin Dubois, ing.
// Produit  Android
// Fichier  RTL/app/src/main/java/com/kms-quebec/rtl/RecurrentTask.java

package com.kms_quebec.rtl;

import java.io.Serializable;

import android.icu.util.Calendar;

class RecurrentTask implements Serializable {

    // Public
    /////////////////////////////////////////////////////////////////////////

    public RecurrentTask() {
        assert( null == mDueDate );
        assert( null == mName    );

        mAfterDoneDate = false                 ;
        mDueDate       = Calendar.getInstance();
        mName          = ""                    ;
        mPeriod        = 7                     ;

        assert( null != mDueDate );
    }

    public String GetNote() {
        String lResult = "";

        lResult = lResult + GetDueDate();
        lResult = lResult + " - Period = ";
        lResult = lResult + GetPeriod();
        lResult = lResult + " days (after the ";

        if ( mAfterDoneDate ) {
            lResult = lResult + "done date)";
        }
        else {
            lResult = lResult + "due date)";
        }

        return lResult;
    }

    public String GetPeriod() {
        assert( 0 < mPeriod );

        String lResult = String.valueOf( mPeriod );

        assert( null != lResult );

        return lResult;
    }

    // Retour
    //  false  La tache n'est pas du
    //  true   La tache est du
    public boolean IsDue() {
        assert( null != mDueDate );

        Calendar lNow = Calendar.getInstance();

        assert( null != lNow );

        return ( 0 >= mDueDate.compareTo( lNow ) );
    }

    public void MarkAsDone() {
        assert( null != mDueDate );
        assert( 0    <  mPeriod  );

        Calendar lNow = Calendar.getInstance();

        assert( null != lNow );

        if ( mAfterDoneDate )
        {
            mDueDate = lNow;
        }

        mDueDate.add( Calendar.DAY_OF_YEAR, mPeriod );
    }

    public void SetDueDate() {
        assert( null != mDueDate );

        mDueDate = Calendar.getInstance();

        assert( null != mDueDate );
    }

    // aPeriod [---;R--]
    public void SetPeriod( String aPeriod ) {
        assert( null != aPeriod );

        mPeriod = Integer.parseInt( aPeriod );

        if ( 0 >= mPeriod ) {
            mPeriod = 1;
        }
    }

    public boolean mAfterDoneDate;
    public String  mName         ;

    // Private
    /////////////////////////////////////////////////////////////////////////

    private String GetDueDate() {
        assert( null != mDueDate );

        String lResult = "";

        lResult = lResult + String.valueOf( mDueDate.get( Calendar.YEAR ) );
        lResult = lResult + "-";
        lResult = lResult + String.valueOf( mDueDate.get( Calendar.MONTH ) + 1 );
        lResult = lResult + "-";
        lResult = lResult + String.valueOf( mDueDate.get( Calendar.DAY_OF_MONTH ) );

        return lResult;
    }

    private Calendar mDueDate;
    public int       mPeriod ;

}
