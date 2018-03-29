package com.kms_quebec.minibd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MiniBD {

    // Public
    /////////////////////////////////////////////////////////////////////////

    public MiniBD( Context aContext ) {
        mOH = new MiniBD_OpenHelper( aContext, "MiniBD.db", null, VERSION_BD );
    }

    public void Open() {
        mBD = mOH.getWritableDatabase();
    }

    public Article GetArticle( String aNom ) {
        Cursor lC = mBD.query( Article.TABLE, Article.QUERY, Article.CHAMP_NOM + " LIKE \"" + aNom + "\"", null, null, null, null );

        return  GetArticle( lC );
    }

    public long InsertArticle( Article aA ) {
        return mBD.insert( Article.TABLE, null, aA.GetContentValues() );
    }

    public int RemoveArticle( int aId ) {
        return mBD.delete( Article.TABLE, Article.CHAMP_ID + " = " + aId, null );
    }

    public int UpdateArticle( int aId, Article aA ) {
        return mBD.update( Article.TABLE, aA.GetContentValues(), Article.CHAMP_ID + " = " + aId, null );
    }

    // Private
    /////////////////////////////////////////////////////////////////////////

    private static final int VERSION_BD = 1;

    private Article GetArticle( Cursor aCursor ) {
        if ( 0 == aCursor.getCount() ) {
            return null;
        }

        aCursor.moveToFirst();

        Article lResult = new Article( aCursor );

        aCursor.close();

        return lResult;
    }

    private SQLiteDatabase    mBD;
    private MiniBD_OpenHelper mOH;

}
