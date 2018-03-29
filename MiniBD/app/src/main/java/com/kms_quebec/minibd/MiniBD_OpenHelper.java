package com.kms_quebec.minibd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiniBD_OpenHelper extends SQLiteOpenHelper {

    // Public
    /////////////////////////////////////////////////////////////////////////

    public MiniBD_OpenHelper( Context aContext, String aNom, SQLiteDatabase.CursorFactory aFactory, int aVersion ) {
        super( aContext, aNom, aFactory, aVersion );
    }

    // ===== SQLiteOpenHelper ===============================================

    @Override
    public void onCreate( SQLiteDatabase aDB ) {
        aDB.execSQL( "CREATE TABLE " + Article.TABLE + "( " +
            Article.CHAMP_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Article.CHAMP_NOM  + " TEXT NOT NULL, " +
            Article.CHAMP_PRIX + " REAL NOT NULL );" );
    }

    @Override
    public void onUpgrade( SQLiteDatabase aDB, int aOldVersion, int aNewVersion ) {
        aDB.execSQL( "DROP TABLE " + Article.TABLE + ";" );
        onCreate( aDB );
    }
}

