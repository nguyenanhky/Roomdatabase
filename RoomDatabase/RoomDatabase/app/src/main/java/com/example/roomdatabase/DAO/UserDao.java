package com.example.roomdatabase.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insertuser(User user1);

    @Query("SELECT * FROM User")
    List<User> getAll();

    // check username;
    @Query("SELECT * FROM User WHERE frist_name = :username")
    List<User>Checkuser(String username);

    // update
    @Update
    void updateuser(User user);

    @Delete
    void DeleteUser(User user);

    // xoa tat ca
    @Query("DELETE FROM User")
    void DeleteAll();

    // search

    @Query("SELECT * FROM  USER WHERE frist_name LIKE '%' || :name || '%'")
    List<User>SearchUser(String name);
}
/*
 onConflict () : phải làm gì khi sung dột sảy ra
 + onConflict = OnConflictStrategy.REPLACE :  dể thay thế hàng hiện tại ( trùng id) bằng các hàng mới
 + OnConflictStrategy.IGNORE : để giữ nguyên hàng hiện có khi trung id

 */
