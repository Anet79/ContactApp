package com.anet.contactapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anet.contactapp.Keys;
import com.anet.contactapp.R;

public class ContactDetails extends AppCompatActivity {

    private TextView contact_details_TVW_phone_number;
    private ImageButton contact_details_BTN_back;

    private TextView contact_details_TVW_email;
    private TextView contact_details_TVW_first_name;
    private TextView contact_details_TVW_last_name;

    private TextView contact_details_TVW_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_deatails);


        init();

        buttonClicked();

        Intent intent = getIntent();

        if (intent.hasExtra(Keys.KEY_CONTACT_ID)) {
            // contact = new Contact(intent.getStringExtra(Keys.KEY_USER_FIRST_NAME),intent.getStringExtra(Keys.KEY_CONTACT_PHONE));
            setContactDetails(intent);

        }
    }

    private void setContactDetails(Intent intent) {

        contact_details_TVW_phone_number.setText(intent.getStringExtra(Keys.KEY_CONTACT_PHONE));
        contact_details_TVW_first_name.setText(intent.getStringExtra(Keys.KEY_CONTACT_FIRST_NAME));


        //check if email, last name, gender not empty
        if (!Keys.KEY_CONTACT_EMAIL.isEmpty()) {
            contact_details_TVW_email.setText(intent.getStringExtra(Keys.KEY_CONTACT_EMAIL));
        }
        if (!Keys.KEY_CONTACT_LAST_NAME.isEmpty()) {
            contact_details_TVW_last_name.setText(intent.getStringExtra(Keys.KEY_CONTACT_LAST_NAME));
        }
        if (!Keys.KEY_CONTACT_GENDER.isEmpty()) {
            contact_details_TVW_gender.setText(intent.getStringExtra(Keys.KEY_CONTACT_GENDER));
        }


    }

    private void buttonClicked() {
        contact_details_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void init() {
        contact_details_TVW_phone_number = findViewById(R.id.contact_details_TVW_phone_number);
        contact_details_BTN_back = findViewById(R.id.contact_details_BTN_back);
        contact_details_TVW_email = findViewById(R.id.contact_details_TVW_email);
        contact_details_TVW_first_name = findViewById(R.id.contact_details_TVW_first_name);
        contact_details_TVW_gender = findViewById(R.id.contact_details_TVW_gender);

        contact_details_TVW_last_name = findViewById(R.id.contact_details_TVW_last_name);

    }
}
