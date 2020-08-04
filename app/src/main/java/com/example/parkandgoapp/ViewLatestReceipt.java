package com.example.parkandgoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.parkandgoapp.model.Parking;
import com.example.parkandgoapp.ui.receipt.ReceiptAdapter;
import com.example.parkandgoapp.viewmodel.UserViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ViewLatestReceipt extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Parking> parkingArrayList;
    UserViewModel userViewModel;
    TextView tvReceiptBldngCde;
    TextView tvReceiptNoHours;
    TextView tvReceiptCarNum;
    TextView tvReceiptAptNo;
    TextView tvCharges;
    Button btnReturn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latest_receipt);
        tvReceiptBldngCde=findViewById(R.id.tvReceiptBldngCde);
        tvReceiptNoHours=findViewById(R.id.tvReceiptNoHours);
        tvReceiptCarNum=findViewById(R.id.tvReceiptNoHours);
        tvReceiptAptNo=findViewById(R.id.tvReceiptAptNo);
        tvCharges=findViewById(R.id.tvCharges);
        btnReturn=findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        parkingArrayList=new ArrayList<>();
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllParkings().observe(this, new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                for (Parking parking : parkings) {
                    Log.e("ReceiptFragment", parking.toString());
                    parkingArrayList.add(parking);
                    tvReceiptBldngCde.setText(Integer.toString(parking.getBuildingNumber()));
                    tvReceiptNoHours.setText(Integer.toString(parking.getNumberOfHours()));
                    tvReceiptCarNum.setText(parking.getLicensePlate());
                    tvReceiptAptNo.setText(Integer.toString(parking.getAptNumber()));
                    tvCharges.setText(Integer.toString(parking.getCharges()));
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
