package com.kms_quebec.chrono;

import java.io.Serializable;

public class Temps implements Serializable {

    public int mMinute ;
    public int mSeconde;

    public void Tick() {
        if ( 59 == mSeconde ) {
            mSeconde = 0;
            if ( 59 == mMinute ) {
                mMinute = 0;
            } else {
                mMinute = mMinute + 1;
            }
        } else {
            mSeconde = mSeconde + 1;
        }
    }

    public void Zero() {
        mMinute  = 0;
        mSeconde = 0;
    }
}
