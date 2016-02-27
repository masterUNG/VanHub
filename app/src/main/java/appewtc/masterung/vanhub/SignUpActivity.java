package appewtc.masterung.vanhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText, emailEditText,
            phoneEditText, locationEditText, nameEditText;
    private String userString, passwordString, emailString,
            phoneString, locationString, nameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindWidget();

    }   // Main Method

    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
        emailEditText = (EditText) findViewById(R.id.editText3);
        phoneEditText = (EditText) findViewById(R.id.editText4);
        locationEditText = (EditText) findViewById(R.id.editText5);
        nameEditText = (EditText) findViewById(R.id.editText8);

    }   // bindWidget

    public void clickSaveData(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();
        locationString = locationEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") ||
                passwordString.equals("") ||
                emailString.equals("") ||
                phoneString.equals("") ||
                locationString.equals("") ||
                nameString.equals("")) {

            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(SignUpActivity.this, R.drawable.danger,
                    "Have Space", "Please Fill All Blank");

        } else {

        } // if

    }   // clickSaveData

}   // Main Class
