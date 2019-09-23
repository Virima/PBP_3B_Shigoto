package com.example.tubes_pbp_kelompok3.ui.edit_profile;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tubes_pbp_kelompok3.PelamarDAO;
import com.example.tubes_pbp_kelompok3.PelamarList;
import com.example.tubes_pbp_kelompok3.PerusahaanDAO;
import com.example.tubes_pbp_kelompok3.PerusahaanList;
import com.example.tubes_pbp_kelompok3.R;
import com.example.tubes_pbp_kelompok3.ui.home.HomeViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    List<PelamarDAO> Users;
    DatabaseReference databaseReference;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public EditText mNama, mEmail, mAlamat, mUsia;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.update_pelamar, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Pelamar");

        //list for store objects of user
        Users = new ArrayList<>();

        setAtribut();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearinxg the previous User list
                Users.clear();

                //getting all nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting User from firebase console
                    PelamarDAO User = postSnapshot.getValue(PelamarDAO.class);
                    //adding User to the list
                    Users.add(User);
                }
                //creating Userlist adapter
                PelamarList UserAdapter = new PelamarList(getActivity(), Users);
                //attaching adapter to the listview
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
    private void CallUpdateAndDeleteDialog(String userNama, String userEmail, String userPassword, String nama, final String email, String password, String jenis_pekerjaan,
                                           String pendidikan_minimum, String lokasiP, String gajiP, String usiaMinP,
                                           String usiaMaxP) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_pelamar, null);
        dialogBuilder.setView(dialogView);
        //Access Dialog views
        final EditText updateTextname = (EditText) dialogView.findViewById(R.id.updateNamaP);
        final EditText updateTextemail = (EditText) dialogView.findViewById(R.id.updateEmailP);
        final EditText updateTextJenisPekerjaan = (EditText) dialogView.findViewById(R.id.updateJenisPekerjaan);

        updateTextname.setText(nama);
        updateTextemail.setText(email);
        updateTextJenisPekerjaan.setText(jenis_pekerjaan);
        updateTextJenisPekerjaan.setText(jenis_pekerjaan);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateUser);

        //username for set dialog title
        dialogBuilder.setTitle(nama);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        // Click listener for Update data
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama= mNama.getText().toString().trim();
                String email=mEmail.getText().toString().trim();
                String alamat=mAlamat.getText().toString().trim();
                String usia=mUsia.getText().toString().trim();
                //checking if the value is provided or not Here, you can Add More Validation as you required

                if (!TextUtils.isEmpty(nama)) {
                    if (!TextUtils.isEmpty(email)) {
                        if (!TextUtils.isEmpty(email)) {
                            //Method for update data
                            updateUser(nama, email, alamat, usia);
                            b.dismiss();
                        }
                    }
                }
            }
        });
    }
    */

    private boolean updateUser(String nama, String email,String alamat, String usia) {

        DatabaseReference UpdateReference = FirebaseDatabase.getInstance().getReference("Pelamar").child(email);
        PelamarDAO User = new PelamarDAO(nama, email, null, alamat, usia, null,
                null, null, null,
                null, null, null);

        UpdateReference.setValue(User);
        Toast.makeText(getActivity().getApplicationContext(), "Profil Diperbaharui", Toast.LENGTH_LONG).show();
        return true;
    }

    private void setAtribut(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.manage_add_favorit, null);
        dialogBuilder.setView(dialogView);

        mNama = dialogView.findViewById(R.id.updateNamaFav);
        mEmail = dialogView.findViewById(R.id.updateEmailFav);
        mAlamat = dialogView.findViewById(R.id.updateJenisPekerjaanFav);
        mUsia = dialogView.findViewById(R.id.updatePendidikanMinFav);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

}