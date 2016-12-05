package com.Jamesglasgow.Cw.OpenGlasgow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by jamesglasgow on 04/12/2016.
 */

public class MapActivity  extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public String latString;
    public String LongString;
    public String NameString;
    public String FillString;
    double Lat,Long;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        latString = extras.getString("EXTRA_LAT");
        LongString = extras.getString("EXTRA_LONG");
        NameString = extras.getString("EXTRA_NAME");
        FillString = extras.getString("EXTRA_FILL");
        Lat= Double.parseDouble(latString);
        Long= Double.parseDouble(LongString);
        setContentView(R.layout.map_layout);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng CarPark = new LatLng(Lat, Long);
        mMap.addMarker(new MarkerOptions().position(CarPark).title("Marker in Sydney"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(CarPark, 14.0f));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(CarPark));
    }

}