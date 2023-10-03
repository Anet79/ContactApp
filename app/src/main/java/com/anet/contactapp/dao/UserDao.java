package com.anet.contactapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.anet.contactapp.entities.Contact;
import com.anet.contactapp.entities.User;
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);



    @Query("SELECT * from user_table where email =:email AND password=:password")
    User login(String email,String password);

}
