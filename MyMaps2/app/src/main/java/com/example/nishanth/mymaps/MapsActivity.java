package com.example.nishanth.mymaps;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    LocationManager mLocMgr;
    LocationListener mlocList;
    boolean flag = true;
    LatLng st, ed;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;

    private Marker mStart;
    private Marker mEnd;
    ArrayList<LatLng> latLngArrayList;
    //private Marker mBrisbane;


    @Override
    protected void onResume() {
        super.onResume();
        latLngArrayList = new ArrayList<>();

        if (!mLocMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("GPS Enable").setMessage("Do you want to enable GPS ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    finish();

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        } else {

            mlocList = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10.0f));

                    LatLng l = new LatLng(location.getLatitude(), location.getLongitude());
//                    mStart.setPosition(l);
                 //   mEnd.setPosition(l);
                    latLngArrayList.add(l);

                    Log.d("locachanged", location.getAccuracy() + " " + location.getLatitude());
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            //mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocList);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mLocMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.setOnMapLongClickListener(this);

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(35.74, -79.1015625);

        //mMap.addMarker(mStart);
       // Polyline mpoly = mMap.addPolyline(new PolylineOptions().add(sydney).add(sydney1).width(5).color(Color.BLACK).geodesic(true));
        mMap.setMyLocationEnabled(true);

      //  Log.d("Location :    ",mMap.getMyLocation().getLatitude()+" " );
      //mMap.addMarker(new MarkerOptions());

        mLocMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    //mLocMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME , MIN_DISTANCE, (LocationListener) this);
      //  Location location = mLocMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        mStart = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker Start"));
        mEnd = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker End"));
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(-37.81319, 144.96298), new LatLng(-31.95285, 115.85734))
                .width(25)
                .color(Color.BLUE)
                .geodesic(true));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {


        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }


        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {


    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        if (flag == true) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
           // mMap.clear();
            mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 100, mlocList);

          //  mStart.setPosition(latLng);
          //  st = mStart.getPosition();
            //mMap.addPolyline(new PolylineOptions().add(latLng).add(sydney1).width(5).color(Color.BLACK).geodesic(true));

            Toast.makeText(getApplicationContext(), " Tracking Started", Toast.LENGTH_LONG).show();
            latLngArrayList.clear();
            mMap.clear();

            flag=false;
        }

        else{
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            ed = latLng;
            //mEnd.setPosition(ed);
            for(int i=0;i<latLngArrayList.size()-1;i++) {
                mMap.addPolyline(new PolylineOptions().add(latLngArrayList.get(i)).add(latLngArrayList.get(i+1)).width(5).color(Color.BLACK).geodesic(true));
            }

            mStart = mMap.addMarker(new MarkerOptions().position(latLngArrayList.get(0)).title("Marker Start"));
            mEnd = mMap.addMarker(new MarkerOptions().position(latLngArrayList.get(latLngArrayList.size()-1)).title("Marker End"));

            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for(int i=0;i<latLngArrayList.size()-1;i++) {
                builder.include(latLngArrayList.get(i));
            }

            builder.include(mEnd.getPosition());



            LatLngBounds bo = builder.build();
            CameraUpdate cu2 = CameraUpdateFactory.newLatLngBounds(bo,50);
            mMap.moveCamera(cu2);



            //mStart.setPosition(latLngArrayList.get(0));

            mLocMgr.removeUpdates(mlocList);
            Toast.makeText(getApplicationContext(), " Tracking Ended", Toast.LENGTH_LONG).show();

            flag=true;

        }

    }

}
