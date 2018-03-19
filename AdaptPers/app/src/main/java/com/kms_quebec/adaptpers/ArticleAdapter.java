package com.kms_quebec.adaptpers;

import java.util.List;

import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ArticleAdapter extends BaseAdapter implements View.OnClickListener {

    // Public
    /////////////////////////////////////////////////////////////////////////

    ArticleAdapter( ListActivity  aActivity, List< Article >  aArticles )
    {
        mArticles = aArticles;
        mInflater = LayoutInflater.from( aActivity );
    }

    // ===== View.OnClickListener ===========================================

    @Override
    public void onClick(View v) {
        Article  lArticle = ( Article )( v.getTag() ); // reinterpret_cast

        lArticle.ChangerSelectionne();
    }

    // ===== Adapter ========================================================

    @Override
    public int getCount() {
        return  mArticles.size();
    }

    @Override
    public Object getItem(int position) {
        return  mArticles.get( position );
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Article  lArticle = mArticles.get( position );

        if ( null == convertView ) {
            convertView = mInflater.inflate( R.layout.article, null );
        }

        TextView  lNom         = ( TextView )( convertView.findViewById( R.id.nom          ) ); // dynamic_cast
        CheckBox  lSelectionne = ( CheckBox )( convertView.findViewById( R.id.selectionne  ) ); // dynamic_cast

        lNom         .setText    ( lArticle.GetNom         () );
        lSelectionne .setChecked ( lArticle.EstSelectionne () );

        lSelectionne .setOnClickListener( this );
        lSelectionne .setTag( lArticle );

        return  convertView;
    }

    // Private
    /////////////////////////////////////////////////////////////////////////

    private  List< Article >   mArticles;
    private  LayoutInflater    mInflater;

}
