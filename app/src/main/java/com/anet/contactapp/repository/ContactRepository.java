package com.anet.contactapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.anet.contactapp.ContactDatabase;
import com.anet.contactapp.dao.ContactDao;
import com.anet.contactapp.entities.Contact;
import com.anet.contactapp.entities.User;

import java.util.List;

public class ContactRepository {

    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application){
        ContactDatabase database = ContactDatabase.getInstance(application);
        contactDao = database.contactDao();
      // allContacts = contactDao.getAllContacts();

    }

    public void insert(Contact contact){
        ContactDatabase.service.execute(() -> contactDao.insert(contact));



    }
    public void update(Contact contact){
        ContactDatabase.service.execute(() -> contactDao.update(contact));
    }
    public void delete(Contact contact){
        ContactDatabase.service.execute(() -> contactDao.delete(contact));
    }

    public LiveData<List<Contact>> getAllContacts(){
        return allContacts;
    }


    public void setUserContacts(User user) {
        this.allContacts = contactDao.getAllContacts(user.getId());
    }
}
