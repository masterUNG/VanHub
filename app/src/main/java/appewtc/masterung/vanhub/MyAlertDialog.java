package appewtc.masterung.vanhub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by masterUNG on 1/26/16 AD.
 */
public class MyAlertDialog {

    public void myDialog(Context context, int intIcon, String strTitle, String strMessage) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);
        objBuilder.setIcon(intIcon);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setCancelable(false);    // นี่คือการทำให้ ปุ่ม undo ไม่ทำงาน
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objBuilder.show();

    }   // myDialog

}   // Main Class
