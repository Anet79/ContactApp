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

public class EditContactActivity extends AppCompatActivity {


    private FloatingActionButton edit_contact_FBN_done;
    private EditText edit_contact_phone_number;
    private EditText edit_contact_email;
    private EditText edit_contact_first_name;
    private EditText edit_contact_gender;
    private EditText edit_contact_last_name;
    private ImageButton edit_contact_BTN_back;

    private String firstNName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        init();

        buttonClicked();

        Intent intent = getIntent();

        if (intent.hasExtra(Keys.KEY_CONTACT_ID)){
           // contact = new Contact(intent.getStringExtra(Keys.KEY_USER_FIRST_NAME),intent.getStringExtra(Keys.KEY_CONTACT_PHONE));
            setOldContact(intent);

        }





    }

    private void setOldContact(Intent intent) {
        edit_contact_phone_number.setText(intent.getStringExtra(Keys.KEY_CONTACT_PHONE));
        edit_contact_first_name.setText(intent.getStringExtra(Keys.KEY_CONTACT_FIRST_NAME));


    //check if email, last name, gender not empty
        if(!Keys.KEY_CONTACT_EMAIL.isEmpty()){
            edit_contact_email.setText(intent.getStringExtra(Keys.KEY_CONTACT_EMAIL));
        }
        if(!Keys.KEY_CONTACT_LAST_NAME.isEmpty()){
            edit_contact_last_name.setText(intent.getStringExtra(Keys.KEY_CONTACT_LAST_NAME));
        }
        if(!Keys.KEY_CONTACT_GENDER.isEmpty()){
            edit_contact_gender.setText(intent.getStringExtra(Keys.KEY_CONTACT_GENDER));
        }




    }


    public void init(){

        edit_contact_FBN_done= findViewById(R.id.edit_contact_FBN_done);
        edit_contact_phone_number= findViewById(R.id.edit_contact_phone_number);
        edit_contact_email= findViewById(R.id.edit_contact_email);
        edit_contact_first_name= findViewById(R.id.edit_contact_first_name);
        edit_contact_gender= findViewById(R.id.edit_contact_gender);
        edit_contact_last_name= findViewById(R.id.edit_contact_last_name);
        edit_contact_BTN_back= findViewById(R.id.edit_contact_BTN_back);

    }

    public void buttonClicked(){
        edit_contact_FBN_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEditContact();
            }
        });

        edit_contact_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }


    public void saveEditContact(){
        firstNName=edit_contact_first_name.getText().toString();
        lastName= edit_contact_last_name.getText().toString();
        email= edit_contact_email.getText().toString();
        phoneNumber= edit_contact_phone_number.getText().toString();
        gender= edit_contact_gender.getText().toString();

//        if(!firstNName.trim().isEmpty()){
//            contact.setFirstName(firstNName);
//
//        }

            // contact = new Contact(firstNName,phoneNumber);
            Intent data = new Intent();
            data.putExtra(Keys.KEY_CONTACT_FIRST_NAME, firstNName);
            data.putExtra(Keys.KEY_CONTACT_PHONE,phoneNumber);


            checkOtherFields(data);
            String id = getIntent().getStringExtra(Keys.KEY_CONTACT_ID);
           // int id = getIntent().getIntExtra(Keys.KEY_CONTACT_ID,-1);
            if (id != null){
                data.putExtra(Keys.KEY_CONTACT_ID,id);

            }
            setResult(RESULT_OK, data);
            finish();

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
        if (!gender.isEmpty()) {
            data.putExtra(Keys.KEY_CONTACT_GENDER, gender);
            // contact.setLastName(lastName);
        }


    }
    
}
