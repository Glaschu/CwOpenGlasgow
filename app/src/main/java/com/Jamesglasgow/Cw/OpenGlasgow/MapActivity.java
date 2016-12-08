package com.Jamesglasgow.Cw.OpenGlasgow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by jamesglasgow on 04/12/2016.
 */

public class MapActivity  extends BaseActivity implements OnMapReadyCallback {
    SharedPreferences SharedPrefs;
    private GoogleMap mMap;
    public String latString;
    public String LongString;
    public String NameString;
    public String FillString;
    double Lat,Long;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /**
         * we will use layout inflater to add view in FrameLayout of our base activity layout
         * /
         * Adding our layout to parent class frame layout.
         */
        getLayoutInflater().inflate(R.layout.map_layout, frameLayout);

        /**
         * Setting title and itemChecked
         */
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        latString = extras.getString("EXTRA_LAT");
        LongString = extras.getString("EXTRA_LONG");
        NameString = extras.getString("EXTRA_NAME");
        FillString = extras.getString("EXTRA_FILL");
        Lat= Double.parseDouble(latString);
        Long= Double.parseDouble(LongString);
        SharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SetUpMap();

    }
    public void SetUpMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //Create the map and apply to the variable
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        switch (SharedPrefs.getInt("mc_TYPE",1)) {
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            default:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng CarPark = new LatLng(Lat, Long);
        mMap.addMarker(new MarkerOptions()
                .position(CarPark)
                .title(NameString)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.carpark_img)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(CarPark, 14.0f));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(CarPark));
    }

}