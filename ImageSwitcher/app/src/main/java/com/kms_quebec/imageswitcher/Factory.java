
package com.kms_quebec.imageswitcher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class Factory implements ViewSwitcher.ViewFactory {

    private android.content.Context mContext ;
    private Bitmap                  mPremiere;

    public Factory( Context aContext, Bitmap aPremiere )
    {
        mContext  = aContext ;
        mPremiere = aPremiere;
    }

    @Override
    public View makeView() {
        ImageView lIV = new ImageView( mContext );

        lIV.setScaleType( ImageView.ScaleType.FIT_CENTER );
        lIV.setLayoutParams( new ImageSwitcher.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT ) );
        lIV.setImageDrawable( new BitmapDrawable( null, mPremiere ) );

        return lIV;
    }

}
