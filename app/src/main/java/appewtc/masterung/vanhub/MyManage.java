package appewtc.masterung.vanhub;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 4/26/16 AD.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String column_id = "_id";
    public static final String column_Name = "Name";
    public static final String column_User = "User";
    public static final String column_Password = "Password";
    public static final String column_Email = "Email";
    public static final String column_Phone = "Phone";
    public static final String column_Lat = "Lat";
    public static final String column_Lng = "Lng";
    public static final String column_Stop = "Stop";
    public static final String column_Price = "Price";
    public static final String column_timeStart = "timeStart";
    public static final String column_timeEnd = "timeEnd";
    public static final String column_News = "News";

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }   // Constructor

    public long addUser(String strName,
                        String strUser,
                        String strPassword,
                        String strEmail,
                        String strPhone,
                        String strLat,
                        String strLng,
                        String strStop,
                        String strPrice,
                        String strTimeStart,
                        String strTimeEnd,
                        String strNews) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Name, strName);
        contentValues.put(column_User, strUser);
        contentValues.put(column_Password, strPassword);
        contentValues.put(column_Email, strEmail);
        contentValues.put(column_Phone, strPhone);
        contentValues.put(column_Lat, strLat);
        contentValues.put(column_Lng, strLng);
        contentValues.put(column_Stop, strStop);
        contentValues.put(column_Price, strPrice);
        contentValues.put(column_timeStart, strTimeStart);
        contentValues.put(column_timeEnd, strTimeEnd);
        contentValues.put(column_News, strNews);

        return sqLiteDatabase.insert(user_table, null, contentValues);
    }


}   // Main Class
