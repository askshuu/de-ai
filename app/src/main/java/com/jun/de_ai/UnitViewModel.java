package com.jun.de_ai;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class UnitViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers(){
        if(users==null){
            users=new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers(){

    }
}
