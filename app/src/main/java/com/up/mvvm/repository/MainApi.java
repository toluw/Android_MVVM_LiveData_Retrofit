package com.up.mvvm.repository;

import com.up.mvvm.model.MainModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainApi {

    @GET("bookv.php")
    Call<List<MainModel>>getData();
}
