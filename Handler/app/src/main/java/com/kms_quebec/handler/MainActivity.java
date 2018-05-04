package com.kms_quebec.handler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add( 0, 0, 0, "Demarrer");
        menu.add( 0, 1, 0, "Pause"   );

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ) {
            case 0 :
                mTimer.postDelayed(this, 1000);
                return true;

            case 1 :
                mTimer.removeCallbacks(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void run() {
        mCount ++;
        mTextView.setText(String.valueOf(mCount));
        mTimer.postDelayed(this, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCount    = 0;
        mTextView = (TextView)(findViewById(R.id.textView));
        mTimer    = new Handler();
    }

    private int      mCount   ;
    private TextView mTextView;
    private Handler  mTimer   ;

}
