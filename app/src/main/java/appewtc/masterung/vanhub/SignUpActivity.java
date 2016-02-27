package appewtc.masterung.vanhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindWidget();

        //Create Spinner
        createSpinner();

    }   // Main Method

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

        } else {

        } // if

    }   // clickSaveData

}   // Main Class
