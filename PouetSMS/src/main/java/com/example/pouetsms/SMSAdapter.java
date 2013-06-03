package com.example.pouetsms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rtrjl on 03/06/13.
 */
public class SMSAdapter extends ArrayAdapter<SMSItem> {

    private ArrayList<SMSItem> smsList;
    private Context context;



    public SMSAdapter(ArrayList<SMSItem> smsList, Context ctx)
    {
        super(ctx,R.layout.sms_layout, smsList);
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
            v = vi.inflate(R.layout.sms_layout,null);

        }

        SMSItem mySMS = smsList.get(position);

        if (mySMS != null)
        {
            TextView contactTextView = (TextView) v.findViewById(R.id.ContactTextView);
            TextView hourTextView = (TextView) v.findViewById(R.id.HourTextView);
            TextView messageTextVew = (TextView) v.findViewById(R.id.MessageTextView);

            contactTextView.setText(mySMS.getNumber());
            hourTextView.setText(mySMS.getDate());
            messageTextVew.setText(mySMS.getMessage());

        }


        return v;
    }



}