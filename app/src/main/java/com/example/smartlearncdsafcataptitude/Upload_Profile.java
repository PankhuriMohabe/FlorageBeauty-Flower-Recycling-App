package com.example.smartlearncdsafcataptitude;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class Upload_Profile extends AppCompatActivity {

    EditText dfullname,dusername,dmobile,dphone,daddress,dheight,dcomplexion,dbirthdate,dbirthtime,dbirthplace,dbloodgroup,dfoodhabits,dspectacles,ddisability,ddivorcee,dhobbies,dqualification,doccupation,dcurrentcity,dincome,downhome,dcaste,dgotra,dshakha,dzodiacsign,dnakshatra,dcharan,dgan,dnaddi,dmangal,dspecialinformation,doccupationoffamily,dexpectations,dmatchpatrika;
    ImageView userImage;
    Uri uri;
    String imageUrl;
    RadioButton radioMale,radiofemale;
    String txt_gender="";
    Button uploadprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__profile);


        userImage = (ImageView)findViewById(R.id.ivImage3);

        dfullname = (EditText) findViewById(R.id.dtxtname);
        dusername = (EditText)findViewById(R.id.dtxtUsername);
        dmobile = (EditText)findViewById(R.id.dtxtMobile);
        dphone = (EditText)findViewById(R.id.dtxtPhone);
        daddress = (EditText)findViewById(R.id.dtxtaddress);
        dheight = (EditText)findViewById(R.id.dtxtHeight);
        dcomplexion = (EditText)findViewById(R.id.dtxtcomplexion);
        dbirthdate = (EditText)findViewById(R.id.dtxtbirthdate);
        dbirthtime= (EditText)findViewById(R.id.dtxtbirthtime);
        dbirthplace= (EditText)findViewById(R.id.dtxtbirthplace);
        dbloodgroup = (EditText)findViewById(R.id.dtxtbloodgroup);
        dfoodhabits= (EditText)findViewById(R.id.dtxtfoodhabits);
        dspectacles = (EditText)findViewById(R.id.dtxtspecatacles);
        ddisability = (EditText)findViewById(R.id.dtxtDisability);
        ddivorcee = (EditText)findViewById(R.id.dtxtDivorcee);
        dhobbies = (EditText)findViewById(R.id.dtxthobbies);
        dqualification = (EditText)findViewById(R.id.dtxtqualification);
        doccupation = (EditText)findViewById(R.id.dtxtoccupation);
        dcurrentcity = (EditText)findViewById(R.id.dtxtcurrentcity);
        dincome = (EditText)findViewById(R.id.dtxtincome);
        downhome = (EditText)findViewById(R.id.dtxtownhome);
        dcaste = (EditText)findViewById(R.id.dtxtcaste);
        dgotra = (EditText)findViewById(R.id.dtxtgota);
        dshakha = (EditText)findViewById(R.id.dtxtshakha);
        dzodiacsign = (EditText)findViewById(R.id.dtxtzodiacsign);
        dnakshatra = (EditText)findViewById(R.id.dtxtnakshatra);
        dcharan = (EditText)findViewById(R.id.dtxtcharan);
        dgan = (EditText)findViewById(R.id.dtxtgan);
        dnaddi = (EditText)findViewById(R.id.dtxtnaddi);
        dmangal = (EditText)findViewById(R.id.dtxtmangal);
        doccupationoffamily = (EditText)findViewById(R.id.dtxtoccupation_of_family);
        dspecialinformation = (EditText)findViewById(R.id.dtxtDescription);
        dexpectations = (EditText)findViewById(R.id.dtxtexpectations);

        radioMale=(RadioButton)findViewById(R.id.radio_ninjas);
        radiofemale=(RadioButton)findViewById(R.id.radio_pirates);
        uploadprofile = (Button) findViewById(R.id.button2);
dmatchpatrika=(EditText)findViewById(R.id.dMatch_patrika);


    }


    public void onRadioButtonClicked(View view) {
        if(radioMale.isChecked()){
            txt_gender="Male";

        }
        if(radiofemale.isChecked()){
            txt_gender="female";
        }
    }




    public void btnSelectImage(View view) {

        Intent photoPicker =new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            uri =data.getData();
            userImage.setImageURI(uri);


        }
        else Toast.makeText(this,"you do not have selected any image please select an image",Toast.LENGTH_SHORT).show();
    }

    public void uploadImage(){
        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("Profiles").child(uri.getLastPathSegment());

        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Profile Uploading...please wait");
        progressDialog.show();


        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask =taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage =uriTask.getResult();
                imageUrl= urlImage.toString();
                uploadProfiles();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });


    }


    public void btnUploadProfiles(View view) {

        uploadprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {

                    String name = dfullname.getText().toString().trim();
                    String username=dusername.getText().toString().trim();
                    String mobile = dmobile.getText().toString().trim();
                    String phone=dphone.getText().toString().trim();
                    String address = daddress.getText().toString().trim();
                    String gender=txt_gender.getBytes().toString().trim();
                    String height = dheight.getText().toString().trim();
                    String complexion=dcomplexion.getText().toString().trim();
                    String dob = dbirthdate.getText().toString().trim();
                    String birthtime=dbirthtime.getText().toString().trim();
                    String bloodgroup= dbloodgroup.getText().toString().trim();
                    String birthplace=dbirthplace.getText().toString().trim();
                    String foodhabits= dfoodhabits.getText().toString().trim();
                    String spectacles=dspectacles.getText().toString().trim();
                    String disability= ddisability.getText().toString().trim();
                    String divorcee=ddivorcee.getText().toString().trim();
                    String caste = dcaste.getText().toString().trim();
                    String hobbies=dhobbies.getText().toString().trim();
                    String qualification = dqualification.getText().toString().trim();
                    String occupation=doccupation.getText().toString().trim();
                    String currentcity = dcurrentcity.getText().toString().trim();
                    String income=dincome.getText().toString().trim();
                    String ownhome = downhome.getText().toString().trim();
                    String gota =dgotra.getText().toString().trim();
                    String shakha=dshakha.getText().toString().trim();
                    String zodiacsign=dzodiacsign.getText().toString().trim();
                    String nakshatra = dnakshatra.getText().toString().trim();
                    String charan=dcharan.getText().toString().trim();
                    String gan =dgan.getText().toString().trim();
                    String naddi = dnaddi.getText().toString().trim();
                    String mangal=dmangal.getText().toString().trim();
                    String matchpatrika = dmatchpatrika.getText().toString().trim();
                    String specialinformation=dspecialinformation.getText().toString().trim();
                    String expectation=dexpectations.getText().toString().trim();
                    String occuptationoffamily=doccupationoffamily.getText().toString().trim();


                    if (TextUtils.isEmpty(name)) {
                        Toast.makeText(Upload_Profile.this, "please enter the valid name", Toast.LENGTH_SHORT).show();

                        if(TextUtils.isEmpty(specialinformation) ){
                            Toast.makeText(Upload_Profile.this, "please enter special information", Toast.LENGTH_SHORT).show();
                        }


                    } else{
                        uploadImage();
                    }



                }


            }
        });




    }


    private void uploadProfiles() {


               ProfileData profileData =new ProfileData(
                dfullname.getText().toString(),
                dusername.getText().toString(),
                dmobile.getText().toString(),
                dphone.getText().toString(),
                daddress.getText().toString(),
                txt_gender,
                dheight.getText().toString(),
                dcomplexion.getText().toString(),
                dbirthdate.getText().toString(),
                dbirthtime.getText().toString(),
                dbirthplace.getText().toString(),
                dbloodgroup.getText().toString(),
                dfoodhabits.getText().toString(),
                dspectacles.getText().toString(),
                ddisability.getText().toString(),
                ddivorcee.getText().toString(),
                dhobbies.getText().toString(),
                dqualification.getText().toString(),
                doccupation.getText().toString(),
                dcurrentcity.getText().toString(),
                dincome.getText().toString(),
                downhome.getText().toString(),
                dcaste.getText().toString(),
                dgotra.getText().toString(),
                dshakha.getText().toString(),
                dzodiacsign.getText().toString(),
                dnakshatra.getText().toString(),
                dcharan.getText().toString(),
                dgan.getText().toString(),
                dnaddi.getText().toString(),
                dmangal.getText().toString(),
                dspecialinformation.getText().toString(),
                doccupationoffamily.getText().toString(),
                dexpectations.getText().toString(),
                        imageUrl

                );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Profiles")
                .child(myCurrentDateTime).setValue(profileData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Upload_Profile.this,"Profile Uploaded",Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Profile.this,"Profile Uploaded Failed",Toast.LENGTH_SHORT).show();

            }
        });


    }

}
