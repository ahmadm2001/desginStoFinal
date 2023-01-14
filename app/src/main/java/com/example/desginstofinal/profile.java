package com.example.desginstofinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static com.example.desginstofinal.FBref.refImages;
import static com.example.desginstofinal.FBref.refAuth;
import static com.example.desginstofinal.FBref.refImages;
import static com.example.desginstofinal.FBref.refstudent;
import java.io.File;
import java.io.IOException;

public class profile extends AppCompatActivity {

    TextView  nameview, phoneview, eTemail;
    ImageView iV;
    String iD="", name, phone, email;
    Intent intent;
    int Gallery = 1;
    Student customer;

    Intent i;
    Bundle bundle;

    Student student=null;
    /**
     * @author Ahmad mashal
     * @version V1.0
     * @since 7/4/2020 This activity will show the profile of the user, there is an option to upload image.
     */

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        i=getIntent();
        bundle=i.getExtras();
      
       // if(bundle==null){
            if(bundle.getSerializable("student")!=null) {

               // Toast.makeText(getApplicationContext(), "ddd", Toast.LENGTH_SHORT).show();
                  student = (Student)bundle.getSerializable("student");

            }
          //  Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
      //  }
        nameview = (TextView) findViewById(R.id.nameview);
        phoneview = (TextView) findViewById(R.id.phoneview);
        eTemail = (TextView) findViewById(R.id.eTemail);

        iV = (ImageView) findViewById(R.id.iV);

        FirebaseUser firebaseUser = refAuth.getCurrentUser();
        student.setEmail(refAuth.getCurrentUser().getEmail());
        iD = student.getUid();//firebaseUser.getUid();


        name = student.getName();
        phone = student.getPhone();
        //Toast.makeText(getApplicationContext(), customer.getEmail(), Toast.LENGTH_SHORT).show();
        email=refAuth.getCurrentUser().getEmail();//customer.getEmail();
        customerprof();
//        Query query = refstudent
//                .orderByChild("uid")
//                .equalTo(iD);
//            query.addListenerForSingleValueEvent(VEL);

//        Query query2 = refdrivr
//                .orderByChild("uid")
//                .equalTo(UID);
//        query2.addListenerForSingleValueEvent(VEL2);
    }


    com.google.firebase.database.ValueEventListener VEL = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dS) {
            /**
             * Listener for if customer is authenticated with the app
             * <p>
             * @param dS
             */
            if (dS.exists()) {
                for(DataSnapshot data : dS.getChildren()) {
                    customer = data.getValue(Student.class);
                    name = customer.getName();
                    phone = customer.getPhone();
                    //Toast.makeText(getApplicationContext(), customer.getEmail(), Toast.LENGTH_SHORT).show();
                    email=refAuth.getCurrentUser().getEmail();//customer.getEmail();
                    break;
                }
                customerprof();

            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    };




    /**
     * Selecting image file to upload to Firebase Storage
     * <p>
     *
     * @param view
     */
    public void upload(View view) {
        Intent si = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(si, Gallery);
    }

    /**
     * Uploading selected image file to Firebase Storage
     * <p>
     *
     * @param requestCode   The call sign of the intent that requested the result
     * @param resultCode    A code that symbols the status of the result of the activity
     * @param data          The data returned
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Gallery) {
                Uri file = data.getData();
                if (file != null) {
                    iV.setImageURI(file);
                    final ProgressDialog pd = ProgressDialog.show(this, "Upload image", "Uploading...", true);
                    StorageReference refImg = refImages.child("aaa.jpg");
                    refImg.putFile(file)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    pd.dismiss();
                                    Toast.makeText(profile.this, "Image Uploaded", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    pd.dismiss();
                                    Toast.makeText(profile.this, "Upload failed", Toast.LENGTH_LONG).show();
                                }
                            });
                } else {
                    Toast.makeText(this, "No Image was selected", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     * Downloading selected image file from Firebase Storage
     * <p>
     *
     * @param view
     */
    public void download(View view) throws IOException {
        final ProgressDialog pd = ProgressDialog.show(this, "Image download", "downloading...", true);

        StorageReference refImg = refImages.child("aaa.jpg");

        final File localFile = File.createTempFile("aaa", "jpg");
        refImg.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Toast.makeText(profile.this, "Image download success", Toast.LENGTH_LONG).show();
                String filePath = localFile.getPath();
                Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                iV.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                pd.dismiss();
                if(e!=null){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void customerprof() {
        /**
         * Shows the appropriate items for the customer's profile.
         * <p>
         */

        nameview.setText("Name :"+name);
        phoneview.setText("Phone :"+phone);
        eTemail.setText("Email: "+email);
    }


}