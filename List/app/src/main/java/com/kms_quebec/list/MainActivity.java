package com.kms_quebec.list;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[]  lF = {
            "2012", "300", "Avatar", "Blade runner", "La memoire dans la peau",
            "Jeu de guerre", "Octobre rouge", "Predateur", "Seigneur des anneaux",
            "Total recall" };

        ArrayAdapter< String >  lA;

        lA = new ArrayAdapter< String >( this, android.R.layout.simple_list_item_1, lF );

        setListAdapter( lA );

        setContentView(R.layout.activity_main);
    }
}
