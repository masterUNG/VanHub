package appewtc.masterung.vanhub;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class VinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vin);

        ListView listView = (ListView) findViewById(R.id.listView2);
        TextView textView = (TextView) findViewById(R.id.txtProvince);

        String strProvince = getIntent().getStringExtra("Province");
        textView.setText("วินรถตู้ไป " + strProvince);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE Stop = " + "'" + strProvince + "'", null);
        cursor.moveToFirst();

        String[] nameStrings = new String[cursor.getCount()];
        for (int i=0;i<cursor.getCount();i++) {

            nameStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Name));
            cursor.moveToNext();

        }   //for
        cursor.close();

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nameStrings);
        listView.setAdapter(stringArrayAdapter);



    }   // Main Method
}   // Main Class
