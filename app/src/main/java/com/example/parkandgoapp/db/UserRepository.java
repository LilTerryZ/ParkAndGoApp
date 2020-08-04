package com.example.parkandgoapp.db;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.style.UpdateAppearance;

import androidx.lifecycle.LiveData;

import com.example.parkandgoapp.model.Parking;
import com.example.parkandgoapp.model.User;

import java.util.List;

/**
 * ParkAndGoApp created by nisarg
 * Student ID: 991541369
 * on 2019-11-14
 */
public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Parking>> allparkings;


    public UserRepository(Application application) {
        UserDB db = UserDB.getINSTANCE(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
        allparkings = userDao.getAllParkings();
    }


    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    public LiveData<List<com.example.parkandgoapp.model.Parking>> getAllParkings() {return allparkings; }

    public void insert(User user){
        new insertAsyncTask(userDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao asyncTaskDao;

        insertAsyncTask(UserDao userDao) {
            asyncTaskDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            asyncTaskDao.insert(users[0]);
            return null;
        }
    }

    public void insertP(Parking parking){
        new insertParkingAsyncTask(userDao).execute(parking);
    }

    private static class insertParkingAsyncTask extends AsyncTask<Parking, Void, Void> {

        private UserDao asyncTaskDao;

        insertParkingAsyncTask(UserDao userDao) {
            asyncTaskDao = userDao;
        }

        @Override
        protected Void doInBackground(Parking...parkings) {
            asyncTaskDao.insertP(parkings[0]);
            return null;
        }
    }

//    public void loadUserById(Integer id){new loadUserByIdAsyncTask(userDao).execute(id);}
//    private static class loadUserByIdAsyncTask extends AsyncTask<Integer, Void, Void>{
//
//        private UserDao asyncTaskDao;
//
//        loadUserByIdAsyncTask(UserDao userDao){
//            asyncTaskDao = userDao;
//        }
//
//
//        @Override
//        protected Void doInBackground(Integer... integers) {
//            asyncTaskDao.loadUserById(integers[0]);
//            return null;
//        }
//    }


    public void update(User user){
        new updateUserAsyncTask(userDao).execute(user);
    }

    private static class updateUserAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao asyncTaskDao;

        updateUserAsyncTask(UserDao userDao){
            asyncTaskDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            asyncTaskDao.update(users[0]);
            return null;
        }
    }

    public void deleteUserByName(String name){
        new deleteUserByNameAsyncTask(userDao).execute(name);
    }

    private static class deleteUserByNameAsyncTask extends AsyncTask<String, Void, Void>{

        private UserDao asyncTaskDao;

        deleteUserByNameAsyncTask(UserDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            asyncTaskDao.deleteUserByName(strings[0]);
            return null;
        }
    }


}
