package com.kms_quebec.notif;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add( 0, 0, 0, "Lancer Notification 0" );
        menu.add( 0, 1, 0, "Lancer Notification 1" );
        menu.add( 0, 2, 0, "Lancer Notification 2" );

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent lI = new Intent( this, MainActivity.class );

        PendingIntent lPI = PendingIntent.getActivity( this, 0, lI, PendingIntent.FLAG_ONE_SHOT );

        Notification.Builder lNB = new Notification.Builder( this );

        lNB.setWhen( System.currentTimeMillis() );
        lNB.setSmallIcon(R.mipmap.ic_launcher);
        lNB.setContentIntent( lPI );

        switch ( item.getItemId() )
        {
            case 0 : lNB.setSubText( "Notification 0" ); break;
            case 1 : lNB.setSubText( "Notification 1" ); break;
            case 2 : lNB.setSubText( "Notification 2" ); break;
        }

        mNM.notify(1, lNB.build());

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNM = (NotificationManager)( getSystemService(NOTIFICATION_SERVICE) );
    }

    @Override
    protected void onResume() {
        mNM.cancelAll();

        super.onResume();
    }

    private NotificationManager mNM;

}
