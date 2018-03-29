package com.kms_quebec.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    List< Livre > mLivres = new ArrayList< Livre >();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Livre lL = new Livre();

        lL.mPages = 523;
        lL.mTitre = "Android pour les pas trop nuls";

        mLivres.add( lL );
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            FileOutputStream lFOS = openFileOutput("Livres", MODE_PRIVATE);
            ObjectOutputStream lOOS = new ObjectOutputStream(lFOS);

            lOOS.writeObject(mLivres);

            lOOS.close();
            lFOS.close();
        }
        catch ( IOException eE ) {
            Toast.makeText( this, eE.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            FileInputStream   lFIS = openFileInput("Livres");
            ObjectInputStream lOIS = new ObjectInputStream(lFIS);

            mLivres.clear();

            mLivres = ( List< Livre > )( lOIS.readObject() ); // dynamic_cast
        }
        catch ( ClassNotFoundException eE ) {
            Toast.makeText( this, eE.getMessage(), Toast.LENGTH_LONG ).show();
        }
        catch ( IOException eE ) {
            Toast.makeText( this, eE.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}

