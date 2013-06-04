package com.example.pouetsms;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rtrjl on 03/06/13.
 */
public class SMSItem{

    public static final String SMS_EXTRA_NAME = "pdus";
    public static final String SMS_URI = "content://sms";

    public static final String ADDRESS = "address";
    public static final String PERSON = "person";
    public static final String DATE = "date";
    public static final String READ = "read";
    public static final String STATUS = "status";
    public static final String TYPE = "type";
    public static final String BODY = "body";
    public static final String SEEN = "seen";

    public static final int MESSAGE_TYPE_INBOX = 1;
    public static final int MESSAGE_TYPE_SENT = 2;

    public static final int MESSAGE_IS_NOT_READ = 0;
    public static final int MESSAGE_IS_READ = 1;

    public static final int MESSAGE_IS_NOT_SEEN = 0;
    public static final int MESSAGE_IS_SEEN = 1;

    private DateFormat dateformat = DateFormat.getDateInstance();
    private DateFormat timeformat = DateFormat.getTimeInstance();
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate(long date) {
        Date myDate = new Date(date);


        this.date = dateformat.format(myDate);
        this.date = this.date+", "+timeformat.format(myDate);



    }

    private String date;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String number;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    SMSItem(String number,String message, String date)
    {
        this.number = number;
        this.message = message;
        try{
            this.setDate(Long.parseLong(date));
        }
        catch(Exception e)
        {
            this.date = date;
        }

    }


}
