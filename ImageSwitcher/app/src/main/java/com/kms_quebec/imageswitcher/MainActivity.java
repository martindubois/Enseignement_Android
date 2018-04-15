package com.kms_quebec.imageswitcher;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;

public class MainActivity extends AppCompatActivity
    implements android.view.View.OnClickListener {

    private ArrayList<Bitmap> mCartes       ;
    private ImageSwitcher     mImageSwitcher;
    private Random            mRandom       ;
    private Bitmap            mVerso        ;

    private static final int NOMBRE = 52;

    @Override
    public void onClick(View v) {
        Bitmap lCarte = mCartes.get( mRandom.nextInt( NOMBRE ) );
        mImageSwitcher.setImageDrawable( new BitmapDrawable( getResources(), lCarte ) );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChargerImages();

        mImageSwitcher = ( ImageSwitcher )( findViewById( R.id.imageSwitcher ) ); // dynamic_cast

        mImageSwitcher.setFactory( new Factory( this, mVerso ) );
        mImageSwitcher.setOnClickListener( this );

        Animation lIn  = AnimationUtils.loadAnimation( this, android.R.anim.slide_in_left   );
        Animation lOut = AnimationUtils.loadAnimation( this, android.R.anim.slide_out_right );

        mImageSwitcher.setInAnimation ( lIn  );
        mImageSwitcher.setOutAnimation( lOut );

        mRandom = new Random();
    }

    private void ChargerImages()
    {
        mCartes = new ArrayList<>( NOMBRE );

        BitmapFactory.Options lOpt = new BitmapFactory.Options();

        lOpt.inDensity = 420;

        Bitmap lCartes = BitmapFactory.decodeResource( getResources(), R.drawable.cartes, lOpt );

        int lH = lCartes.getHeight() /  5;
        int lL = lCartes.getWidth () / 13;

        for ( int i = 0; i < 13; i ++ )
        {
            for ( int j = 0; j < 4; j ++ )
            {
                Bitmap lCarte = Bitmap.createBitmap( lCartes, i * lL, j * lH, lL, lH );
                mCartes.add( lCarte );
            }
        }

        mVerso = Bitmap.createBitmap( lCartes, 2 * lL, 4 * lH, lL, lH );
    }

}
