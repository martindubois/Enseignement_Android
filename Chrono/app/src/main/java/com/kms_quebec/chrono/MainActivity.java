package com.kms_quebec.chrono;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Chrono mChrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bitmap lFond   = BitmapFactory.decodeResource( getResources(), R.drawable.chrono4 );

        mChrono = new Chrono( this );

        mChrono.SetFond( lFond );

        setContentView( mChrono );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add( 0, 0, 0, "Zero" );

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() )
        {
            case 0 :
                mChrono.Zero();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            FileOutputStream   lFOS = openFileOutput("Temps", MODE_PRIVATE);
            ObjectOutputStream lOOS = new ObjectOutputStream(lFOS);

            lOOS.writeObject( mChrono.mTemps );

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
            FileInputStream   lFIS = openFileInput( "Temps" );
            ObjectInputStream lOIS = new ObjectInputStream( lFIS );

            mChrono.mTemps = ( Temps )( lOIS.readObject() );

            lOIS.close();
            lFIS.close();
        }
        catch ( ClassNotFoundException eE ) {
            Toast.makeText( this, eE.getMessage(), Toast.LENGTH_LONG ).show();
        }
        catch ( IOException eE ) {
            Toast.makeText( this, eE.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

}
