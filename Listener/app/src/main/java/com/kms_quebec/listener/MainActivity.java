package com.kms_quebec.listener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        RadioButton.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener,
        View.OnClickListener,
        View.OnFocusChangeListener,
        View.OnHoverListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton         = ( Button       ) findViewById( R.id.button        );
        mCheckBox       = ( CheckBox     ) findViewById( R.id.checkBox      );
        mEditText       = ( EditText     ) findViewById( R.id.editText      );
        mRadioButton0   = ( RadioButton  ) findViewById( R.id.radioButton0  );
        mRadioButton1   = ( RadioButton  ) findViewById( R.id.radioButton1  );
        mSeekBar        = ( SeekBar      ) findViewById( R.id.seekBar       );
        mTextView       = ( TextView     ) findViewById( R.id.textView      );

        mButton       .setOnClickListener         ( this );
        mCheckBox     .setOnClickListener         ( this );
        mEditText     .setOnFocusChangeListener   ( this );
        mRadioButton0 .setOnCheckedChangeListener ( this );
        mRadioButton1 .setOnCheckedChangeListener ( this );
        mSeekBar      .setOnSeekBarChangeListener ( this );
        mTextView     .setOnHoverListener         ( this );
    }

    Button        mButton        ;
    CheckBox      mCheckBox      ;
    EditText      mEditText      ;
    RadioButton   mRadioButton0  ;
    RadioButton   mRadioButton1  ;
    SeekBar       mSeekBar       ;
    TextView      mTextView      ;

    @Override
    public void onClick(View v) {
        switch ( v.getId() ) {
            case R.id.button:
                Toast.makeText(this, "Button clique"   , Toast.LENGTH_LONG).show();
                break;

            case R.id.checkBox:
                Toast.makeText(this, "CheckBox clique" , Toast.LENGTH_LONG).show();
                break;

            default : assert( false );
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if ( hasFocus ) {
            Toast.makeText(this, "EditText entre", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "EditText sortie", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onHover(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch ( buttonView.getId() )
        {
            case  R.id.radioButton0 :
                if ( isChecked ) {
                    Toast.makeText(this, "RadioButton selectionne", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, "RadioButton0 deselectionne", Toast.LENGTH_LONG).show();
                }
                break;

            case  R.id.radioButton1 :
                break;

            default : assert( false );
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Toast.makeText( this, "SeekBar deplace", Toast.LENGTH_LONG ).show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
