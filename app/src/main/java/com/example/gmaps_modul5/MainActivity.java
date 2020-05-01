package com.example.gmaps_modul5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    Location currentLocation;
    private GoogleMap mMap;
    private ImageButton button;
    private ImageButton button2;
    private ImageButton button3;
    private Integer ntah;

    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= findViewById(R.id.imageButton2);
        button2= findViewById(R.id.imageButton);
        button3= findViewById(R.id.imageButton3);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntah =0;
                fetchLastLocation();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntah = 1;
                fetchLastLocation();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntah = 2;
                fetchLastLocation();
            }
        });


    }

    private void fetchLastLocation() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(),currentLocation.getLatitude()
                            +""+currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(MainActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);


        if (ntah == 0) {
            LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("HOME SWEET HOME").snippet("Perumahan Citra Sari, Rumbai Pesisir, Pekanbaru");
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            googleMap.addMarker(markerOptions);

            Toast.makeText(getApplicationContext(),"CURRENT LOCATION",Toast.LENGTH_SHORT).show();

        } else if (ntah == 1) {

            LatLng shs = new LatLng(0.5867559, 101.4298067);
            LatLng jhs = new LatLng(0.5934453, 101.4319104);
            LatLng es = new LatLng(0.5807977, 101.4394119);
            LatLng smait = new LatLng(0.5762139, 101.4320022);
            LatLng x = new LatLng(0.5855647, 101.4323245);

            MarkerOptions markerOptions2 = new MarkerOptions().position(shs).title("SMA CENDANA PEKANBARU").snippet("PT. Chevron Pacific Indonesia, Jl. Komp. Palem, Lembah Damai, Rumbai Pesisir, Pekanbaru City, Riau 28271");
            markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
            MarkerOptions markerOptions3 = new MarkerOptions().position(jhs).title("SMP CENDANA PEKANBARU").snippet("Lembah Damai, Kec. Rumbai Pesisir, Kota Pekanbaru, Riau 28271");
            markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
            MarkerOptions markerOptions4 = new MarkerOptions().position(es).title("SD CENDANA PEKANBARU").snippet("Lembah Damai, Kec. Rumbai Pesisir, Kota Pekanbaru, Riau 28271");
            markerOptions4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
            MarkerOptions markerOptions5 = new MarkerOptions().position(smait).title("SMA AL ITTIHAD PEKANBARU").snippet("SMAIT Al-Ittihad Jl. Yos Sudarso, Lembah Damai, Kec. Rumbai Pesisir, Kota Pekanbaru, Riau 28266");
            markerOptions5.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

            googleMap.animateCamera(CameraUpdateFactory.newLatLng(shs));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(shs, 15));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(jhs));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(jhs, 15));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(es));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(es, 15));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(smait));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(smait, 15));
            googleMap.addMarker(markerOptions2);
            googleMap.addMarker(markerOptions3);
            googleMap.addMarker(markerOptions4);
            googleMap.addMarker(markerOptions5);
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(x));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(x, 14));

            Toast.makeText(getApplicationContext(),"SCHOOLS",Toast.LENGTH_SHORT).show();
        } else {
            LatLng ah = new LatLng(0.5232301, 101.4496205);
            LatLng aw = new LatLng(0.5208092,101.4413658);
            LatLng sm = new LatLng(0.5273899,101.4400338);
            LatLng bha = new LatLng(0.520448,101.4468047);
            LatLng is = new LatLng(0.5256889,101.4345446);
            LatLng as = new LatLng(0.5232369,101.4401302);

            MarkerOptions markerOptions6 = new MarkerOptions().position(ah).title("RS ARIFIN AHMAD").snippet("Jl. Diponegoro No.2, Sumahilang, Kec. Pekanbaru Kota, Kota Pekanbaru, Riau 28156");
            markerOptions6.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            MarkerOptions markerOptions7 = new MarkerOptions().position(aw).title("RS AWAL BROS").snippet("Jl. Jend. Ahmad Yani No.73, Tanah Datar, Kec. Pekanbaru Kota, Kota Pekanbaru, Riau 28156");
            markerOptions7.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            MarkerOptions markerOptions8 = new MarkerOptions().position(sm).title("RS SANTA MARIA").snippet("Jl. Jend. Ahmad Yani No.68, RT./RW/RW.001/001, Pulau Karam, Kec. Sukajadi, Kota Pekanbaru, Riau 28127");
            markerOptions8.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            MarkerOptions markerOptions9 = new MarkerOptions().position(bha).title("RS BHAYANGKARA RIAU").snippet("Jl. RA Kartini No.14, Simpang Empat, Pekanbaru Kota, Simpang Empat, Kec. Pekanbaru Kota, Kota Pekanbaru, Riau 28156");
            markerOptions9.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            MarkerOptions markerOptions10 = new MarkerOptions().position(is).title("RS ISLAM IBNU SINA").snippet("Jl. Melati No.60, Harjosari, Kec. Sukajadi, Kota Pekanbaru, Riau 28122");
            markerOptions10.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));


            googleMap.animateCamera(CameraUpdateFactory.newLatLng(ah));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ah, 15));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(aw));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(aw, 15));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(sm));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sm, 15));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(bha));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bha, 15));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(is));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(is, 15));
            googleMap.addMarker(markerOptions6);
            googleMap.addMarker(markerOptions7);
            googleMap.addMarker(markerOptions8);
            googleMap.addMarker(markerOptions9);
            googleMap.addMarker(markerOptions10);
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(as));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(as, 14));

            Toast.makeText(getApplicationContext(),"HOSPITALS",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case  REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }
}