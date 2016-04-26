package appewtc.masterung.vanhub;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }   // Main Method

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

                //Show Marker
                mMap.addMarker(new MarkerOptions().position(latLng));

            }   // onMapClick
        });


    }   // onMapReady

}   // Main Class
