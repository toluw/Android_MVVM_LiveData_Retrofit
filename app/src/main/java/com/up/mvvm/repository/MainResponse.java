package com.up.mvvm.repository;

import com.up.mvvm.model.MainModel;

import java.util.List;

public class MainResponse {
    private List<MainModel>response;
    private  String error;

    public MainResponse(String error) {
        this.error = error;
        this.response = null;
    }

    public MainResponse(List<MainModel> response) {
        this.response = response;
        this.error = error;
    }

    public List<MainModel> getResponse() {
        return response;
    }

    public void setResponse(List<MainModel> response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
