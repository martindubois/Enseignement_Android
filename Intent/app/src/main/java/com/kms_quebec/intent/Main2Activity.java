package com.kms_quebec.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent lIntent = getIntent();

        Bundle lBundle = lIntent.getBundleExtra( "Data" );

        mEditText = ( EditText )( findViewById( R.id.editText2 ) ); // dynamic_cast

        mEditText.setText( lBundle.getString( "Text" ) );

        Button lButton = ( Button )( findViewById( R.id.button2 ) ); // dynamic_cast

        lButton.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        Intent  lIntent = new Intent();

        lIntent.putExtra( "Text", mEditText.getText().toString() );

        setResult( 0, lIntent );

        finish();
    }
}
