package com.anet.contactapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.anet.contactapp.ContactDatabase;
import com.anet.contactapp.dao.ContactDao;
import com.anet.contactapp.dao.UserDao;
import com.anet.contactapp.entities.Contact;
import com.anet.contactapp.entities.User;

import java.util.List;

public class UserRepository {

    private UserDao userDao;

    private LiveData<User> userLiveData;

    private User user;


    public UserRepository(Application application){
        ContactDatabase database = ContactDatabase.getInstance(application);
        userDao = database.userDao();
        //userLiveData= new






    }

    public void insert(User user){
        ContactDatabase.service.execute(() -> userDao.insert(user));

    }

    public User login(String email,String password){
       // userLiveData=userDao.login();
       // userDao.login(email,password);

         ContactDatabase.service.execute(() -> {
            user = userDao.login(email, password);

    });
         return user;



    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
