package com.example.parkandgoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.parkandgoapp.db.UserRepository;
import com.example.parkandgoapp.model.Parking;
import com.example.parkandgoapp.model.User;

import java.util.List;

/**
 * ParkAndGoApp created by test
 * Student ID: 991541369
 * on 2019-11-14
 */
public class UserViewModel extends AndroidViewModel {
    private LiveData<List<User>> allUsers;
    private LiveData<List<Parking>> allParkings;

    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
        allParkings = userRepository.getAllParkings();
    }

    public void insert(User user){
        userRepository.insert(user);
    }
    public void insertP(Parking parking){
        userRepository.insertP(parking);
    }

    //public void loadUserById(int id){userRepository.loadUserById(id);}
    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
    public LiveData<List<Parking>> getAllParkings(){
        return allParkings;
    }

    //public void update(User user){userRepository.update(user);}

    public void deleteUserByName(String name){
        userRepository.deleteUserByName(name);
    }
}
