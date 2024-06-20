package com.asadbek.retrofitexample.retrofit

object Common {
    val BASE_URL = "https://reqres.in/api/"

    val retrofitService:RetrofitService
        get() = RetrofitClient.getRetrofit(BASE_URL).create(RetrofitService::class.java)
}