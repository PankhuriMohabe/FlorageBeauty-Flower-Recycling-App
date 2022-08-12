package com.example.smartlearncdsafcataptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView gender,fullname,username,mobile,phone,address,height,complexion,birthdate,birthtime,birthplace,bloodgroup,foodhabits,spectacles,disability,divorcee,hobbies,qualification,occupation,currentcity,income,ownhome,caste,gotra,shakha,zodiacsign,nakshatra,charan,gan,naddi,mangal,specialinformation,occupationoffamily,expectations;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        gender = (TextView)findViewById(R.id.txtgender);
        profileImage=(ImageView)findViewById(R.id.ivImage2);
        fullname = (TextView)findViewById(R.id.txtname);
        username = (TextView)findViewById(R.id.txtUsername);
        mobile = (TextView)findViewById(R.id.txtMobile);
        phone = (TextView)findViewById(R.id.txtPhone);
        address = (TextView)findViewById(R.id.txtaddress);
        height = (TextView)findViewById(R.id.txtHeight);
        complexion = (TextView)findViewById(R.id.txtcomplexion);
        birthdate = (TextView)findViewById(R.id.txtbirthdate);
        birthtime= (TextView)findViewById(R.id.txtbirthtime);
        birthplace= (TextView)findViewById(R.id.txtbirthplace);
        bloodgroup = (TextView)findViewById(R.id.txtbloodgroup);
        foodhabits= (TextView)findViewById(R.id.txtfoodhabits);
        spectacles = (TextView)findViewById(R.id.txtspecatacles);
        disability = (TextView)findViewById(R.id.txtDisability);
        divorcee = (TextView)findViewById(R.id.txtDivorcee);
        hobbies = (TextView)findViewById(R.id.txthobbies);
        qualification = (TextView)findViewById(R.id.txtqualification);
        occupation = (TextView)findViewById(R.id.txtoccupation);
        currentcity = (TextView)findViewById(R.id.txtcurrentcity);
        income = (TextView)findViewById(R.id.txtincome);
        ownhome = (TextView)findViewById(R.id.txtownhome);
        caste = (TextView)findViewById(R.id.txtcaste);
        gotra = (TextView)findViewById(R.id.txtgota);
        shakha = (TextView)findViewById(R.id.txtshakha);
        zodiacsign = (TextView)findViewById(R.id.txtzodiacsign);
        nakshatra = (TextView)findViewById(R.id.txtnakshatra);
        charan = (TextView)findViewById(R.id.txtcharan);
        gan = (TextView)findViewById(R.id.txtgan);
        naddi = (TextView)findViewById(R.id.txtnaddi);
        mangal = (TextView)findViewById(R.id.txtmangal);
        occupationoffamily = (TextView)findViewById(R.id.txtoccupation_of_family);
        specialinformation = (TextView)findViewById(R.id.txtDescription);
        expectations = (TextView)findViewById(R.id.txtexpectations);




        Bundle mBundle =getIntent().getExtras();
        if(mBundle!=null){
            profileImage.setImageResource(mBundle.getInt("Image"));
            fullname.setText(mBundle.getString("fullname"));
            username.setText(mBundle.getString("Username"));
            mobile.setText(mBundle.getString("mobile"));
            phone.setText(mBundle.getString("phone"));
            address.setText(mBundle.getString("address"));
            gender.setText(mBundle.getString("gender"));
            height.setText(mBundle.getString("height"));
            complexion.setText(mBundle.getString("complexion"));
            birthdate.setText(mBundle.getString("birthdate"));
            birthtime.setText(mBundle.getString("birthtime"));
            birthplace.setText(mBundle.getString("birthplace"));
            bloodgroup.setText(mBundle.getString("bloodgroup"));
            foodhabits.setText(mBundle.getString("foodhabits"));
            spectacles.setText(mBundle.getString("spacacles"));
            disability.setText(mBundle.getString("disability"));
            hobbies.setText(mBundle.getString("hobbies"));
            qualification.setText(mBundle.getString("qualification"));
            occupation.setText(mBundle.getString("occupation"));
            currentcity.setText(mBundle.getString("currentcity"));
            income.setText(mBundle.getString("income"));
            ownhome.setText(mBundle.getString("ownhome"));
            caste.setText(mBundle.getString("caste"));
            gotra.setText(mBundle.getString("gotra"));
            shakha.setText(mBundle.getString("shakha"));
            zodiacsign.setText(mBundle.getString("zodiacsign"));
            nakshatra.setText(mBundle.getString("nakshatra"));
            charan.setText(mBundle.getString("charan"));
            divorcee.setText(mBundle.getString("divorcee"));
            gan.setText(mBundle.getString("gan"));
            naddi.setText(mBundle.getString("naadi"));
            mangal.setText(mBundle.getString("mangal"));
            specialinformation.setText(mBundle.getString("specialinformation"));
            occupationoffamily.setText(mBundle.getString("occupationoffamilymember"));
            expectations.setText(mBundle.getString("expectation"));
           // kundliImage.setImageResource(mBundle.getInt("kundliImage"));

            Glide.with(this)
                    .load(mBundle.getString("Image"))
                     .into(profileImage);

        }

    }

    public void addtocart(View view) {
        startActivity(new Intent(getApplicationContext(),Gallery.class));
    }
}
