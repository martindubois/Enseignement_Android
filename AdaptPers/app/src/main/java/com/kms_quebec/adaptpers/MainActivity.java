package com.kms_quebec.adaptpers;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

    // Protected
    /////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        AjouterArticles();

        mAdapter = new ArticleAdapter( this, mArticles );

        setListAdapter( mAdapter );
    }

    // Private
    /////////////////////////////////////////////////////////////////////////

    private  void  AjouterArticles()
    {
        mArticles.clear();

        mArticles.add( new Article( "Bebe epinard"        ) );
        mArticles.add( new Article( "Beurre d'arrachide"  ) );
        mArticles.add( new Article( "Biere"               ) );
        mArticles.add( new Article( "Boeuf"               ) );
        mArticles.add( new Article( "Carottes"            ) );
        mArticles.add( new Article( "Cherios"             ) );
        mArticles.add( new Article( "Chocolat"            ) );
        mArticles.add( new Article( "Croque-Nature"       ) );
        mArticles.add( new Article( "Croustille"          ) );
        mArticles.add( new Article( "Gateau"              ) );
        mArticles.add( new Article( "Jambon"              ) );
        mArticles.add( new Article( "Jus d'orange"        ) );
        mArticles.add( new Article( "Lait"                ) );
        mArticles.add( new Article( "Pain blanc"          ) );
        mArticles.add( new Article( "Pain brun"           ) );
        mArticles.add( new Article( "Pepsi"               ) );
        mArticles.add( new Article( "Perrier"             ) );
        mArticles.add( new Article( "Pizza"               ) );
        mArticles.add( new Article( "Porc"                ) );
        mArticles.add( new Article( "Riz"                 ) );
        mArticles.add( new Article( "Salade"              ) );
        mArticles.add( new Article( "Sauce a spagetti"    ) );
    }

    private  ArticleAdapter   mAdapter;
    private  List< Article >  mArticles = new ArrayList< Article >();

}
