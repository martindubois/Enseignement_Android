package com.kms_quebec.sons;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    MenuItem    mArreter    ;
    MenuItem    mJouer      ;
    MenuItem    mPause      ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        // Ressource
        mMediaPlayer = MediaPlayer.create( this, R.raw.son );
        */

        // Lien Internet
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource( "http://www.logz.org/fichiers/_mobile_34484_Going-Blind-Court.mp3" );
            mMediaPlayer.prepareAsync();
        }
        catch ( java.io.IOException eE )
        {
            Toast.makeText( this, eE.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mArreter = menu.add( 0, 0, 0, "Arreter" );
        mJouer   = menu.add( 0, 1, 0, "Jouer"   );
        mPause   = menu.add( 0, 2, 0, "Pause"   );

        mArreter.setEnabled( false );
        mPause  .setEnabled( false );

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() )
        {
            case 0 :
                mMediaPlayer.stop();
                mArreter.setEnabled( false );
                mJouer  .setEnabled( true  );
                mPause  .setEnabled( false );
                break;

            case 1 :
                mMediaPlayer.start();
                mArreter.setEnabled( true  );
                mJouer  .setEnabled( false );
                mPause  .setEnabled( true  );
                break;

            case 2 :
                mMediaPlayer.pause();
                mArreter.setEnabled( true  );
                mJouer  .setEnabled( true  );
                mPause  .setEnabled( false );
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
