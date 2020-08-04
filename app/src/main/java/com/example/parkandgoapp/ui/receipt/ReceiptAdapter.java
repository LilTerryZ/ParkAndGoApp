package com.example.parkandgoapp.ui.receipt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.parkandgoapp.R;
import com.example.parkandgoapp.model.Parking;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class ReceiptAdapter extends ArrayAdapter {
    Context context;

    public ReceiptAdapter(@NonNull Context context, ArrayList<Parking> parkings) {
        super(context, 0,parkings);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Parking parking=(Parking) getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.receipt_item,parent,false);
        }
        TextView tvCP=convertView.findViewById(R.id.tvCP);
        TextView tvRNum=convertView.findViewById(R.id.tvRNum);
        tvCP.setText(parking.getLicensePlate());
        tvRNum.setText("Car Plate Number: ");

       return convertView;
        }
    }
