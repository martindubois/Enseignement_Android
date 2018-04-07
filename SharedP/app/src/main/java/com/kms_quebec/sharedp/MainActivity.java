package com.kms_quebec.sharedp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add( 0, 0, 0, "Ecrire" );
        menu.add( 0, 1, 0, "Lire"   );
        menu.add( 0, 2, 0, "Retirer");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText                 lET  = ( EditText ) findViewById( R.id.editText );
        SharedPreferences        lSP  = getSharedPreferences( "SharedP", MODE_PRIVATE );
        SharedPreferences.Editor lSPE;

        switch ( item.getItemId() )
        {
            case 0 :
                lSPE = lSP.edit();
                lSPE.putString( "Nom", lET.getText().toString() );
                lSPE.apply();
                break;

            case 1 :
                lET.setText( lSP.getString( "Nom", "Valeur par defaut" ) );
                break;

            case 2 :
                lSPE = lSP.edit();
                lSPE.remove( "Nom" );
                lSPE.apply();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
