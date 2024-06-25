package com.asadbek.retrofitexample.retrofit

import com.asadbek.retrofitexample.models.created.ReqUser
import com.asadbek.retrofitexample.models.created.ResUser
import com.asadbek.retrofitexample.models.single.SingleUserResponse
import com.asadbek.retrofitexample.models.listUsers.UserResponce
import com.asadbek.retrofitexample.models.update.ResUpdateUser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    // https://reqres.in/api/users?page=2
    @GET("users")
    fun getUsers():Call<UserResponce>


    @GET("users")
    fun getUsers(@Query("delay") delay:Int):Call<UserResponce>

    @GET("users/{id}")
    fun getSingleUser(@Path("id") id:Int):Call<SingleUserResponse>

    @POST("users")
    fun createUser(@Body reqUser: ReqUser):Call<ResUser>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: Int, @Body reqUser: ReqUser):Call<ResUpdateUser>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: Int):Call<ResponseBody>

}