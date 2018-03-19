package com.kms_quebec.adaptpers;

public class Article {

    // Public
    /////////////////////////////////////////////////////////////////////////

    public  Article( String  aNom )
    {
        mNom          = aNom  ;
        mSelectionne  = false ;
    }

    public  boolean  EstSelectionne()
    {
        return  mSelectionne;
    }

    public  String  GetNom()
    {
        return  mNom;
    }

    public  void  ChangerSelectionne()
    {
        mSelectionne = ! mSelectionne;
    }

    // Private
    /////////////////////////////////////////////////////////////////////////

    private  String   mNom         ;
    private  boolean  mSelectionne ;

}
