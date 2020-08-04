package com.example.parkandgoapp.ui.consumerSupport;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.parkandgoapp.R;

/** Consumer Support created by Tanishq.K */

public class SupportFragment extends Fragment implements View.OnClickListener{

    Button btnCall;
    Button btnEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_support, container, false);

        btnCall = root.findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);

        btnEmail = root.findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCall:
                this.makeCall();
                break;

            case R.id.btnEmail:
                this.sendEmail();
                break;
        }
    }

    private void makeCall(){

        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel:6478705700"));

        if(ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Log.e("SupportFragment","Call Permission Not Granted, Please Try Again");
            return;
        }

        startActivity(callIntent);
    }

    private void sendEmail(){

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"khandhar@sheridancollege.ca"});

        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Parking Support Query");

        emailIntent.putExtra(Intent.EXTRA_TEXT,"This email is in regards to inquire and start a query ticket for ParkAndGoApp Customer Support");

        emailIntent.setType("message/rfc822");

        startActivity(Intent.createChooser(emailIntent,"Please select email client"));
    }
}