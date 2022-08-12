package com.example.smartlearncdsafcataptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<ProfileData> myProfileData;
    ProfileData mProfileData;
    myAdapter myAdapter;
    EditText txt_Search;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.vijay_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item2:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                Toast.makeText(this,"Logout succesfully",Toast.LENGTH_SHORT);
                return true;
            case R.id.item1:
                startActivity(new Intent(getApplicationContext(),Profile_for_me.class));
                return true;
            case R.id.item3:
                Toast.makeText(this,"notification is selected",Toast.LENGTH_SHORT);
                return true;
            case R.id.item4:
                startActivity(new Intent(getApplicationContext(),Upload_Profile.class));
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.vijay);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()){
                    case R.id.vijay:
                        return true;
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),
                                Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.register:
                        startActivity(new Intent(getApplicationContext(),
                                DetailActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nev:
                        startActivity(new Intent(getApplicationContext(),
                                Gallery.class));


                }
                return false;
            }
        });




        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        txt_Search=(EditText)findViewById(R.id.txt_Searchtext);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Product ...");

        myProfileData = new ArrayList<>();
        myAdapter  =new myAdapter(MainActivity.this,myProfileData);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Profiles");
        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myProfileData.clear();

                for(DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
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
        if(item.getUsername().toLowerCase().contains(text.toLowerCase())){
            filterList.add(item);
        }
    }
        myAdapter.filteredList(filterList);
}


    public void btn_uploadActivity(View view) {
        startActivity(new Intent(this,Upload_Profile.class));
    }
}
