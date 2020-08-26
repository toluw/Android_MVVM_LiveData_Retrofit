package com.up.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.up.mvvm.model.MainModel;
import com.up.mvvm.repository.MainRepo;
import com.up.mvvm.repository.MainResponse;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final LiveData<MainResponse> mainObservable;


    public MainViewModel() {
        mainObservable = MainRepo.getInstance().getMain();
    }





    public LiveData<MainResponse> getMainData() {
        return mainObservable;
    }


}
