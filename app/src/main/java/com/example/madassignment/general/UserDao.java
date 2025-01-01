package com.example.madassignment.general;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    User authenticateUser(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);

    @Query("UPDATE users SET password = :newPassword WHERE username = :username")
    void updateUserPassword(String username, String newPassword);

    @Query("SELECT * FROM users WHERE username LIKE :query OR firstName LIKE :query OR lastName LIKE :query")
    List<User> searchUsers(String query);
}
