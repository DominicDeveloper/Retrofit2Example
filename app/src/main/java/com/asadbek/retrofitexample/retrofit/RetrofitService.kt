package com.asadbek.retrofitexample.retrofit

import com.asadbek.retrofitexample.models.listUsers.UserResponce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    // https://reqres.in/api/users?page=2
    @GET("users")
    fun getUsers():Call<UserResponce>


    @GET("users")
    fun getUsers(@Query("delay") delay:Int):Call<UserResponce>


}