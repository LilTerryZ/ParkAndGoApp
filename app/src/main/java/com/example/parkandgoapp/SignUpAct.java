package com.example.parkandgoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.parkandgoapp.model.User;
import com.example.parkandgoapp.viewmodel.UserViewModel;

import java.util.List;

public class SignUpAct extends AppCompatActivity implements View.OnClickListener {


    String username;
    String password;
    String phonenumber;
    String email;
    String numberplate;
    String cardnumber;
    String Cvv;
    UserViewModel userViewModel;
    EditText edtusername1;
    EditText edtpassword1;
    EditText edtemail1;
    EditText edtphone1;
    EditText edtnumberplate1;
    EditText edtcardnumber1;
    Button btnlogin1;
    EditText edtCvv;
    public static final String EXTRA_REPLY = "com.example.parkandgoapp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.referWidgets();

        userViewModel = new UserViewModel(getApplication());
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                for (User user : users){
                    Log.e("Signup", user.toString());
                }
            }
        });

    }

    private void referWidgets(){
        edtusername1= findViewById(R.id.edtusername);
        edtpassword1 = findViewById(R.id.edtpassword);
        edtemail1 = findViewById(R.id.edtemail);
        edtphone1 = findViewById(R.id.edtphone);
        edtcardnumber1 = findViewById(R.id.edtCardNumber);
        edtnumberplate1 = findViewById(R.id.edtnumberplate);
        edtCvv=findViewById(R.id.edtCVV);
        btnlogin1 = findViewById(R.id.btnlogin);
        btnlogin1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnlogin:
               if(this.validateData()){
                   Log.e("aaa","aaa");
                   createUserAndReply();
                   openMenuActivity();
               }
               break;

        }

    }


    private void createUserAndReply(){
        username = edtusername1.getText().toString();
        password = edtpassword1.getText().toString();
        phonenumber = edtphone1.getText().toString();
        numberplate = edtnumberplate1.getText().toString();
        email = edtemail1.getText().toString();
        cardnumber = edtcardnumber1.getText().toString();
        Cvv=edtCvv.getText().toString();

        User newUser = new User(username,password,phonenumber,numberplate,email,cardnumber);

        userViewModel = new UserViewModel(getApplication());
        userViewModel.getAllUsers().observe(SignUpAct.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users){
                    Log.e("SignUp Successfully", user.toString());
                }
            }
        });
        userViewModel.insert(newUser);

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY,newUser);
        setResult(RESULT_OK,replyIntent);

        finish();


    }

    void openMenuActivity(){
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
    }

    private boolean validateData(){
        boolean allvalidations = true;

        if(edtusername1.getText().toString().isEmpty()){
            edtusername1.setError("You must enter username");
            allvalidations = false;
        }
        if(edtpassword1.getText().toString().isEmpty() || edtpassword1.getText().toString().length()<8){
            edtpassword1.setError("The minimum length of password is 8");
            allvalidations = false;
        }
        if(edtphone1.getText().toString().isEmpty() || edtphone1.getText().toString().length()<10 ||edtphone1.getText().toString().length()>10)  {
            edtphone1.setError("Phone number format is wrong");
            allvalidations = false;
        }
        if(edtnumberplate1.getText().toString().isEmpty()){
            edtnumberplate1.setError("Enter the number plate");
            allvalidations = false;
        }
        if(edtemail1.getText().toString().isEmpty()){
            edtemail1.setError("Email cannot be empty");
            allvalidations = false;
        }
        if(edtCvv.getText().toString().isEmpty()||edtCvv.getText().toString().length()!=3 ){
            edtCvv.setError("Cvv is incorrect");
            allvalidations = false;
        }else if (!Utils.isValidEmail(edtemail1.getText().toString())){
            edtemail1.setError("Please provide valid email address");
            allvalidations = false;
        }


        return allvalidations;
    }
}
