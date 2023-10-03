package com.anet.contactapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anet.contactapp.Keys;
import com.anet.contactapp.R;
import com.anet.contactapp.entities.User;
import com.anet.contactapp.viewModels.UserViewModel;

public class LoginActivity extends AppCompatActivity {


    private UserViewModel userViewModel;

    private EditText login_email;
    private Button login_button;

    private EditText login_password;

    private TextView login_go_to_signup;

    ActivityResultLauncher<Intent> addUserActivityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        init();

        buttonClicked();

        userViewModel =new ViewModelProvider(this).get(UserViewModel.class);

       // userViewModel = new UserViewModel(this.getApplication());

        addUserActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes

                            Intent data = result.getData();
                            if (data != null) {
                                String name = data.getStringExtra(Keys.KEY_USER_FIRST_NAME);
                                String password = data.getStringExtra(Keys.KEY_USER_PASSWORD);
                                String email = data.getStringExtra(Keys.KEY_USER_EMAIL);
                                User user = new User(name, email, password);
                                Log.d("id","id"+user.getId());

//                                Intent intent = new Intent();
//                                intent.putExtra(Keys.KEY_USER_ID,user.getId());
//                                intent.putExtra(Keys.KEY_USER_FIRST_NAME,user.getUserName());
//                                intent.putExtra(Keys.KEY_USER_EMAIL, user.getEmail());
//                                intent.putExtra(Keys.KEY_USER_PASSWORD,user.getPassword());
                                checkIfUserExist(user);
//                                Intent intent = new Intent(LoginActivity.this,ContactViewActivity.class);
//
//                                Intent intent = new Intent(getApplicationContext(), ContactViewActivity.class);
//                                startActivity(intent);


                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "This user  didn't saved", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }

    private void checkIfUserExist(User user) {

       User user1= userViewModel.login(user.getEmail(),user.getPassword());


        if(user1!=null){
            Toast.makeText(LoginActivity.this, "This user  exist", Toast.LENGTH_SHORT).show();
        }
        else{
            userViewModel.insert(user);
            Log.d("id","pttt"+user.getId());
            Toast.makeText(LoginActivity.this, "Signup succeed", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, ContactViewActivity.class);
            intent.putExtra(Keys.KEY_USER_ID,user.getId());
            intent.putExtra(Keys.KEY_USER_FIRST_NAME,user.getUserName());
            intent.putExtra(Keys.KEY_USER_EMAIL, user.getEmail());
            intent.putExtra(Keys.KEY_USER_PASSWORD,user.getPassword());
            startActivity(intent);
            //finish();







        }


    }

    private void buttonClicked() {

        login_go_to_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent= new Intent(LoginActivity.this,SignupActivity.class);
                addUserActivityResultLauncher.launch(intent);

            }
        });



        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String email = login_email.getText().toString();
               String password = login_password.getText().toString();
               User user = userViewModel.login(email,password);

               if(user== null){
                   Toast.makeText(LoginActivity.this, "This user Doesn't exist", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(LoginActivity.this, "Login succeed", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(LoginActivity.this, ContactViewActivity.class);
                   intent.putExtra(Keys.KEY_USER_ID,user.getId());
                   intent.putExtra(Keys.KEY_USER_FIRST_NAME,user.getUserName());
                   intent.putExtra(Keys.KEY_USER_EMAIL, user.getEmail());
                   intent.putExtra(Keys.KEY_USER_PASSWORD,user.getPassword());
                   startActivity(intent);
                   finish();

               }

            }
        });


    }


    public void init(){

        login_go_to_signup= findViewById(R.id.login_go_to_signup);
        login_email= findViewById(R.id.login_email);
        login_password= findViewById(R.id.login_password);
        login_button= findViewById(R.id.login_button);
    }
}
