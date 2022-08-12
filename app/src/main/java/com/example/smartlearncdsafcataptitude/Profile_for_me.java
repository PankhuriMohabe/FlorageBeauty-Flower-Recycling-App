package com.example.smartlearncdsafcataptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Profile_for_me extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<ProfileData> myProfileData;
    ProfileData mProfileData;
    myAdapter myAdapter;
    EditText txt_Search;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    TextView txtMarquee;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_for_me);

        txtMarquee=(TextView)findViewById(R.id.marqueee);
        txtMarquee.setSelected(true);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Profile_for_me.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_Search = (EditText) findViewById(R.id.txt_Searchtext);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Profiles ...");

        myProfileData = new ArrayList<>();
        myAdapter = new myAdapter(Profile_for_me.this, myProfileData);
        mRecyclerView.setAdapter(myAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Profiles");
        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myProfileData.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    ProfileData profileData = itemSnapshot.getValue(ProfileData.class);
                    myProfileData.add(profileData);
                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();

            }
        });

        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });

    }



    private void filter(String text) {
        ArrayList<ProfileData>filterList =new ArrayList<>();
        for(ProfileData item : myProfileData){
            if(item.getGender().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        myAdapter.filteredList(filterList);
    }
}