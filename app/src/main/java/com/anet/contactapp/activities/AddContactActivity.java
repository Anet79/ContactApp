package com.anet.contactapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anet.contactapp.Keys;
import com.anet.contactapp.R;
import com.anet.contactapp.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddContactActivity extends AppCompatActivity {

    private FloatingActionButton add_contact_FBN_done;
    private EditText add_contact_phone_number;
    private EditText add_contact_email;
    private EditText add_contact_first_name;
    private EditText add_contact_last_name;
    private ImageButton add_contact_BTN_back;

    private String firstNName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        init();

        buttonClicked();





    }


    public void init(){

        add_contact_FBN_done= findViewById(R.id.add_contact_FBN_done);
        add_contact_phone_number= findViewById(R.id.add_contact_phone_number);
        add_contact_email= findViewById(R.id.add_contact_email);
        add_contact_first_name= findViewById(R.id.add_contact_first_name);
        add_contact_last_name= findViewById(R.id.add_contact_last_name);
        add_contact_BTN_back= findViewById(R.id.add_contact_BTN_back);

    }

    public void buttonClicked(){
        add_contact_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_contact_FBN_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });



    }


    public void saveContact(){
         firstNName=add_contact_first_name.getText().toString();
         lastName= add_contact_last_name.getText().toString();
         email= add_contact_email.getText().toString();
         phoneNumber= add_contact_phone_number.getText().toString();


        if (firstNName.trim().isEmpty() || phoneNumber.trim().isEmpty()) {

            Toast.makeText(this,
                    "One of your fields is not complete", Toast.LENGTH_SHORT).show();

        }
        else {
            // contact = new Contact(firstNName,phoneNumber);
            Intent data = new Intent();
            data.putExtra(Keys.KEY_CONTACT_FIRST_NAME, firstNName);
            data.putExtra(Keys.KEY_CONTACT_PHONE,phoneNumber);
            checkOtherFields(data);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    public void checkOtherFields(Intent data){

        if (!email.isEmpty()) {
            data.putExtra(Keys.KEY_CONTACT_EMAIL, email);
            //contact.setEmail(email);


        }
        if (!lastName.isEmpty()) {
            data.putExtra(Keys.KEY_CONTACT_LAST_NAME, lastName);
           // contact.setLastName(lastName);
        }


    }
}
