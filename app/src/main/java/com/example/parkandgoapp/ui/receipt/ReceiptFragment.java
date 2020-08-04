package com.example.parkandgoapp.ui.receipt;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.parkandgoapp.R;
import com.example.parkandgoapp.model.Parking;
import com.example.parkandgoapp.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReceiptFragment extends Fragment {
    ArrayList<Parking> parkingArrayList;
    UserViewModel userViewModel;
    ReceiptAdapter receiptAdapter;
    ListView lstReceipts;
    String name;
    String plateNum;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_receipt, container, false);
        super.onCreate(savedInstanceState);
        parkingArrayList=new ArrayList<>();
        lstReceipts=root.findViewById(R.id.lstReceipts);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllParkings().observe(this, new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                for (Parking parking : parkings) {
                    Log.e("ReceiptFragment", parking.toString());
                    parkingArrayList.add(parking);
                }
            }

        });

        receiptAdapter = new ReceiptAdapter(getActivity(), parkingArrayList);
        lstReceipts.setAdapter(receiptAdapter);
        this.listViewListener();
        return root;
    }

   void displayDetails(){
       userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
       userViewModel.getAllParkings().observe(this, new Observer<List<Parking>>() {
           @Override
           public void onChanged(List<Parking> parkings) {
               for (Parking parking : parkings) {
                 //  if(parking.getLicensePlate().equals(plateNum)) {
                   Log.e("ReceiptFragment", parking.toString());
                   AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                           .setTitle("Receipt")
                           .setMessage("Car Plate:  " + parking.getLicensePlate()
                                   + "\nDate-time of parking:  " + parking.getDatetime()
                                   + "\nduration of parking:  " + parking.getNumberOfHours()
                                   + "\nbuilding code:  " + parking.getBuildingNumber()
                                   + "\nsuit # of host:  " + parking.getAptNumber()
                                   + "\nparking cost:  " + parking.getCharges())
                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Log.i("ok", "fine");
                               }
                           })
                           .create();
                   alertDialog.show();
                  //  }
               }
           }

       });
    }
    private void listViewListener(){
        lstReceipts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("position",Integer.toString((position)));
               // Log.i("position", parkingArrayList.get(position));
              //  plateNum = (String) parent.getItemAtPosition(position);

                //(Item) parent.getAdapter().getItem(position);
                displayDetails();
            }
        });
    }


}