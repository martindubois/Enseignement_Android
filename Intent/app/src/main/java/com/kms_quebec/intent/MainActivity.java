package com.kms_quebec.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button  lButton = ( Button )( findViewById( R.id.button ) ); // dynamic_cast

        lButton.setOnClickListener( this );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EditText  lEditText = (EditText)( findViewById( R.id.editText ) ); // dynamic_cast

        lEditText.setText( data.getStringExtra( "Text" ) );
    }

    @Override
    public void onClick(View v) {
        Intent  lIntent = new Intent( this, Main2Activity.class );

        startActivityForResult( lIntent, 0 );
    }
}
