package com.example.roomdatabase.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabase.DAO.UserDao;
import com.example.roomdatabase.entities.User;

@Database(entities = {User.class},version = 1)

public abstract class UserDatabase extends RoomDatabase {
    private static final String Databasename ="userone.db";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class,Databasename)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public  abstract UserDao userDao();
}
