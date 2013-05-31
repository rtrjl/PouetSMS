package com.example.pouetsms;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
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

        ArrayList<String> smsList = new ArrayList<String>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query( Uri.parse("content://sms/inbox"), null, null,null,null);
        int indexBody = cursor.getColumnIndex( "body" );
        int indexAddr = cursor.getColumnIndex( "address" );
        if ( indexBody < 0 || !cursor.moveToFirst() ) return;
        smsList.clear();
        do
        {
            String str = "Sender: " + cursor.getString( indexAddr ) + "\n" + cursor.getString( indexBody );
            smsList.add( str );
        }
        while( cursor.moveToNext() );

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,smsList);
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
