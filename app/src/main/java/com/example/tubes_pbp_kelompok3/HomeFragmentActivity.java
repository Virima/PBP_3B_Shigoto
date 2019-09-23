package com.example.tubes_pbp_kelompok3;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentActivity extends AppCompatActivity {

    EditText editTextName, editTextEmail, editTextNumber;
    ListView listViewP;
    Fragment home;

    List<PerusahaanDAO> Users;
    DatabaseReference databaseReference;

    public EditText mNama, mEmail, mPassword;
    private Spinner mPekerjaan, mPendidikan, mPenempatan;
    private EditText mUsiaMin, mUsiaMax, mGajiBulanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // method for find ids of views
        findViews();


        // to maintian click listner of views
        initListner();

    }

    private void findViews() {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        listViewP = (ListView) findViewById(R.id.listViewPerusahaan);
        //list for store objects of user
        Users = new ArrayList<>();

    }

    @Override
    protected void onStart() {
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
                PerusahaanList UserAdapter = new PerusahaanList(HomeFragmentActivity.this, Users);
                //attaching adapter to the listview
                listViewP.setAdapter(UserAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

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

    private void CallUpdateAndDeleteDialog(String nama, final String email, String password, String jenis_pekerjaan,
                                           String pendidikan_minimum, String lokasiP, String gajiP, String usiaMinP,
                                           String usiaMaxP) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
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
        Toast.makeText(getApplicationContext(), "Perusahaan Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteUser(String id) {
        //getting the specified User reference
        DatabaseReference DeleteReference = FirebaseDatabase.getInstance().getReference("Perusahaan").child(id);
        //removing User
        DeleteReference.removeValue();
        Toast.makeText(getApplicationContext(), "Perusahaan Deleted", Toast.LENGTH_LONG).show();
        return true;
    }
}
