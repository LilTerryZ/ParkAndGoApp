package com.example.parkandgoapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.parkandgoapp.model.Parking;
import com.example.parkandgoapp.model.User;

import java.util.List;

/**
 * ParkAndGoApp created by nisarg
 * Student ID: 991541369
 * on 2019-11-14
 */


@Dao
public interface UserDao {

    @Insert
    void insert(User user);


    @Insert
    void insertP(Parking parking);

    @Update
    void update(User user);

//    @Delete
//    void deleteUserByEmail(User user);

    @Query("SELECT * FROM parkandgo_table ORDER BY email ASC")
    LiveData<List<User>> getAllUsers();

    @Query("DELETE FROM parkandgo_table WHERE username = :name")
    void deleteUserByName(String name);

//    @Query("SELECT * FROM parking_table WHERE id=:id")
//    String loadUserById(int id);

    @Query("SELECT * FROM parking_table ORDER BY licenseplate ASC")
    LiveData<List<Parking>> getAllParkings();

}
