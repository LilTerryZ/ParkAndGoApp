package com.example.parkandgoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.parkandgoapp.R;
import com.example.parkandgoapp.model.User;
import com.example.parkandgoapp.viewmodel.UserViewModel;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.List;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends Fragment implements View.OnClickListener {

    Button addparking;
    Button viewParking;

    private static final int ADD_PARKING_ACT = 1;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        addparking = root.findViewById(R.id.btnAddParking);
        addparking.setOnClickListener(this);
        viewParking=root.findViewById(R.id.btnViewParkingR);
        viewParking.setOnClickListener(this);
    return root;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddParking:
                this.addparkingact();
                break;
            case R.id.btnViewParkingR:
                this.viewParkingR();
                break;

        }
    }

    private void viewParkingR() {
        Intent viewParking = new Intent(getActivity(), ViewLatestReceipt.class);
        startActivity(viewParking);
    }

    void addparkingact(){
        Intent addparking = new Intent(getActivity(), AddParkingActivity.class);
        startActivityForResult(addparking,ADD_PARKING_ACT);
    }
}
