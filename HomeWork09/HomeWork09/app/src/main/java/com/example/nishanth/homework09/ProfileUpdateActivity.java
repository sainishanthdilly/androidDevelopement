package com.example.nishanth.homework09;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ProfileUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email,password,confirm_password,first,last;
    RadioGroup rd;
    Button upload, reg, cancel;
    ImageView tv;
    ProgressBar progressBar;
    LinearLayout linearLayout;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String email1,password1,first_name,last_name;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int PICK_IMAGE_REQUEST = 1;
    Bitmap imageBitmap;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users_Table");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email = (EditText) findViewById(R.id.signUP_Email);
        password = (EditText) findViewById(R.id.signup_password);
        confirm_password = (EditText)findViewById(R.id.signUp_ConfirmPassword);
        first = (EditText) findViewById(R.id.signup_first_name);
        last = (EditText) findViewById(R.id.signup_lastname);
        rd = (RadioGroup) findViewById(R.id.signUpRadioGroup);
        tv = (ImageView) findViewById(R.id.textView_signup_path);
        reg = (Button) findViewById(R.id.signup_register);
        cancel = (Button) findViewById(R.id.signup_cancel);
        upload  = (Button) findViewById(R.id.signup_upload);
        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.reg_progress);
        linearLayout = (LinearLayout) findViewById(R.id.regForm);
        linearLayout.removeViewAt(1);
        linearLayout.removeViewAt(1);

        email.setText(user.getEmail());
        email.setEnabled(false);

        String s = user.getDisplayName();

        first.setText(s.substring(0,s.indexOf(",")-1));

        last.setText(s.substring(s.indexOf(",")+1,s.length()));

        reg.setText("Update Profile");
        upload.setOnClickListener(this);

        Picasso.with(getApplicationContext()).load(user.getPhotoUrl()).into(tv);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("User1", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Userout", "onAuthStateChanged:signed_out");
                }

            }
        };

        reg.setOnClickListener(this);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void setUserDetails() {

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();

        StorageReference userProfilePics = storageRef.child("userProfiles/"+ user.getUid()+".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = userProfilePics.putBytes(data);
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


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                tv.setImageBitmap(imageBitmap);
                // Log.d(TAG, String.valueOf(bitmap));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.signup_upload){

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

        }

        else {

            progressBar.setVisibility(View.VISIBLE);

            email1 = email.getText().toString();
            first_name = first.getText().toString();
            last_name = last.getText().toString();

            View focusView = null;
            boolean cancel = false;

             if (TextUtils.isEmpty(first_name)) {
                first.setError(getString(R.string.error_field_required));
                focusView = first;
                cancel = true;
            } else if (TextUtils.isEmpty(last_name)) {
                last.setError(getString(R.string.error_field_required));
                focusView = last;
                cancel = true;
            }

            if (cancel) {
                focusView.setFocusable(true);
            } else {

                setUserDetails();


            }
        }

    }

    private void UpdateDetails(Uri u) {

        Log.d(" Path ",u.getPath());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        myRef.child(user.getUid()).child("first_name").setValue(first_name);
        myRef.child(user.getUid()).child("last_name").setValue(last_name);
        myRef.child(user.getUid()).child("profile_pic").setValue(String.valueOf(u));

        //User_Table user_table = new User_Table(user.getUid(),email1,first_name,last_name,String.valueOf(u), f,f,f);
        //myRef.child(user.getUid()).setValue(user_table);

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(first_name+", "+last_name)
                .setPhotoUri(u)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("profile", "User profile updated.");
                            progressBar.setVisibility(View.GONE);
                            Intent i = new Intent(ProfileUpdateActivity.this,Profile.class);
                            startActivity(i);


                        }
                    }
                });


    }



}
