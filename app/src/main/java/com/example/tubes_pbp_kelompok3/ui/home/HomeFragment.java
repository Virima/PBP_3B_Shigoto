package com.example.tubes_pbp_kelompok3.ui.home;

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
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_pbp_kelompok3.HomeFragmentActivity;
import com.example.tubes_pbp_kelompok3.PerusahaanDAO;
import com.example.tubes_pbp_kelompok3.PerusahaanList;
import com.example.tubes_pbp_kelompok3.R;
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

    public EditText mNama, mEmail, mPassword, mJenisPerusahaan;
    private Spinner mPekerjaan, mPendidikan, mPenempatan;
    private EditText mUsiaMin, mUsiaMax, mGajiBulanan;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ListView listview =(ListView) root.findViewById(R.id.listViewPerusahaan);
        final String[] items = new String[] {"Item 1", "Item 2", "Item 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PerusahaanDAO User = Users.get(i);
                CallUpdateAndDeleteDialog(User.getNamaP(), User.getEmailP(),User.getPasswordP(),User.getJenis_pekerjaan(),
                        User.getPendidikan_minimum(), User.getLokasiP(), User.getGajiP(), User.getUsiaMin(),
                        User.getUsiaMax());;
            }
        });

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });

        // method for find ids of views
        findViews();


        // to maintian click listner of views
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
                PerusahaanList UserAdapter = new PerusahaanList(getActivity(), Users); //---------- -_- ---------//
                //attaching adapter to the listview
                listViewP.setAdapter(UserAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CallUpdateAndDeleteDialog(String nama, final String email, String password, String jenis_pekerjaan,
                                           String pendidikan_minimum, String lokasiP, String gajiP, String usiaMinP,
                                           String usiaMaxP) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_perusahaan, null);
        dialogBuilder.setView(dialogView);
        //Access Dialog views
        final EditText updateTextname = (EditText) dialogView.findViewById(R.id.updateNamaP);
        final EditText updateTextemail = (EditText) dialogView.findViewById(R.id.updateEmailP);
        final EditText updateTextmobileno = (EditText) dialogView.findViewById(R.id.updateJenisPekerjaan);
        updateTextname.setText(nama);
        updateTextemail.setText(email);
        updateTextmobileno.setText(jenis_pekerjaan);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateUser);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteUser);
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
                String password=mPassword.getText().toString().trim();
                String pekerjaan=mPekerjaan.getSelectedItem().toString().trim();
                String usiaMin=mUsiaMin.getText().toString().trim();
                String usiaMax=mUsiaMax.getText().toString().trim();
                String pendidikan=mPendidikan.getSelectedItem().toString().trim();
                String penempatan=mPenempatan.getSelectedItem().toString().trim();
                String gaji=mGajiBulanan.getText().toString();
                //checking if the value is provided or not Here, you can Add More Validation as you required

                if (!TextUtils.isEmpty(nama)) {
                    if (!TextUtils.isEmpty(email)) {
                        if (!TextUtils.isEmpty(email)) {
                            //Method for update data
                            updateUser(nama, email, password, pekerjaan,
                                    pendidikan, penempatan, gaji, usiaMin, usiaMax);
                            b.dismiss();
                        }
                    }
                }

            }
        });

        // Click listener for Delete data
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Method for delete data
                deleteUser(email);
                b.dismiss();
            }
        });
    }

    private boolean updateUser(String nama, String email, String password, String jenis_pekerjaan,
                               String pendidikan_minimum, String lokasiP, String gajiP, String usiaMinP, String usiaMaxP) {
        //getting the specified User reference
        DatabaseReference UpdateReference = FirebaseDatabase.getInstance().getReference("Perusahaan").child(email);
        PerusahaanDAO User = new PerusahaanDAO(nama, email, password, jenis_pekerjaan, pendidikan_minimum,
                lokasiP, gajiP, usiaMinP, usiaMaxP);
        //update  User  to firebase
        UpdateReference.setValue(User);
        Toast.makeText(getActivity().getApplicationContext(), "Perusahaan Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteUser(String id) {
        //getting the specified User reference
        DatabaseReference DeleteReference = FirebaseDatabase.getInstance().getReference("Perusahaan").child(id);
        //removing User
        DeleteReference.removeValue();
        Toast.makeText(getActivity().getApplicationContext(), "Perusahaan Deleted", Toast.LENGTH_LONG).show();
        return true;
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

    private void findViews() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Perusahaan");


        listViewP = (ListView) getView().findViewById(R.id.listViewPerusahaan);

        //list for store objects of user
        Users = new ArrayList<>();

    }


}
