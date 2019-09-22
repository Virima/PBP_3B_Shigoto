package com.example.tubes_pbp_kelompok3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PerusahaanList extends ArrayAdapter<PerusahaanDAO> {

    private Activity context;
    //list of users
    List<PerusahaanDAO> Users;

    public PerusahaanList(Activity context, List<PerusahaanDAO> Users) {
        super(context, R.layout.recycle_view, Users);
        this.context = context;
        this.Users = Users;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.recycle_view, null, true);
        //initialize
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.RVNama);
        TextView textviewemail = (TextView) listViewItem.findViewById(R.id.RVEmail);
        TextView textviewnumber = (TextView) listViewItem.findViewById(R.id.RVJenisPekerjaan);

        //getting user at position
        PerusahaanDAO User = Users.get(position);

        textViewName.setText(User.getNamaP());

        textviewemail.setText(User.getEmailP());

        textviewnumber.setText(User.getJenis_pekerjaan());

        return listViewItem;
    }
}
