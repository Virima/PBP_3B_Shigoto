package com.example.tubes_pbp_kelompok3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private Context context;
    private List<PerusahaanDAO> result;

    public RecycleAdapter(Context context, List<PerusahaanDAO> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycle_view, parent,false);
        final MyViewHolder holder =new MyViewHolder(itemView);
        return holder;
    }
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position){
        PerusahaanDAO PerusahaanDAO=result.get(position);
        holder.mNama.setText("Nama\t: "+PerusahaanDAO.getNamaP());
        holder.mEmail.setText("Email\t: "+PerusahaanDAO.getEmailP());
        holder.mJenisPekerjaan.setText("Jenis Pekerjaan\t: "+PerusahaanDAO.getJenis_pekerjaan());
        holder.mPendidikanMinimum.setText("Pendidikan Minimum\t: "+PerusahaanDAO.getPendidikan_minimum()+"\n");
        holder.mLokasi.setText("Lokasi\t: "+PerusahaanDAO.getLokasiP()+"\n");
        holder.mGaji.setText("Gaji\t: "+PerusahaanDAO.getGajiP()+"\n");
        holder.mUsiaMin.setText("Usia Min\t: "+PerusahaanDAO.getUsiaMin()+"\n");
        holder.mUsiaMax.setText("Usia Max\t: "+PerusahaanDAO.getUsiaMax()+"\n");

        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Oh You touch Me",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public int getItemCount() {
        return result.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNama, mEmail, mJenisPekerjaan, mPendidikanMinimum,
                        mLokasi, mGaji, mUsiaMin, mUsiaMax;
        private LinearLayout mParent;

        public MyViewHolder (@NonNull View itemView)
        {
            super(itemView);
            mNama=itemView.findViewById(R.id.RVNama);
            mEmail=itemView.findViewById(R.id.RVEmail);
            mJenisPekerjaan=itemView.findViewById(R.id.RVJenisPekerjaan);
            mPendidikanMinimum=itemView.findViewById(R.id.RVPendidikanMin);
            mLokasi=itemView.findViewById(R.id.RVLokasi);
            mGaji=itemView.findViewById(R.id.RVGaji);
            mUsiaMin=itemView.findViewById(R.id.RVUsiaMin);
            mUsiaMax=itemView.findViewById(R.id.RVUsiaMax);
            mParent=itemView.findViewById(R.id.Parent);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh You touch me?",Toast.LENGTH_SHORT).show();
        }
    }
}
