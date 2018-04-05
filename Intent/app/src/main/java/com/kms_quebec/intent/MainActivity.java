package com.kms_quebec.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText)( findViewById( R.id.editText ) ); // dynamic_cast

        Button  lButton = ( Button )( findViewById( R.id.button ) ); // dynamic_cast

        lButton.setOnClickListener( this );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mEditText.setText( data.getStringExtra( "Text" ) );
    }

    @Override
    public void onClick(View v) {
        EditText  lEditText = (EditText)( findViewById( R.id.editText ) ); // dynamic_cast

        Bundle lBundle = new Bundle();

        lBundle.putString( "Text", mEditText.getText().toString() );

        Intent  lIntent = new Intent( this, Main2Activity.class );

        lIntent.putExtra( "Data", lBundle );

        startActivityForResult( lIntent, 0 );
    }
}
