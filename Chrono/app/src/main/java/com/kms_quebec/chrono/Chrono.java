package com.kms_quebec.chrono;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;

public class Chrono extends View implements View.OnClickListener, Runnable {

    public Temps mTemps;

    private boolean mActif  ;
    private Bitmap  mFond   ;
    private Handler mHandler;
    private Paint   mMinute ;
    private Paint   mSeconde;

    private static final Point CENTRE   = new Point( 541, 908 );
    private static final int   LONGUEUR = 400;

    public Chrono( Context aContext) {
        super( aContext );

        mActif   = false;
        mHandler = new Handler();

        mMinute = new Paint();
        mMinute.setColor      ( Color.BLACK );
        mMinute.setStrokeWidth( 10 );
        mMinute.setStyle      ( Paint.Style.STROKE );

        mSeconde = new Paint();
        mSeconde.setColor      ( Color.RED );
        mSeconde.setStrokeWidth( 5 );
        mSeconde.setStyle      ( Paint.Style.STROKE );

        mTemps = new Temps();
        mTemps.Zero();

        setOnClickListener( this );
    }

    public void SetFond( Bitmap aFond ) {
        mFond = aFond;
    }

    public void Zero() {
        mTemps.Zero();

        invalidate();
    }

    @Override
    public void onClick(View v) {
        if ( mActif ) {
            mActif = false;
            mHandler.removeCallbacks( this );
        } else {
            mActif = true;
            mHandler.postDelayed( this, 1000 );
        }
    }

    @Override
    public void run() {
        mTemps.Tick();

        mHandler.postDelayed( this, 1000 );

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        android.graphics.Rect lDst;

        lDst = new Rect( 10, 100, canvas.getWidth() - 10, canvas.getHeight() - 100 );

        canvas.drawBitmap( mFond, null, lDst, null );

        double lAngle_rad = ( mTemps.mMinute / 60.0 ) * 2.0 * Math.PI;

        canvas.drawLine(
            CENTRE.x,
            CENTRE.y,
            ( float )( CENTRE.x + LONGUEUR * Math.sin( lAngle_rad ) ),
            ( float )( CENTRE.y - LONGUEUR * Math.cos( lAngle_rad ) ),
            mMinute );

        lAngle_rad = ( mTemps.mSeconde / 60.0 ) * 2.0 * Math.PI;

        canvas.drawLine(
            CENTRE.x,
            CENTRE.y,
            ( float )( CENTRE.x + LONGUEUR * Math.sin( lAngle_rad ) ),
            ( float )( CENTRE.y - LONGUEUR * Math.cos( lAngle_rad ) ),
            mSeconde );
    }

}