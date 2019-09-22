package com.example.tubes_pbp_kelompok3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LogoutFragmentActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private MenuItem menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.fragment_logout);
        FirebaseAuth.getInstance().signOut();

        menu = findViewById(R.id.nav_logout);
        menu.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                FirebaseAuth.getInstance().signOut();
                Logout();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return true;
            }
        });
    }

    private void Logout() {
        Intent intent = new Intent(getApplicationContext(), WelcomePageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
