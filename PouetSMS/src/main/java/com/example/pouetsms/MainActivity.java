package com.example.pouetsms;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart(){
        super.onStart();

        ArrayList<SMSItem> smsList = new ArrayList<SMSItem>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query( Uri.parse("content://sms/inbox"), null, null,null,null);
        int indexBody = cursor.getColumnIndex( SMSItem.BODY);
        int indexAddr = cursor.getColumnIndex(SMSItem.ADDRESS);
        int indexDate = cursor.getColumnIndex(SMSItem.DATE);
        if ( indexBody < 0 || !cursor.moveToFirst() ) return;
        smsList.clear();
        do {
            smsList.add(new SMSItem(cursor.getString(indexAddr), cursor.getString(indexBody), cursor.getString(indexDate)));
        }
        while( cursor.moveToNext() );

        SMSAdapter adapter;
        adapter = new SMSAdapter(smsList,this);
        ListView listview = (ListView) findViewById(R.id.SMSList);
        listview.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }




    
}
