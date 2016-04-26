package appewtc.masterung.vanhub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText, emailEditText,
            phoneEditText, locationEditText, nameEditText,
            priceEditText, newsEditText;

    private Spinner stopSpinner, timeStartSpinner, timeEndSpinner;

    private String userString, passwordString, emailString,
            phoneString, nameString,
            priceString, newsString,
            stopString, timeStartString, timeEndString;

    private TextView latTextView, lngTextView;
    private double latADouble = 0, lngADouble;

    private LocationManager locationManager;
    private Criteria criteria;
    private boolean gpsABoolean, networkABoolean;
    private double userLatADouble, userLngADouble;
    private String latString, lngString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindWidget();

        //Create Spinner
        createSpinner();

        //Open Service Get Location
        openServiceGetLocation();

    }   // Main Method

    public void clickByGPS(View view) {

        latTextView.setText(Double.toString(userLatADouble));
        lngTextView.setText(Double.toString(userLngADouble));

    }

    @Override
    protected void onStop() {
        super.onStop();

        locationManager.removeUpdates(locationListener);

    }

    public Location myFindLocation(String strProvider, String strError) {

        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {

            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(strProvider);

        } else {
            Log.d("vanhub", "Cannot find Location ==> " + strError);
        }

        return location;
    }


    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            userLatADouble = location.getLatitude();
            userLngADouble = location.getLongitude();

        }   // onLocationChange

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        //for Location
        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER,
                "Cannot Connected Internet");
        if (networkLocation != null) {

            userLatADouble = networkLocation.getLatitude();
            userLngADouble = networkLocation.getLongitude();

        }

        Location gpsLocation = myFindLocation(LocationManager.GPS_PROVIDER, "No Card GPS");
        if (gpsLocation != null) {
            userLatADouble = gpsLocation.getLatitude();
            userLngADouble = gpsLocation.getLongitude();
        }

        Log.d("vanhub1", "Lat ==> " + userLatADouble);
        Log.d("vanhub1", "Lng ==> " + userLngADouble);

        // for Map
        Log.d("26April", "onResume ทำงาน");

        latADouble = getIntent().getDoubleExtra("Lat", 0);
        lngADouble = getIntent().getDoubleExtra("Lng", 0);

        if (latADouble == 0) {
            latTextView.setText(getResources().getString(R.string.unknow));
            lngTextView.setText(getResources().getString(R.string.unknow));
        } else {
            latTextView.setText(Double.toString(latADouble));
            lngTextView.setText(Double.toString(lngADouble));
        }

    }   // onResume



    public void clickMyMap(View view) {
        startActivity(new Intent(SignUpActivity.this, MyMaps.class));
    }



    private void openServiceGetLocation() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);

    }   //openServiceGetLocation

    private void createSpinner() {

        //Spinner of Stop Province
        final String[] stopProvinceStrings = getResources().getStringArray(R.array.stop);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, stopProvinceStrings);
        stopSpinner.setAdapter(stringArrayAdapter);

        stopSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stopString = stopProvinceStrings[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                stopString = stopProvinceStrings[0];
            }
        });

        //Spinner of Time Start
        final String[] timeStartStrings = getResources().getStringArray(R.array.time_start);
        ArrayAdapter<String> timeStartArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, timeStartStrings);
        timeStartSpinner.setAdapter(timeStartArrayAdapter);

        timeStartSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timeStartString = timeStartStrings[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                timeStartString = timeStartStrings[0];
            }
        });

        //Spinner of Time End
        final String[] timeEndStrings = getResources().getStringArray(R.array.time_stop);
        ArrayAdapter<String> timeEndArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, timeEndStrings);
        timeEndSpinner.setAdapter(timeEndArrayAdapter);

        timeEndSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timeEndString = timeEndStrings[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                timeEndString = timeEndStrings[0];
            }
        });

    }   // createSpinner

    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
        emailEditText = (EditText) findViewById(R.id.editText3);
        phoneEditText = (EditText) findViewById(R.id.editText4);
        nameEditText = (EditText) findViewById(R.id.editText8);
        priceEditText = (EditText) findViewById(R.id.editText10);
        newsEditText = (EditText) findViewById(R.id.editText13);
        stopSpinner = (Spinner) findViewById(R.id.spinner);
        timeStartSpinner = (Spinner) findViewById(R.id.spinner2);
        timeEndSpinner = (Spinner) findViewById(R.id.spinner3);
        latTextView = (TextView) findViewById(R.id.textView14);
        lngTextView = (TextView) findViewById(R.id.textView16);

    }   // bindWidget

    public void clickSaveData(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        priceString = priceEditText.getText().toString().trim();
        newsString = newsEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") ||
                passwordString.equals("") ||
                emailString.equals("") ||
                phoneString.equals("") ||
                priceString.equals("") ||
                nameString.equals("") ||
                newsString.equals("")) {

            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(SignUpActivity.this, R.drawable.danger,
                    "Have Space", "Please Fill All Blank");

        } else if (latADouble == 0) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, R.drawable.icon_myaccount, "ยังไม่ระบุตำแหน่ง",
                    "โปรดระบุตำแหน่ง");
        } else {
            //No Space
            confirmData();
        }

    }   // clickSaveData

    private void confirmData() {

        latString = latTextView.getText().toString();
        lngString = lngTextView.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_myaccount);
        builder.setTitle("โปรดตรวจข้อมูล");
        builder.setMessage(getResources().getString(R.string.user) + newsString + "\n" +
        "User = " + userString + "\n" +
        getResources().getString(R.string.pass) + passwordString + "\n" +
        getResources().getString(R.string.email) + emailString + "\n" +
        getResources().getString(R.string.tel) + phoneString + "\n" +
        getResources().getString(R.string.stop) + stopString + "\n" +
        getResources().getString(R.string.price) + priceString + "\n" +
        getResources().getString(R.string.timeStart) + timeEndString + "\n" +
        getResources().getString(R.string.timeEnd) + timeEndString + "\n" +
        getResources().getString(R.string.specialnews) + newsString + "\n" +
        getResources().getString(R.string.lat) + latString + "\n" +
        getResources().getString(R.string.lng) + lngString);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                updateToMySQL();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }   // confirmData

    private void updateToMySQL() {

        //Change Policy
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        try {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            nameValuePairs.add(new BasicNameValuePair("Name", nameString));
            nameValuePairs.add(new BasicNameValuePair("User", userString));
            nameValuePairs.add(new BasicNameValuePair("Password", passwordString));
            nameValuePairs.add(new BasicNameValuePair("Email", emailString));
            nameValuePairs.add(new BasicNameValuePair("Phone", phoneString));
            nameValuePairs.add(new BasicNameValuePair("Lat", latString));
            nameValuePairs.add(new BasicNameValuePair("Lng", lngString));
            nameValuePairs.add(new BasicNameValuePair("Stop", stopString));
            nameValuePairs.add(new BasicNameValuePair("Price", priceString));
            nameValuePairs.add(new BasicNameValuePair("timeStart", timeStartString));
            nameValuePairs.add(new BasicNameValuePair("timeEnd", timeEndString));
            nameValuePairs.add(new BasicNameValuePair("News", newsString));

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://swiftcodingthai.com/van/php_add_user_master.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpClient.execute(httpPost);

            Toast.makeText(SignUpActivity.this, "บันทึกข้อมูลเรียบร้อย ขอบคุณคะ", Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(SignUpActivity.this, "ไม่สามารอัพข้อมูลได้", Toast.LENGTH_SHORT).show();
        }

    }   // updateToMySQL

}   // Main Class
