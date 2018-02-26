package com.kms_quebec.menu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button  mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = ( Button )( findViewById( R.id.button ) ); // dynamic_cast

        registerForContextMenu( mButton );
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate( R.menu.context_menu, menu );
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch ( item.getItemId() )
        {
            case R.id.rouge : mButton.setTextColor( Color.RED    ); return  true;
            case R.id.vert  : mButton.setTextColor( Color.GREEN  ); return  true;
            case R.id.bleu  : mButton.setTextColor( Color.BLUE   ); return  true;
        }

        return super.onContextItemSelected(item);
    }
}
