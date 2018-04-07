package com.kms_quebec.mediac;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    MediaController mMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView lVV = (VideoView) findViewById( R.id.videoView );

        Uri lU = Uri.parse( "http://www.html5videoplayer.net/videos/toystory.mp4" );

        lVV.setVideoURI( lU );

        mMC = new MediaController( this );

        mMC.setMediaPlayer    ( lVV );
        lVV.setMediaController( mMC );

        lVV.requestFocus();
    }
}
