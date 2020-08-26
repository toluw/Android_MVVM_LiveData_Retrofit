package com.up.mvvm.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.up.mvvm.model.MainModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainRepo {

    private static MainRepo mainRepo;

    public static MainRepo getInstance(){
        if (mainRepo == null){
            mainRepo = new MainRepo();
        }
        return mainRepo;
    }

    public LiveData<MainResponse>getMain(){

        final MutableLiveData<MainResponse> data = new MutableLiveData<>();

     Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mainlety/project/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

     MainApi mainApi = retrofit.create(MainApi.class);

        Call<List<MainModel>> call = mainApi.getData();

        call.enqueue(new Callback<List<MainModel>>() {
            @Override
            public void onResponse(Call<List<MainModel>> call, Response<List<MainModel>> response) {
                if(response.isSuccessful()){
                    List<MainModel> res  = response.body();
                    data.setValue(new MainResponse(res));

                }else{
                    String mes = "An error occured";
                    data.setValue(new MainResponse(mes));


                }
            }

            @Override
            public void onFailure(Call<List<MainModel>> call, Throwable t) {
                String mes = t.getMessage();
                data.setValue(new MainResponse(mes));
            }
        });

            return  data;

    }


}
