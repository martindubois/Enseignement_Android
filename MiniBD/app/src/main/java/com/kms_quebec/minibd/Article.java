package com.kms_quebec.minibd;

import android.content.ContentValues;
import android.database.Cursor;

public class Article {

    // Public
    /////////////////////////////////////////////////////////////////////////

    public static final String CHAMP_ID   = "id"  ;
    public static final String CHAMP_NOM  = "nom" ;
    public static final String CHAMP_PRIX = "prix";

    public static final String QUERY[] = { Article.CHAMP_ID, Article.CHAMP_NOM, Article.CHAMP_PRIX };

    public static final String TABLE = "table_articles";

    public int    mId  ;
    public String mNom ;
    public Float  mPrix;

    public Article( Cursor aCursor ) {
        mId   = aCursor.getInt   ( 0 );
        mNom  = aCursor.getString( 1 );
        mPrix = aCursor.getFloat ( 2 );
    }

    public ContentValues GetContentValues() {
        ContentValues lResult = new ContentValues();
        lResult.put( CHAMP_NOM , mNom  );
        lResult.put( CHAMP_PRIX, mPrix );
        return lResult;
    }
}

