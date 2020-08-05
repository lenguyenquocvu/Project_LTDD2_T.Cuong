package com.example.quanlydiemsinhvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.Accounts;
import com.example.quanlydiemsinhvien.data_models.SinhVien;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter {
    TextView tvIDSinhVien, tvPassword;
    ArrayList<SinhVien> dataSV;
    Context context;
    int resource;
    public SinhVienAdapter(Context context, int resource, ArrayList<SinhVien> dataSV) {
        super(context, resource, dataSV);
        this.context = context;
        this.resource = resource;
        this.dataSV = dataSV;
    }
    @Override
    public int getCount() {
        return dataSV.size();
    }

    @Override
    public View getView(int position, View recycleView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(recycleView==null){
            recycleView = LayoutInflater.from(context).inflate(R.layout.rclist_item_sinhvien,parent,false);
            viewHolder = new ViewHolder();

            recycleView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) recycleView.getTag();
        }
        //Set text:
        SinhVien sinhVien = dataSV.get(position);
        //  tvIDSinhVien.setText(accounts.getId());
        return recycleView;
    }
    public class ViewHolder{
        private TextView tvMaSV;
        private TextView tvHoTenSV;
        private TextView tvMaHP;

    }

}
