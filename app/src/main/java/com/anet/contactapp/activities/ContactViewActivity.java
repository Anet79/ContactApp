package com.anet.contactapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anet.contactapp.Keys;
import com.anet.contactapp.R;
import com.anet.contactapp.adapter.ContactAdapter;
import com.anet.contactapp.callbacks.OnContactClicked;
import com.anet.contactapp.entities.Contact;
import com.anet.contactapp.entities.User;
import com.anet.contactapp.viewModels.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ContactViewActivity extends AppCompatActivity {

    private  RecyclerView recyclerView;
    private ContactViewModel contactViewModel;

    private FloatingActionButton add_contact_FTB_add_contact;

    ActivityResultLauncher<Intent> addContactActivityResultLauncher;
    ActivityResultLauncher<Intent> editContactActivityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);
        recyclerView = findViewById(R.id.recycler_view);



        add_contact_FTB_add_contact = findViewById(R.id.add_contact_FTB_add_contact);
        add_contact_FTB_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactViewActivity.this, AddContactActivity.class);
                addContactActivityResultLauncher.launch(intent);
            }
        });


         addContactActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();

                            String name = data.getStringExtra(Keys.KEY_CONTACT_FIRST_NAME);
                            String phoneNumber = data.getStringExtra(Keys.KEY_CONTACT_PHONE);
                            Contact contact = new Contact(name,phoneNumber);
                            if(!Keys.KEY_CONTACT_EMAIL.isEmpty()){
                                contact.setEmail(data.getStringExtra(Keys.KEY_CONTACT_EMAIL));
                            }
                            if(!Keys.KEY_CONTACT_LAST_NAME.isEmpty()){
                                contact.setLastName(data.getStringExtra(Keys.KEY_CONTACT_LAST_NAME));
                            }
                            contactViewModel.insert(contact);

                        }
                    }
                });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ContactAdapter contactAdapter = new ContactAdapter();
        recyclerView.setAdapter(contactAdapter);

        contactViewModel =new ViewModelProvider(this).get(ContactViewModel.class);
        contactViewModel.setSpecificUser(getUser());
        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {

                    //update RecyclerView
                    contactAdapter.setContacts(contacts);

                    Toast.makeText(ContactViewActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                }

        });

            // class that help to do a delete (with swipe)
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //TODO- do a warning dialog that ask if the user are sure to delete this contact

                contactViewModel.delete(contactAdapter.getContactAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ContactViewActivity.this, "Contact Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        contactAdapter.setContactsClickListener(new OnContactClicked() {
            @Override
            public void clicked(Contact contact) {
                Intent intent = new Intent(ContactViewActivity.this, ContactDetails.class);

                initContactDetails(contact,intent);
                startActivity(intent);
            }

            @Override
            public void editClicked(Contact contact) {

                Intent intent = new Intent(ContactViewActivity.this, EditContactActivity.class);
                initContactDetails(contact,intent);
                editContactActivityResultLauncher.launch(intent);






            }
        });

        editContactActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();

                           // int id = data.getIntExtra(Keys.KEY_CONTACT_ID,-1);
                            String id = data.getStringExtra(Keys.KEY_CONTACT_ID);

                            if(id == null){
                                Toast.makeText(ContactViewActivity.this,"Contact can't be updated",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            String name = data.getStringExtra(Keys.KEY_CONTACT_FIRST_NAME);
                            String phoneNumber = data.getStringExtra(Keys.KEY_CONTACT_PHONE);
                            Contact contact = new Contact(name,phoneNumber);
                            contact.setId(id);
                            if(!Keys.KEY_CONTACT_EMAIL.isEmpty()){
                                contact.setEmail(data.getStringExtra(Keys.KEY_CONTACT_EMAIL));
                            }
                            if(!Keys.KEY_CONTACT_LAST_NAME.isEmpty()){
                                contact.setLastName(data.getStringExtra(Keys.KEY_CONTACT_LAST_NAME));
                            }

                            if(!Keys.KEY_CONTACT_GENDER.isEmpty()){
                                contact.setGender(data.getStringExtra(Keys.KEY_CONTACT_GENDER));
                            }
                            contactViewModel.update(contact);
                            Toast.makeText(ContactViewActivity.this,"Contact updated",Toast.LENGTH_SHORT).show();


                        }
                    }
                });

    }

    private void initContactDetails(Contact contact,Intent intent) {

        intent.putExtra(Keys.KEY_CONTACT_ID,contact.getId());
        intent.putExtra(Keys.KEY_CONTACT_FIRST_NAME,contact.getFirstName());
        intent.putExtra(Keys.KEY_CONTACT_LAST_NAME,contact.getLastName());
        intent.putExtra(Keys.KEY_CONTACT_EMAIL,contact.getEmail());
        intent.putExtra(Keys.KEY_CONTACT_PHONE,contact.getPhoneNumber());
        intent.putExtra(Keys.KEY_CONTACT_GENDER,contact.getGender());
    }

    private User getUser() {

        Intent data = getIntent();
        if (data != null) {
            String name = data.getStringExtra(Keys.KEY_USER_FIRST_NAME);
            String password = data.getStringExtra(Keys.KEY_USER_PASSWORD);
            String email = data.getStringExtra(Keys.KEY_USER_EMAIL);
            String id= data.getStringExtra(Keys.KEY_USER_ID);
            User user = new User(name, email, password);
            user.setId(id);
            return user;


        }
        return null;
    }


}
