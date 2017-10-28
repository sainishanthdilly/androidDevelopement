package com.example.nishanth.homework09;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
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

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.nishanth.homework09.R.id.masked;
import static com.example.nishanth.homework09.R.id.signUpRadioGroup;

public class Registration extends AppCompatActivity implements View.OnClickListener{

    private EditText email,password,confirm_password,first,last;
    RadioGroup rd;
    Button upload, reg, cancel;
    ImageView tv;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String email1,password1,first_name,last_name;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users_Table");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap output;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r = 0;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


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





        upload.setOnClickListener(this);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


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

    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);

        }

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.signup_upload){

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        }

        else {

            progressBar.setVisibility(View.VISIBLE);

            email1 = email.getText().toString();
            password1 = password.getText().toString();
            first_name = first.getText().toString();
            String confirm_pass = confirm_password.getText().toString();
            last_name = last.getText().toString();

            View focusView = null;
            boolean cancel = false;

            if (!TextUtils.isEmpty(password1) && !isPasswordValid(password1)) {
                password.setError("Enter password more than 8 characters");
                focusView = password;
                cancel = true;
            } else if (!TextUtils.isEmpty(email1) && !isEmailValid(email1)) {
                email.setError("Enter valid email");
                focusView = email;
                cancel = true;
            } else if (!password1.equals(confirm_pass)) {
                password.setError("passwords dont match");
                focusView = password;
                cancel = true;

            } else if (TextUtils.isEmpty(first_name)) {
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
                mAuth.createUserWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("Create", "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Registration.this, "mmn",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    setUserDetails();
                                }
                            }
                        });
            }
        }
    }

    private void setUserDetails() {

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();


        StorageReference userProfilePics = storageRef.child("userProfiles/"+ user.getUid()+".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap = getCircularBitmap(imageBitmap);
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

        private void UpdateDetails(Uri u) {

        Log.d(" Path ",u.getPath());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

          HashMap<String,Integer> f= new LinkedHashMap<>();
            f.put("self",1);


            User_Table user_table = new User_Table(user.getUid(),email1,first_name,last_name,String.valueOf(u), f,f,f);
            myRef.child(user.getUid()).setValue(user_table);

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
                            Intent i = new Intent(Registration.this,Profile.class);
                            startActivity(i);

                        }
                    }
                });


    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 8;
    }

    private boolean isFirstNameValid(String first) {
        return first.length()> 4;
    }

    private boolean isLastNameValid(String last) {
        //TODO: Replace this with your own logic
        return last.length() > 4;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            tv.setImageBitmap(getRoundedRectBitmap(imageBitmap,100));

           // mImageView.setImageBitmap(imageBitmap);
        }
    }



    public static Bitmap getRoundedRectBitmap(Bitmap bitmap, int pixels) {
        Bitmap result = null;
        try {
            result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);

            int color = 0xff424242;
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(rect);
            int roundPx = pixels;

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
        } catch (NullPointerException e) {
            // return bitmap;
        } catch (OutOfMemoryError o){}
        return result;
    }




}
