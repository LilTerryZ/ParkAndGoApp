package com.example.parkandgoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.parkandgoapp.model.Parking;
import com.example.parkandgoapp.ui.receipt.ReceiptAdapter;
import com.example.parkandgoapp.viewmodel.UserViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AddParkingActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_PrkngRcpt;
    UserViewModel userViewModel;
    EditText edt_BldngCde;
    EditText edt_NoHours;
    EditText edt_CarNum;
    EditText edt_AptNo;

    String edtbldngCde;
    String edtNohrs;
    String edtCarnum1;
    String AptNo;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String date;
    int charge;
    ReceiptAdapter receiptAdapter;
    ArrayList<Parking> parkingArraylist= new ArrayList<>();



    public static final String EXTRA_REPLY = "com.example.parkandgoapp";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);
        this.referWidgets();
        userViewModel = new UserViewModel(getApplication());
        userViewModel.getAllParkings().observe(AddParkingActivity.this, new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                //task when the data changes
                for (Parking parking : parkings){
                    Log.e("OnCreateAddP", parking.toString());
                    parkingArraylist.add(parking);

                }
            }
        });

    }


    private void referWidgets(){
        btn_PrkngRcpt = findViewById(R.id.btnPrkngRcpt);
        btn_PrkngRcpt.setOnClickListener(this);

        edt_BldngCde = findViewById(R.id.edtBldngCde);
        edt_NoHours = findViewById(R.id.edtNoHours);
        edt_CarNum = findViewById(R.id.edtCarNum);
        edt_AptNo = findViewById(R.id.edtAptNo);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPrkngRcpt:
                CreateParkingReply();
                //Log.d("",userViewModel.loadUserById(1))
                openMenuActivity();
        }
    }
    private void CreateParkingReply(){
        edtbldngCde= edt_BldngCde.getText().toString();
        edtNohrs = edt_NoHours.getText().toString();
        edtCarnum1 =edt_CarNum.getText().toString();
        AptNo = edt_AptNo.getText().toString();
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        date = simpledateformat.format(calander.getTime());
        int bCode, aptNo, hours;
        bCode = Integer.parseInt(edtbldngCde);
        aptNo = Integer.parseInt(AptNo);
        hours = Integer.parseInt(edtNohrs);
        int Charges = CalculateCharges();

        Parking newParking = new Parking(bCode,aptNo,edtCarnum1,hours,date,Charges);
        Log.e("AddParkingActivity",newParking.toString());

        userViewModel.insertP(newParking);

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY,newParking);
        setResult(RESULT_OK,replyIntent);
        finish();
    }
    void openMenuActivity(){
        Intent menuIntent = new Intent(this, MainActivity.class);
        startActivity(menuIntent);
    }

    public int CalculateCharges(){
        int bCode, aptNo, hours;
//        bCode = Integer.parseInt(edtbldngCde);
//        aptNo = Integer.parseInt(AptNo);
        hours = Integer.parseInt(edtNohrs);
        if(hours<=1){
            charge = 4;
        }
        if(hours>1 && hours<3){
            charge = 8;
        }
        if(hours>=3 && hours<10){
            charge = 12;
        }
        if(hours>=10){
            charge = 20;
        }
        return charge;


    }
}
