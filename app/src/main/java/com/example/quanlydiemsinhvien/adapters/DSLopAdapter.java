package com.example.quanlydiemsinhvien.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.Card_View_Model;

import java.util.ArrayList;

public class DSLopAdapter extends  RecyclerView.Adapter<DSLopAdapter.ViewHolder>{
    private ArrayList<Card_View_Model> danhsachlop;
    private Context context;

    public DSLopAdapter(Context context, ArrayList<Card_View_Model> danhsachlop) {
        this.danhsachlop = danhsachlop;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Card_View_Model aCard = danhsachlop.get(position);
        holder.mTxtTenLHP.setText(aCard.getTenLHP());
        holder.mTxtMaLHP.setText(aCard.getMaLHP());
    }

    @Override
    public int getItemCount() {
        return danhsachlop.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtMaLHP;
        private TextView mTxtTenLHP;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtMaLHP = (TextView) itemView.findViewById(R.id.txtMaLHP);
            mTxtTenLHP = (TextView) itemView.findViewById(R.id.txtTenLHP);
        }
    }
}

