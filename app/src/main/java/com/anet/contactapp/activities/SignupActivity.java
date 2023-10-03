package com.anet.contactapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anet.contactapp.Keys;
import com.anet.contactapp.R;
import com.anet.contactapp.entities.User;
import com.anet.contactapp.viewModels.ContactViewModel;
import com.anet.contactapp.viewModels.UserViewModel;

public class SignupActivity extends AppCompatActivity {

    private TextView signup_go_to_login;
    private EditText signup_password;
    private EditText signup_email;
    private EditText signup_last_name;
    private EditText signup_first_name;
    private Button signup_button;

    private String firstNName;
    private String lastName;
    private String email;
    private String password;

    private UserViewModel userViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();

        buttonClicked();

        userViewModel =new ViewModelProvider(this).get(UserViewModel.class);

    }

    private void buttonClicked() {

        signup_go_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));

            }

        });




        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                firstNName=signup_first_name.getText().toString();
                lastName= signup_last_name.getText().toString();
                email= signup_email.getText().toString();
                password = signup_password.getText().toString();
                if(firstNName.isEmpty()||lastName.isEmpty()||email.isEmpty()||password.isEmpty()){
                    setResult(RESULT_CANCELED,intent);
                }
                else{
                    intent.putExtra(Keys.KEY_USER_FIRST_NAME,firstNName);
                    intent.putExtra(Keys.KEY_USER_LAST_NAME,lastName);
                    intent.putExtra(Keys.KEY_USER_EMAIL,email);

                    intent.putExtra(Keys.KEY_USER_PASSWORD,password);

                    setResult(RESULT_OK,intent);
                    finish();
                }


            }

        });



    }

    private void init(){

        signup_go_to_login= findViewById(R.id.signup_go_to_login);
        signup_password= findViewById(R.id.signup_password);
        signup_email= findViewById(R.id.signup_email);
        signup_last_name= findViewById(R.id.signup_last_name);
        signup_first_name= findViewById(R.id.signup_first_name);
        signup_button= findViewById(R.id.signup_button);

    }

}
