package com.example.tubes_pbp_kelompok3.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tubes_pbp_kelompok3.DaftarPelamarActivity;
import com.example.tubes_pbp_kelompok3.HomeFragmentActivity;
import com.example.tubes_pbp_kelompok3.LogoutFragmentActivity;
import com.example.tubes_pbp_kelompok3.R;
import com.example.tubes_pbp_kelompok3.WelcomePageActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;
    private FirebaseAuth mAuth;
    private MenuItem menu;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

                Intent intent = new Intent(getActivity(), WelcomePageActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

}