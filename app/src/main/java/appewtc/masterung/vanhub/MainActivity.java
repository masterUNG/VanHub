package appewtc.masterung.vanhub;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

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

        //Synchronize JSON to SQLite
        synJSON();

    }   // Main Method

    //Create Inner Class
    public class ConnectedJSON extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://swiftcodingthai.com/van/php_get_user_master.php").build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("28April", "JSON ===>>> " + s);

        }   // onPost
    }   // Connected Class

    private void synJSON() {

        ConnectedJSON connectedJSON = new ConnectedJSON();
        connectedJSON.execute();

    }   // synJSON

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
