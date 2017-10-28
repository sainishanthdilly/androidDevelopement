package com.example.nishanth.homework09;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddChatroom extends AppCompatActivity implements Chatroom_adapter.SendAdapterata {
    RecyclerView recyclerView;
    TextView chatrrom_name,no_add;
    String chatname_addroom;
    String chatroom_id,image_url;
    Chatroom chatroom;
    FloatingActionButton chatadd_roombutton;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Uri filePath;
    ImageButton back,icontrip;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childref;
    DatabaseReference intialchilref;

    Chatroom_adapter adaptor;
    List<Chatroom> chatroomarrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users_Table");
    FirebaseStorage storage = FirebaseStorage.getInstance();
    static String  sortY ="SORTBYYEAR";
    String username;
    String current_time=String.valueOf(System.currentTimeMillis());
    ProgressDialog progressDialog;

    User_Table user1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            InputStream inputStream = null;
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            progressDialog.show();
            StorageReference storageRef = storage.getReference();
            StorageReference userProfilePics = storageRef.child("Tripcon/" + Math.random() + ".jpg");
            UploadTask uploadTask = userProfilePics.putFile(filePath);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    progressDialog.dismiss();
                    UpdateDetails(downloadUrl);
                }
            });


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);
        super.onCreate(savedInstanceState);
        chatroom=new Chatroom();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot children: dataSnapshot.getChildren()) {
                    if (user.getUid().equals(children.getKey())) {
                       user1 = children.getValue(User_Table.class);

                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //user1.setUser_id(user.getUid());
        username = user.getDisplayName();

        chatroomarrayList=new ArrayList<Chatroom>();

        setContentView(R.layout.activity_add_chatroom);
        recyclerView= (RecyclerView) findViewById(R.id.recycle_addchatroom);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        chatrrom_name=(TextView)findViewById(R.id.chatname_add);
        chatadd_roombutton=(FloatingActionButton) findViewById(R.id.chat_addbutton);
        no_add=(TextView)findViewById(R.id.not_addded);
        icontrip=(ImageButton)findViewById(R.id.imagetripicon);
        intialchilref=databaseReference;
                //.child(user1.getUser_id());

        icontrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,  REQUEST_IMAGE_CAPTURE);

               /* Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } */


            }
        });

        if (intialchilref!= null) {
            intialchilref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    chatroomarrayList.clear();
                    for (DataSnapshot datasnp : dataSnapshot.getChildren()) {

                        if (datasnp.getKey().equals(user1.getUser_id()) ) {


                            for (DataSnapshot datasnp1 : datasnp.getChildren()) {


                                Chatroom chatroom = datasnp1.getValue(Chatroom.class);
                                chatroomarrayList.add(chatroom);



                                if (chatroomarrayList != null) {
                                    adaptor = new Chatroom_adapter(chatroomarrayList, AddChatroom.this, username);
                                    recyclerView.setAdapter(adaptor);
                                }
                            }
                        }
                        else{
                            Set<String> friendRooms  = user1.getFriends().keySet();
                            if(friendRooms.contains(datasnp.getKey().toString())){
                                for (DataSnapshot datasnp1 : datasnp.getChildren()) {


                                    Chatroom chatroom = datasnp1.getValue(Chatroom.class);
                                    chatroomarrayList.add(chatroom);



                                    if (chatroomarrayList != null) {
                                        adaptor = new Chatroom_adapter(chatroomarrayList, AddChatroom.this, username);
                                        recyclerView.setAdapter(adaptor);
                                    }
                                }


                            }

                        }

                    }
                }



                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });
        }
    }

    @Override
    public void sendAdapData(String position, boolean isFavCity) {

    }

    @Override
    public void senddeleteAdapData(String position, int pos) {

     if(chatroomarrayList.get(pos).getCreated_by().equals(user1.getUser_id())){
         intialchilref.child(user1.getUser_id()).child(position).removeValue();

     }


    }

    @Override
    public Context getContext() {
        return null;
    }

    private void UpdateDetails(Uri u) {

        Log.d(" Path ",u.getPath());
        String s=u.toString();
        Log.d("sx",s);
        image_url=s;

        chatadd_roombutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no_add.setVisibility(View.GONE);
                chatname_addroom=chatrrom_name.getText().toString();
                childref = databaseReference.child(user1.getUser_id());

                if(image_url=="" || image_url==null)
                {

                }
                else {
                    chatroom=new Chatroom();
                    chatroom_id= childref.push().getKey();
                    chatroom.setRoomid(chatroom_id);
                    chatroom.setRoomname(chatname_addroom);
                    chatroom.setCreated_byid(user1.getUser_id());
                    chatroom.setCreated_by(username);
                    chatroom.setCreated_time(current_time);
                    chatroom.setTrip_icon(image_url);
                    Log.d("im",image_url);

                    childref.child(chatroom_id).setValue(chatroom);
                    if (childref != null) {
                        childref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                chatroomarrayList.clear();
                                for (DataSnapshot datasnp : dataSnapshot.getChildren()) {

                                    Chatroom chatroom = datasnp.getValue(Chatroom.class);
                                    chatroomarrayList.add(chatroom);

                                }
                                if (chatroomarrayList != null) {
                                    adaptor = new Chatroom_adapter(chatroomarrayList, AddChatroom.this, username);
                                    recyclerView.setAdapter(adaptor);
                                }
                            }


                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }


                        });
                    }


                }

            }
        });




    }




}
