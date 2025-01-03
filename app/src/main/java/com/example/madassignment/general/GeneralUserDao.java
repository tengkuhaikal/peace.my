package com.example.madassignment.general;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface GeneralUserDao {

    @Insert
    void insertUser(GeneralUser user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    GeneralUser authenticateUser(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    GeneralUser getUserByUsername(String username);

    @Query("UPDATE users SET password = :newPassword WHERE username = :username")
    void updateUserPassword(String username, String newPassword);

    @Query("SELECT * FROM users WHERE username LIKE :query OR firstName LIKE :query OR lastName LIKE :query")
    List<GeneralUser> searchUsers(String query);
}
