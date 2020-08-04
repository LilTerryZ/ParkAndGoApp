package com.example.parkandgoapp.ui.updateProfile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkandgoapp.R;
import com.example.parkandgoapp.model.User;
import com.example.parkandgoapp.viewmodel.UserViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class UpdateProfileFragment extends Fragment implements View.OnClickListener {

    Button btnUpdate;
    EditText edtSname;
    EditText edtpassword;
    EditText edtemail;
    EditText edtphone;
    EditText edtnumberplate;
    EditText edtCardNumber;
    EditText edtDate;
    EditText edtCVV;
    String username;
    String password;
    String phonenumber;
    String email;
    String numberplate;
    String cardnumber;
    EditText edtOriginalUserName;
    UserViewModel userViewModel;
//    List<User> allUsers = userViewModel.getAllUsers().getValue();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_update_profile, container, false);
        btnUpdate=root.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        edtSname=root.findViewById(R.id.edtSname);
        edtpassword=root.findViewById(R.id.edtpassword);
        edtemail=root.findViewById(R.id.edtemail);
        edtphone=root.findViewById(R.id.edtphone);
        edtnumberplate=root.findViewById(R.id.edtnumberplate);
        edtCardNumber=root.findViewById(R.id.edtCardNumber);
        edtDate=root.findViewById(R.id.edtDate);
        edtCVV=root.findViewById(R.id.edtCVV);
        edtOriginalUserName=root.findViewById(R.id.edtOriginalUsername);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                for (User user : users){
                    Log.e("Updated", user.toString());
                }
            }
        });
        return root;
    }

    @Override
    public void onClick(View view) {

        username= edtSname.getText().toString();
        password=edtpassword.getText().toString();
        email= edtemail.getText().toString();
        phonenumber=edtphone.getText().toString();
        numberplate= edtnumberplate.getText().toString();
        cardnumber=edtCardNumber.getText().toString();
        userViewModel.deleteUserByName(edtOriginalUserName.getText().toString());
        User newUser = new User(username,password,phonenumber,numberplate,email,cardnumber);
        userViewModel.insert(newUser);
        Log.e("Settings2", newUser.toString());
        Toast.makeText(getActivity(),"Updated",Toast.LENGTH_LONG).show();
    }
}