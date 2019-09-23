package com.example.tubes_pbp_kelompok3.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.tubes_pbp_kelompok3.DaftarPerusahaanActivity;
import com.example.tubes_pbp_kelompok3.LoginActivity;
import com.example.tubes_pbp_kelompok3.PerusahaanDAO;
import com.example.tubes_pbp_kelompok3.PerusahaanList;
import com.example.tubes_pbp_kelompok3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    List<PerusahaanDAO> Users;
    DatabaseReference databaseReference;
    ListView listViewP;
    private static final String TAG = "LoginActivity";

    public EditText mNama, mEmail, mPassword;
    private EditText mPekerjaan, mPendidikan, mPenempatan;
    private EditText mGajiBulanan, mUsiaMin, mUsiaMax;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ListView listview =(ListView) root.findViewById(R.id.listViewUsers);
        final String[] items = new String[] {"Item 1", "Item 2", "Item 3"};

        databaseReference = FirebaseDatabase.getInstance().getReference("Perusahaan");
        listViewP = (ListView) root.findViewById(R.id.listViewUsers);

        //list for store objects of user
        Users = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PerusahaanDAO User = Users.get(i);
                CallUpdateAndDeleteDialog(User.getNamaP(), User.getEmailP(),User.getPasswordP(),User.getJenis_pekerjaan(),
                        User.getPendidikan_minimum(), User.getLokasiP(), User.getGajiP(), User.getUsiaMin(),
                        User.getUsiaMax());
            }
        });

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });

        setAtribut();
        initListner();

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
                    PerusahaanDAO User = postSnapshot.getValue(PerusahaanDAO.class);
                    //adding User to the list
                    Users.add(User);
                }
                //creating Userlist adapter
                PerusahaanList UserAdapter = new PerusahaanList(getActivity(), Users);
                //attaching adapter to the listview
                listViewP.setAdapter(UserAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CallUpdateAndDeleteDialog(final String nama, final String email, String password, final String jenis_pekerjaan,
                                           final String pendidikan_minimum, final String lokasiP, final String gajiP, String usiaMinP,
                                           String usiaMaxP) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.manage_add_favorit, null);
        dialogBuilder.setView(dialogView);
        //Access Dialog views
        final EditText updateTextname = (EditText) dialogView.findViewById(R.id.updateNamaFav);
        final EditText updateTextemail = (EditText) dialogView.findViewById(R.id.updateEmailFav);
        final EditText updateTextJenisPekerjaan = (EditText) dialogView.findViewById(R.id.updateJenisPekerjaanFav);
        final EditText updatePendidikanMin= (EditText) dialogView.findViewById(R.id.updatePendidikanMinFav);
        final EditText updateLokasi= (EditText) dialogView.findViewById(R.id.updateLokasiFav);
        final EditText updateGaji= (EditText) dialogView.findViewById(R.id.updateGajiBulananFav);

        updateTextname.setText(nama);
        updateTextemail.setText(email);
        updateTextJenisPekerjaan.setText(jenis_pekerjaan);
        updateGaji.setText(gajiP);
        updatePendidikanMin.setText(pendidikan_minimum);
        updateLokasi.setText(lokasiP);
        final Button buttonAddFav = (Button) dialogView.findViewById(R.id.buttonAddFav);

        //username for set dialog title
        dialogBuilder.setTitle(nama);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonAddFav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //OnClickRegister();
                addFavorit(nama, email, jenis_pekerjaan, pendidikan_minimum, lokasiP, gajiP);
                Toast.makeText(getActivity(), "Success",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initListner() {

        // list item click listener
        listViewP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PerusahaanDAO User = Users.get(i);
                CallUpdateAndDeleteDialog(User.getNamaP(), User.getEmailP(),User.getPasswordP(),User.getJenis_pekerjaan(),
                        User.getPendidikan_minimum(), User.getLokasiP(), User.getGajiP(), User.getUsiaMin(),
                        User.getUsiaMax());
            }
        });
    }

    private void setAtribut(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.manage_add_favorit, null);
        dialogBuilder.setView(dialogView);

        mNama = dialogView.findViewById(R.id.updateNamaFav);
        mEmail = dialogView.findViewById(R.id.updateEmailFav);
        mPekerjaan = dialogView.findViewById(R.id.updateJenisPekerjaanFav);
        mPendidikan = dialogView.findViewById(R.id.updatePendidikanMinFav);
        mPenempatan = dialogView.findViewById(R.id.updateLokasiFav);
        mGajiBulanan = dialogView.findViewById(R.id.updateGajiBulananFav);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    private void OnClickRegister(){
        //String nama= mNama.getText().toString();
        //String email=mEmail.getText().toString();
        //String pekerjaan=mPekerjaan.getText().toString();
        //String pendidikan=mPendidikan.getText().toString();
        //String penempatan=mPenempatan.getText().toString();
        //String gaji=mGajiBulanan.getText().toString();

        //addFavorit(nama, email, pekerjaan, pendidikan, penempatan,gaji);

        Toast.makeText(getActivity(), "Success",Toast.LENGTH_SHORT).show();
    }

    private void addFavorit(String nama, String email, String pekerjaan, String pendidikan,
                               String penempatan, String gajiBulanan)
    {
        PerusahaanDAO perusahaanDAO = new PerusahaanDAO(nama, email, null, pekerjaan, pendidikan, penempatan,
                gajiBulanan, null, null);
        mDatabase.child("Favorit").child(nama).setValue(perusahaanDAO);
    }

}
