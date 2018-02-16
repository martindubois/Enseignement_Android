package com.kms_quebec.proc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout lLayout = new LinearLayout( this );
        lLayout.setOrientation( LinearLayout.VERTICAL );
        ArrayList< TextView > lList = new ArrayList< TextView >();
        for ( int  i = 0; i < 5; i ++ )
        {
            TextView  lText = new TextView( this );
            lText.setId( i );
            lText.setText( "Texte" + lText.getId() );
            lList.add( lText );
        }
        for ( int  i = 0; i < 5; i ++ )
        {
            lLayout.addView( lList.get( i ) );
        }
        lList.get( 2 ).setText( "Un lapin!" );

        setContentView( lLayout );
    }
}
