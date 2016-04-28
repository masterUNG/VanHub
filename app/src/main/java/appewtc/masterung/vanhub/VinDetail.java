package appewtc.masterung.vanhub;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class VinDetail extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String nameString, latString, lngString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vin_map);

        nameString = getIntent().getStringExtra("Name");
        latString = getIntent().getStringExtra("Lat");
        lngString = getIntent().getStringExtra("Lng");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_vin);
        mapFragment.getMapAsync(this);
    }   // Main Method


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double douLat = Double.parseDouble(latString);
        double douLng = Double.parseDouble(lngString);

        LatLng latLng = new LatLng(douLat, douLng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.build4))
        .title(nameString));


    }   // onMap
}   // Main Class
