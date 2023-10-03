package com.anet.contactapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.anet.contactapp.dao.ContactDao;
import com.anet.contactapp.dao.UserDao;
import com.anet.contactapp.entities.Contact;
import com.anet.contactapp.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Contact.class}, version = 4,exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    //Singleton
    private static  ContactDatabase instance;
    public abstract ContactDao contactDao();
    public abstract UserDao userDao();

    public static ExecutorService service = Executors.newFixedThreadPool(4);

    public static  synchronized ContactDatabase getInstance(Context context){
        if (instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(),ContactDatabase.class,"all_database")
                    .fallbackToDestructiveMigration().build();

        }
        return instance;
    }
//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//        }
//    };
}
