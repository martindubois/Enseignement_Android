package com.kms_quebec.linear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView  lTV;

        lTV = (TextView) findViewById( R.id.textView1 ); // dynamic_cast
        lTV.setTextSize( 24.0f );

        lTV = (TextView) findViewById( R.id.textView2 ); // dynamic_cast
        lTV.setTextSize( 24.0f );

        lTV = (TextView) findViewById( R.id.textView3 ); // dynamic_cast
        lTV.setTextSize( 24.0f );

        lTV = (TextView) findViewById( R.id.textView4 ); // dynamic_cast
        lTV.setTextSize( 24.0f );
    }
}
