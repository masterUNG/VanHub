package appewtc.masterung.vanhub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        final String[] nameStrings = new String[cursor.getCount()];
        final String[] latStrings = new String[cursor.getCount()];
        final String[] lngStrings = new String[cursor.getCount()];


        for (int i=0;i<cursor.getCount();i++) {

            nameStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Name));
            latStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Lat));
            lngStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Lng));

            cursor.moveToNext();

        }   //for
        cursor.close();

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nameStrings);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(VinActivity.this, VinDetail.class);

                intent.putExtra("Name", nameStrings[i]);
                intent.putExtra("Lat", latStrings[i]);
                intent.putExtra("Lng", lngStrings[i]);

                startActivity(intent);


            }   // onItemClick
        });



    }   // Main Method
}   // Main Class
