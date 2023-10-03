package com.anet.contactapp.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anet.contactapp.entities.Contact;
import com.anet.contactapp.entities.User;
import com.anet.contactapp.repository.ContactRepository;
import com.anet.contactapp.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {


    private UserRepository userRepository;
   private User user;
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);

    }


    public void insert(User user) {
        userRepository.insert(user);
    }

    public User login(String email,String password){
      return userRepository.login(email,password);


    }

    public User getUser() {
        return user;
    }
}
