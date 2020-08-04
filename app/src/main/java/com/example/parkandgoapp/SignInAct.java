package com.example.parkandgoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkandgoapp.model.User;
import com.example.parkandgoapp.viewmodel.UserViewModel;

import java.util.List;

public class SignInAct extends AppCompatActivity implements View.OnClickListener {

    EditText edtusername;
    EditText edtpassword;
    Button signin;
    Switch swtRmbr;
    TextView tv_delete_account;
    TextView tvFrgtPsswd;
    public static final int SIGN_UP_REQUEST_CODE = 1;
   // public static final int SIGN_UP_REQUEST_CODE = 1;
    public  static final String USER_PREF = "com.example.parkandgoapp.userpref";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    String username = "";
    String password = "";
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edtusername = findViewById(R.id.edtusername);
        edtpassword = findViewById(R.id.edtpassword);
        tv_delete_account=findViewById(R.id.tv_delete_account);
        tvFrgtPsswd=findViewById(R.id.tvFrgtPsswd);
        tvFrgtPsswd.setOnClickListener(this);
        tv_delete_account.setOnClickListener(this);
        signin=findViewById(R.id.btnsigniN);
        signin.setOnClickListener(this);
        swtRmbr=findViewById(R.id.swtRmbr);
        userViewModel = new UserViewModel(getApplication());
        userViewModel.getAllUsers().observe(SignInAct.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users){
                    Log.e("SignInActivityOnCreate", user.toString());
                }
            }
        });
        this.getRememberedData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsigniN:
                this.SignIn();
                break;
            case R.id.tv_delete_account:
                this.deleteUserAccount();
                break;
            case R.id.tvFrgtPsswd:
                this.resetPassword();
                break;
        }

    }

    void SignIn(){
        username = edtusername.getText().toString();
        password = edtpassword.getText().toString();

        if (this.authenticateUser(username, password)){
            //if (username=="1" && password=="1"){
                this.openMenuActivity();

            if (swtRmbr.isChecked()){
                this.rememberData();
            }else{
                this.forgetData();
            }
            Toast.makeText(this, "Login successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Incorrect Username/Password ! Try again.",Toast.LENGTH_LONG).show();
        }
    }
    private boolean authenticateUser(String username, String password){
        List<User> allUsers = userViewModel.getAllUsers().getValue();
        for(User user: allUsers){
            Log.e("email",user.getEmail());
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }

    void openMenuActivity(){
        Intent menuIntent = new Intent(SignInAct.this,MainActivity.class);
        startActivity(menuIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_UP_REQUEST_CODE){
            if(requestCode == RESULT_OK){
                User newUser = (User) data.getSerializableExtra("com.example.parkandgoapp.REPLY");
                Log.e("SIGN_IN_ACT",newUser.toString());
                userViewModel.insert(newUser);
            }
        }
    }

    private void rememberData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        sp.edit().putString(USERNAME, edtusername.getText().toString()).commit();
        sp.edit().putString(PASSWORD, edtpassword.getText().toString()).commit();
    }

    private void forgetData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    private void deleteUserAccount(){
        userViewModel.deleteUserByName(edtusername.getText().toString());
        Toast.makeText(this,"Deleted Successfully!",Toast.LENGTH_LONG).show();
    }


    private void getRememberedData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        edtusername.setText(sp.getString(USERNAME, ""));
        edtpassword.setText(sp.getString(PASSWORD, ""));
    }
    void resetPassword(){
        Toast.makeText(this,"Coming soon....",Toast.LENGTH_LONG).show();
    }


}
