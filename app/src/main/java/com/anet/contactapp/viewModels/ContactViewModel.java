package com.anet.contactapp.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anet.contactapp.entities.User;
import com.anet.contactapp.repository.ContactRepository;
import com.anet.contactapp.entities.Contact;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository contactRepository;
    private LiveData<List<Contact>> allContacts;

    public User user;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);

    }

    public void insert(Contact contact) {
        contact.setUserId(user.getId());
        contactRepository.insert(contact);
    }

    public void update(Contact contact) {
        contact.setUserId(user.getId());
        contactRepository.update(contact);
    }

    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }
    public LiveData<List<Contact>> getAllContacts(){
        return allContacts;
    }


    public void setSpecificUser(User user) {
        this.user = user;
        contactRepository.setUserContacts(user);
        allContacts = contactRepository.getAllContacts();

    }
}
