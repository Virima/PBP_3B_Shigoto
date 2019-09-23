package com.example.tubes_pbp_kelompok3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PelamarList extends ArrayAdapter<PelamarDAO> {

    private Activity context;
    //list of users
    List<PelamarDAO> Users;

    public PelamarList(Activity context, List<PelamarDAO> Users) {
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
        TextView textviewalamat = (TextView) listViewItem.findViewById(R.id.RVJenisPekerjaan);

        //getting user at position
        PelamarDAO User = Users.get(position);

        textViewName.setText(User.getNama());

        textviewemail.setText(User.getEmail());

        textviewalamat.setText(User.getAlamat());

        return listViewItem;
    }
}
