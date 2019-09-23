package com.example.tubes_pbp_kelompok3.ui.favorite;

import android.os.Bundle;
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

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    List<PerusahaanDAO> Users;
    DatabaseReference databaseReference;
    ListView listViewP;

    public EditText mNama, mEmail, mPassword, mJenisPerusahaan;
    private Spinner mPekerjaan, mPendidikan, mPenempatan;
    private EditText mUsiaMin, mUsiaMax, mGajiBulanan;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);

        ListView listview =(ListView) root.findViewById(R.id.listViewFavorit);
        final String[] items = new String[] {"Item 1", "Item 2", "Item 3"};

        databaseReference = FirebaseDatabase.getInstance().getReference("Favorit");
        listViewP = (ListView) root.findViewById(R.id.listViewFavorit);

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

        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

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

    private void CallUpdateAndDeleteDialog(String nama, final String email, String password, String jenis_pekerjaan,
                                           String pendidikan_minimum, String lokasiP, String gajiP, String usiaMinP,
                                           String usiaMaxP) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.manage_add_favorit, null);
        dialogBuilder.setView(dialogView);
        //Access Dialog views
        final EditText updateTextname = (EditText) dialogView.findViewById(R.id.updateNamaFav);
        final EditText updateTextemail = (EditText) dialogView.findViewById(R.id.updateEmailFav);
        final EditText updateTextJenisPekerjaan = (EditText) dialogView.findViewById(R.id.updateJenisPekerjaanFav);
        final EditText updateGaji= (EditText) dialogView.findViewById(R.id.updateGajiBulananFav);

        updateTextname.setText(nama);
        updateTextemail.setText(email);
        updateTextJenisPekerjaan.setText(jenis_pekerjaan);
        updateGaji.setText(gajiP);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteFav);

        //username for set dialog title
        dialogBuilder.setTitle(nama);
        final AlertDialog b = dialogBuilder.create();
        b.show();

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


    private boolean deleteUser(String id) {
        //getting the specified User reference
        DatabaseReference DeleteReference = FirebaseDatabase.getInstance().getReference("Favorit").child(id);
        //removing User
        DeleteReference.removeValue();
        Toast.makeText(getActivity().getApplicationContext(), "Perusahaan Favorit Dihapus", Toast.LENGTH_LONG).show();
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
}