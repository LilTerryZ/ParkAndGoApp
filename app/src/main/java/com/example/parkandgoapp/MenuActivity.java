


package com.example.parkandgoapp;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.PersistableBundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.annotation.Nullable;
        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.Observer;

        import com.example.parkandgoapp.R;
        import com.example.parkandgoapp.SignInAct;
        import com.example.parkandgoapp.SignUpAct;
        import com.example.parkandgoapp.model.User;
        import com.example.parkandgoapp.viewmodel.UserViewModel;

        import java.util.List;

        import static android.app.Activity.RESULT_OK;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener   {

    Button signin;
    Button signup;

    public static final int SIGN_UP_REQUEST_CODE = 1;

    UserViewModel userViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        signin = findViewById(R.id.btnSignIn);
        signin.setOnClickListener(this);

        signup = findViewById(R.id.btnSignUp);
        signup.setOnClickListener(this);

        userViewModel = new UserViewModel(this.getApplication());

        userViewModel.getAllUsers().observe(MenuActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //task when the data changes
                for (User user : users) {
                    Log.e("MenuActivity", user.toString());
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn:
                this.SignIn();
                break;
            case R.id.btnSignUp:
                this.SignUp();
                break;
        }
    }

    void SignIn(){
        Intent newpage2 = new Intent(this, SignInAct.class);
        startActivity(newpage2);

    }
    void SignUp(){
        Intent newpage1 = new Intent(this,SignUpAct.class);
        startActivityForResult(newpage1,SIGN_UP_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_UP_REQUEST_CODE){
            if(resultCode == RESULT_OK){

                User newUser = (User) data.getSerializableExtra("com.example.parkandgoapp.ui.home.REPLY");
                Log.e("HOME_FRAGMENT_ACTIVITY", newUser.toString());

                //insert new user account detail into database
                userViewModel.insert(newUser);
            }
        }
    }
}