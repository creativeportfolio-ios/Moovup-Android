package com.example.anonymous.demoapp.api;

import com.example.anonymous.demoapp.modal.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/api/json/get/cfdlYqzrfS")
    Call<List<Contact>> getContact();
}
