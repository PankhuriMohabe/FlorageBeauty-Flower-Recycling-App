package com.example.smartlearncdsafcataptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    TextView txtMarquee;

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
        setContentView(R.layout.activity_dashboard);

        txtMarquee=(TextView)findViewById(R.id.marquee);
        txtMarquee.setSelected(true);




        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()){
                    case R.id.vijay:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.dashboard:
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



    }
}
