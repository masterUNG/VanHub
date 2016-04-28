package appewtc.masterung.vanhub;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request SQLite
        myManage = new MyManage(MainActivity.this);

        //Test Add Value
//        myManage.addUser("name", "user", "pass", "email", "phone", "lat", "lng",
//                "stop", "price", "start", "end", "new");

        //Delete SQLite
        deleteSQLite();


    }   // Main Method

    private void deleteSQLite() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);
    }

    public void clickSignIn(View view) {
        startActivity(new Intent(MainActivity.this, SignInActivity.class));
    }

    public void clickSignUp(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   // Main Class
