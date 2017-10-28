package com.example.nishanth.homework09;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements Message_leftadapter.SendAdapterata {
    RecyclerView recyclerView;
    TextView chatrrom_message,no_add;
    ImageButton send, image_upload,back;
    String chatroom_id, chatroom_name, username, userid;
    Message_pojo message = new Message_pojo();
    String message_fromuser;
    List<Message_pojo> messagearrayList = new ArrayList<>();
    Message_leftadapter leftadapter;
    String message_id;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;
    ImageView img2;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    int counter;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences preferences;
    String current_time=String.valueOf(System.currentTimeMillis());
    BufferedInputStream bufferedReader;
    Uri filePath;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childref;
    DatabaseReference seconReference;
    ProgressDialog progressDialog;
    User_Table user1;
    String img_user;
    String tripchatroom_name;
    String tripusername;
    String tripuserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Intent i = getIntent();
        chatroom_id = i.getStringExtra("trip_id");
        tripchatroom_name = i.getStringExtra("trip_name");
        tripusername = i.getStringExtra("trip_creayedby");
        tripuserid = i.getStringExtra("trip_userid");
        childref = databaseReference.child(tripuserid);
        seconReference = childref.child(chatroom_id);

        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        user1=new User_Table();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user1.setUser_id(user.getUid());
        username = user.getDisplayName();
        img_user=user.getPhotoUrl()+"";
        Log.d("imdfg",img_user);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("userid", userid);
        editor.commit();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_addchat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        chatrrom_message = (TextView) findViewById(R.id.enter_message);
        img2 = (ImageView) findViewById(R.id.imgmessage);
        send = (ImageButton) findViewById(R.id.Enter);
        image_upload = (ImageButton) findViewById(R.id.image_upload);

        progressDialog = new ProgressDialog(this);


        datachange_databse();   //Onload print if any messages in db

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                message_fromuser = chatrrom_message.getText().toString();
                message=new Message_pojo();
                message.setActual_message(message_fromuser);
                chatrrom_message.setText("");
                if (message.getActual_message().equals("")|| message.getActual_message().equals(null)) {
                    Toast.makeText(getApplicationContext(),"Please Enter message",Toast.LENGTH_LONG).show();

                }
                else {

                    HashMap<String, Integer> mp = new HashMap<String, Integer>();
                    mp.put("self",1);

                    message.setMessage_userid(userid);                    //USERID AND USERNAME FROM INTENT
                    message.setMessage_username(username);
                    message.setMessage_time(current_time);
                    message_id = childref.child(chatroom_id).push().getKey();
                    message.setMessage_id(message_id);
                    message.setImage_message(null);
                    message.setUserImage(img_user);
                    message.setDeleted(mp);
                    childref.child(chatroom_id).child(message_id).setValue(message);
                    datachange_databse();
                }
            }
        });

        image_upload.setOnClickListener(new View.OnClickListener() {
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


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar_items,menu);
        return true;

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

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
            StorageReference userProfilePics = storageRef.child("userProfiles/" + Math.random() + ".jpg");
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

          /*  if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                InputStream inputStream=null;
                String image_date=null;
                try {
                     inputStream = getApplicationContext().getContentResolver().openInputStream(data.getData());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
               // Bundle extras = data.getExtras();
                try {
                    image_date= converter(inputStream);
                    Log.d("kl",image_date);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                 bufferedReader = new BufferedInputStream(inputStream);
                imageBitmap = BitmapFactory.decodeStream(bufferedReader);
                //imageBitmap = (Bitmap) extras.get(image_date);
                if(imageBitmap==null)
                {
                    Toast.makeText(getApplicationContext(),"No image selected",Toast.LENGTH_LONG).show();
                }

                StorageReference storageRef = storage.getReference();


                StorageReference userProfilePics = storageRef.child("userProfiles/" + userid + ".jpg");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data1 = baos.toByteArray();

                UploadTask uploadTask = userProfilePics.putBytes(data1);
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

                        UpdateDetails(downloadUrl);
                    }
                });


                // img2.setImageBitmap((imageBitmap));

                // mImageView.setImageBitmap(imageBitmap);
            }  */

    }


    private void UpdateDetails(Uri u) {

        Log.d(" Path ",u.getPath());
        String s=u.toString();
        Log.d("sx",s);

        childref = databaseReference.child(userid);
        seconReference = childref.child(chatroom_id);
        message=new Message_pojo();
       // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        message.setMessage_userid(userid);                    //USERID AND USERNAME FROM INTENT
        message.setMessage_username(username);
        message_id = childref.child(chatroom_id).push().getKey();
        message.setMessage_id(message_id);
        message.setMessage_time(current_time);
        message.setUserImage(img_user);
        message.setImage_message(s);
        HashMap<String, Integer> mp = new HashMap<String, Integer>();
        mp.put("self",1);
        message.setDeleted(mp);

        childref.child(chatroom_id).child(message_id).setValue(message);
        datachange_databse();



    }

    @Override
    public void sendAdapData(String position, boolean isFavCity) {



    }

    @Override
    public void senddeleteAdapData(String position) {
        Log.d("dele",position);

        childref.child(chatroom_id).child(position).child("deleted").child(userid).setValue(1);

        datachange_databse();



    }

    @Override
    public Context getContext() {
        return ChatActivity.this;
    }


    public  void datachange_databse() {


        if (seconReference != null) {
            seconReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    messagearrayList.clear();
                    for (DataSnapshot datasnp : dataSnapshot.getChildren()) {

                        if(datasnp.getKey().equals("created_by") || datasnp.getKey().equals("created_byid") ||
                                datasnp.getKey().equals("roomid") ||
                                datasnp.getKey().equals("roomname") || datasnp.getKey().equals("created_time")||datasnp.getKey().equals("trip_icon")||datasnp.getKey().equals("places_list"))
                        {

                        }
                        else
                        {
                            Message_pojo All_Messages = datasnp.getValue(Message_pojo.class);


                            if(All_Messages.getDeleted()!=null ) {
                                if (!All_Messages.getDeleted().keySet().contains(userid)) {
                                    messagearrayList.add(All_Messages);
                                }

                        }

                        }

                    }
                    if(messagearrayList!=null) {


                        leftadapter = new Message_leftadapter(messagearrayList,ChatActivity.this,userid);
                        recyclerView.setAdapter(leftadapter);

                    }
                }



                @Override
                public void onCancelled(DatabaseError databaseError) {
                }


            });
        }


    }

    public String converter(InputStream in) throws IOException {
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String read;

        while((read=br.readLine()) != null) {
            //System.out.println(read);
            sb.append(read);
        }

        br.close();
        return sb.toString();
    }


    public void updateProfile(MenuItem item){
        Intent i = new Intent(ChatActivity.this,ProfileUpdateActivity.class);
        startActivity(i);



    }

    public void logout(MenuItem item){

        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(ChatActivity.this,LoginActivity.class);
        startActivity(i);
    }

}