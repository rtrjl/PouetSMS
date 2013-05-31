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

    public class SMSItem{
        private String number;
        private String message;

        SMSItem(String number,String message)
        {
            this.number = number;
            this.message = message;
        }

    }

    public class SMSAdapter extends ArrayAdapter<String> {

        private ArrayList<String> smsList;
        private Context context;

        public SMSAdapter(ArrayList<String> smsList, Context ctx)
        {
            super(ctx,R.layout.SMSLayout, smsList);
            this.context = ctx;
            this.smsList = smsList;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if(v==null)
            {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.SMSLayout,null);

            }



        }



    }
    
}
