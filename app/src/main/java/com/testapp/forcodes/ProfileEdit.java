package com.testapp.forcodes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileEdit extends AppCompatActivity {
    ImageView profileImage;
    Button changeProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        profileImage = findViewById(R.id.profileImage);
        changeProfileBtn = findViewById(R.id.changeProfileBtn);

//        StorageReference profileRef = storageRefrence.child("profile.jpg");

        changeProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when click go to gallery to pic image .

                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                profileImage.setImageURI(imageUri);

                // here i am creating a method to upload image to firebase
//                uploadImageToFirebae(imageUri);

            }
        }
    }

//    private void uploadImageToFirebae(Uri imageUri) {
//        // this method allows uploading images to the firebase database
//        //upload image to firebase storage
//        StorageReference fileRef = storageRefrence.chile("profile.jpg");
//        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(ProfileEdit.this, "Image UPloaded.", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(ProfileEdit.this, "Failed.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.option_menu,menu);
    return true;
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, ProfileEdit.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent intent1 = new Intent(this, ItemsList.class);
                startActivity(intent1);
                return true;
            case R.id.item3:
                Intent intent3 = new Intent(this,StudentList.class);
                startActivity(intent3);
                return true;
            case R.id.item4:
                Intent intent4= new Intent(this,Home.class);
                startActivity(intent4);
                return true;
            case R.id.item5:
                Toast.makeText(this, "Bye Bye...", Toast.LENGTH_SHORT).show();
                Intent intent5= new Intent(this,Login.class);
                startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}