package com.kms_quebec.boites;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnClickListener {

    // Public
    /////////////////////////////////////////////////////////////////////////

    // ===== DialogInterface.OnClickListener ================================

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch ( which )
        {
            case  0 : mMessage.setText( "Bonjour Docteur"  ); break;
            case  1 : mMessage.setText( "Bonjour Madame"   ); break;
            case  2 : mMessage.setText( "Bonjour Monsier"  ); break;

            case  BUTTON_NEGATIVE:
                mMessage.setText( "Ne soyez pas timide!" );
                break;

            case  BUTTON_POSITIVE :
                mMessage.setText( "Bonjour " + mNom.getText() );
                break;
        }
    }

    // ===== View.OnClickListener ===========================================

    @Override
    public void onClick(View v) {

        AlertDialog.Builder  lAlert;

        switch ( v.getId() )
        {
            case  R.id.buttonOk :
                mDialog.dismiss();
                RadioGroup lGroup = ( RadioGroup )( mDialog.findViewById( R.id.radioGroup ) ); // dynamic_cast
                switch ( lGroup.getCheckedRadioButtonId() )
                {
                    case R.id.radioDocteur  : mMessage.setText( "Bonjour Docteur"  ); break;
                    case R.id.radioMadame   : mMessage.setText( "Bonjour Madame"   ); break;
                    case R.id.radioMonsieur : mMessage.setText( "Bonjour Monsieur" ); break;

                    default : mMessage.setText( "Bonjour Chose" );
                }
                break;

            case  R.id.choix :
                lAlert = new AlertDialog.Builder( this );

                lAlert.setItems( R.array.Titres, this );
                lAlert.setTitle( "Quel est votre titre?" );

                lAlert.setNegativeButton( "Annuler" , this );
                lAlert.setPositiveButton( "OK"      , this );

                lAlert.show();
                break;

            case  R.id.radio :
                mDialog = new Dialog( this );

                mDialog.setContentView( R.layout.dialog );
                mDialog.setTitle( "Quel est votre titre" );

                Button  lButton = ( Button )( mDialog.findViewById( R.id.buttonOk ) ); // dynamic_cast
                lButton.setOnClickListener( this );

                mDialog.show();
                break;

            case  R.id.texte :
                lAlert = new AlertDialog.Builder( this );

                mNom = new EditText( this );

                lAlert.setMessage ( "Quel est votre prenom?" );
                lAlert.setTitle   ( "Question!" );
                lAlert.setView    ( mNom );

                lAlert.setNegativeButton( "Annuler" , this );
                lAlert.setPositiveButton( "OK"      , this );

                lAlert.show();
                break;
        }

    }

    // Protected
    /////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessage = ( TextView )( findViewById( R.id.message ) ); // dynamic_cast

        Button lChoix = ( Button )( findViewById( R.id.choix ) ); // dynamic_cast
        Button lRadio = ( Button )( findViewById( R.id.radio ) ); // dynamic_cast
        Button lTexte = ( Button )( findViewById( R.id.texte ) ); // dynamic_cast

        lChoix.setOnClickListener( this );
        lRadio.setOnClickListener( this );
        lTexte.setOnClickListener( this );
    }

    // Private
    /////////////////////////////////////////////////////////////////////////

    private Dialog   mDialog  ;
    private TextView mMessage ;
    private EditText mNom     ;
}
