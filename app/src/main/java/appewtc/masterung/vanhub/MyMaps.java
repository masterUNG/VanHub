package appewtc.masterung.vanhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latADouble = 0, lngADouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }   // Main Method

    public void clickSaveLocation(View view) {

        if (latADouble ==0) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, R.drawable.icon_myaccount, "ยังไม่เลือกพิกัด",
                    "โปรดเลือกพิกัด คะ");
        } else {
            Intent intent = new Intent(MyMaps.this, SignUpActivity.class);
            intent.putExtra("Lat", latADouble);
            intent.putExtra("Lng", lngADouble);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //กำหนดจุดเริ่มต้นที่ อนุสาวรี
        LatLng latLng = new LatLng(13.76479216, 100.53831339);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

        //Create Marker When Click onMap
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                //Clear All Marker
                mMap.clear();

                //Show Marker
                mMap.addMarker(new MarkerOptions().position(latLng));

                //Get Lat, Lng
                latADouble = latLng.latitude;
                lngADouble = latLng.longitude;


            }   // onMapClick
        });


    }   // onMapReady

}   // Main Class
