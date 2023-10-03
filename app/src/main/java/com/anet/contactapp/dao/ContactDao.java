package com.anet.contactapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.anet.contactapp.entities.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM contact_table WHERE id = :userId ")
    void deleteAll(int userId);



    // for get all the contact to recyclerView
    @Query("SELECT * FROM contact_table WHERE userId = :userId")
    LiveData<List<Contact>> getAllContacts(String userId);


}
