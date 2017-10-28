package com.example.nishanth.homework09;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.Locale.*;

public class TripDisplay extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener,Place_adapter.SendAdapterata {

    protected GoogleApiClient mGoogleApiClient;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase reference = FirebaseDatabase.getInstance();
    DatabaseReference childref;
    Chatroom chatroomt;
    private static final LatLngBounds BOUNDS_INDIA = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));

    private EditText mAutocompleteView;
    private RecyclerView mRecyclerView,placesrecycler;
    private LinearLayoutManager mLinearLayoutManager;
    private PlacesAutoCompleteAdapter mAutoCompleteAdapter;
    Place_adapter place_adapter;
    ImageView delete;
    Button finalisplace,chat_button;
    String chatroom_id, chatroom_name, username, userid,trip_icon,create_time;
    List<String> array_store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_display);
        chatroomt=new Chatroom();
       // childref = databaseReference.getRef(userid).getRef(chatid).setValue();
        Intent i = getIntent();
        chatroom_id = i.getStringExtra("trip_id");
        chatroom_name = i.getStringExtra("trip_name");
        username = i.getStringExtra("trip_creayedby");
        userid = i.getStringExtra("trip_userid");
        trip_icon=i.getStringExtra("trip_icon");
        create_time=i.getStringExtra("created_time");

        childref = reference.getReference(userid).child(chatroom_id);
        buildGoogleApiClient();
        array_store=new ArrayList<>();
        mAutocompleteView = (EditText)findViewById(R.id.autocomplete_places);

        delete=(ImageView)findViewById(R.id.cross);
        finalisplace=(Button)findViewById(R.id.final_places);
        chat_button=(Button)findViewById(R.id.chat);


        mAutoCompleteAdapter =  new PlacesAutoCompleteAdapter(this, R.layout.searchview_adapter,
                mGoogleApiClient, BOUNDS_INDIA, null);
        placesrecycler=(RecyclerView)findViewById(R.id.recycle_pl);

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mLinearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAutoCompleteAdapter);
        delete.setOnClickListener(this);
        mAutocompleteView.addTextChangedListener(new TextWatcher() {

            @SuppressLint("LongLogTag")
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.toString().equals("") && mGoogleApiClient.isConnected()) {
                    mAutoCompleteAdapter.getFilter().filter(s.toString());
                }else if(!mGoogleApiClient.isConnected()){
                    Toast.makeText(getApplicationContext(), Constants.API_NOT_CONNECTED,Toast.LENGTH_SHORT).show();
                    Log.e(Constants.PlacesTag,Constants.API_NOT_CONNECTED);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });


        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, int position) {
                        final PlacesAutoCompleteAdapter.PlaceAutocomplete item = mAutoCompleteAdapter.getItem(position);
                        final String placeId = String.valueOf(item.placeId);
                       mRecyclerView.setVisibility(View.GONE);
                        Log.i("TAG", "Autocomplete item selected: " + item.description);
                        mAutocompleteView.setText(item.description);
                        array_store.add(item.description+ "");
                        Log.d("abcccccc",array_store.toString());
                        placesrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                        place_adapter = new Place_adapter(array_store,TripDisplay.this);
                        placesrecycler.setAdapter(place_adapter);


                        /*
                             Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                         */

                    /*    PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                                .getPlaceById(mGoogleApiClient, placeId);
                        placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(PlaceBuffer places) {
                                if(places.getCount()==1){
                                    //Do the things here on Click....

                                   mAutocompleteView.setText(item.description);
                                    array_store.add(item.description+ "");
                                    Log.d("abcccccc",array_store.toString());


                                 //   Toast.makeText(getApplicationContext(),String.valueOf(places.get(0).getLatLng()),Toast.LENGTH_SHORT).show();
                                }else {

                                    Toast.makeText(getApplicationContext(),Constants.SOMETHING_WENT_WRONG,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Log.i("TAG", "Clicked: " + item.description);
                        Log.i("TAG", "Called getPlaceById to get Place details for " + item.placeId);*/
                    }
                })


        );


        mAutocompleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        finalisplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*chatroomt.setRoomid(chatroom_id);
                chatroomt.setRoomname(chatroom_name);
                chatroomt.setTrip_icon(trip_icon);
                chatroomt.setCreated_time(create_time);
                chatroomt.setCreated_by(username);
                chatroomt.setCreated_byid(userid);
                chatroomt.setPlaces_list(array_store);*/
                childref =childref.child("places_list");
                childref.setValue(array_store);
                Intent i = new Intent(TripDisplay.this,MapsActivity.class);
                startActivity(i);
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(TripDisplay.this, getDefault());
                ArrayList<LatLng> list = new ArrayList<LatLng>();

                for(int j=0;j<array_store.size();j++) {
                    try {
                        addresses = geocoder.getFromLocationName(array_store.get(j),1);
                        list.add(new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                try {
                    addresses = geocoder.getFromLocationName(array_store.get(0),1);
                    list.add(new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude()));
                } catch (IOException e) {
                    e.printStackTrace();
                }



                array_store.clear();

                Intent intent = new Intent(TripDisplay.this,PolygonAc.class);
                intent.putExtra("latlan",  list);
                startActivity(intent);


                // placesrecycler.setVisibility(View.GONE);
            }
        });
     chat_button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i=new Intent(getApplicationContext(),ChatActivity.class);
             i.putExtra("trip_id",chatroom_id);
             i.putExtra("trip_name",chatroom_name);
             i.putExtra("trip_creayedby",username);
             i.putExtra("trip_userid",userid);
             startActivity(i);
         }
     });

    }




    @Override
    public void onClick(View v) {
        if(v==delete){
            mAutocompleteView.setText("");
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.v("Google API Callback", "Connection Suspended");
        Log.v("Code", String.valueOf(i));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.v("Google API Callback","Connection Failed");
        Log.v("Error Code", String.valueOf(connectionResult.getErrorCode()));
        Toast.makeText(this, Constants.API_NOT_CONNECTED,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar_items,menu);
        return true;

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();
    }




    @Override
    public void onResume() {
        super.onResume();
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()){
            Log.v("Google API","Connecting");
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mGoogleApiClient.isConnected()){
            Log.v("Google API","Dis-Connecting");
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void sendAdapData(String position, boolean isFavCity) {

    }

    @Override
    public void senddeleteAdapData(int position) {
        array_store.remove(position);
        place_adapter.notifyDataSetChanged();




    }

    @Override
    public Context getContext() {
        return TripDisplay.this;
    }

    public void finalplace()
    {

    }

    public void updateProfile(MenuItem item){
        Intent i = new Intent(TripDisplay.this,ProfileUpdateActivity.class);
        startActivity(i);



    }

    public void logout(MenuItem item){

        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(TripDisplay.this,LoginActivity.class);
        startActivity(i);
    }




}
