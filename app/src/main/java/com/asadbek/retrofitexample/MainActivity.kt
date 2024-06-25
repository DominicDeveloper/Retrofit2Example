package com.asadbek.retrofitexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.asadbek.retrofitexample.databinding.ActivityMainBinding
import com.asadbek.retrofitexample.models.created.ReqUser
import com.asadbek.retrofitexample.models.created.ResUser
import com.asadbek.retrofitexample.models.listUsers.UserResponce
import com.asadbek.retrofitexample.models.single.SingleUserResponse
import com.asadbek.retrofitexample.models.update.ResUpdateUser
import com.asadbek.retrofitexample.retrofit.Common
import com.asadbek.retrofitexample.retrofit.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    lateinit var retrofitService: RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofitService = Common.retrofitService

        // barcha user malumotlarini olib kelish
        retrofitService.getUsers().enqueue(object : Callback<UserResponce>{
            // onResponse - api dan javob muvaffaqiyatli kelganda ushbu funktsiya ishka tushadi
            override fun onResponse(call: Call<UserResponce>, response: Response<UserResponce>) {
                if (response.isSuccessful){
                    Log.d(TAG, "onResponse: ${response.body()}")
                    binding.myTextView.text = "${response.body()}"
                }
            }

            // api dan javob muvaffaqiyatsiz kelganda ushbu funktsiya ishka tushadi
            override fun onFailure(call: Call<UserResponce>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Muammo yuz berdi!", Toast.LENGTH_SHORT).show()
            }

        })


        // id orqali bitta usr ma`lumotini olib kelish tanlab
        retrofitService.getSingleUser(23).enqueue(object :Callback<SingleUserResponse>{
            override fun onResponse(
                call: Call<SingleUserResponse>,
                response: Response<SingleUserResponse>
            ) {
                Log.d(TAG, "responseSingle: ${response.body()?.data}")
            }

            override fun onFailure(call: Call<SingleUserResponse>, t: Throwable) {
                Log.d(TAG, "responseSingle: ${t.message}")
            }

        })

        // post
        val reqUser = ReqUser("Dominic","Developer")
        retrofitService.createUser(reqUser).enqueue(object :Callback<ResUser>{
            override fun onResponse(call: Call<ResUser>, response: Response<ResUser>) {
              if (response.isSuccessful){
                  Log.d(TAG, "onResponseCreate: ${response.body()}")
              }
            }

            override fun onFailure(call: Call<ResUser>, t: Throwable) {

            }

        })
        // update
        val reqUser2 = ReqUser("Bobur","Developer")
        retrofitService.updateUser(2,reqUser2).enqueue(object :Callback<ResUpdateUser>{
            override fun onResponse(call: Call<ResUpdateUser>, response: Response<ResUpdateUser>) {
                if (response.isSuccessful){
                    Log.d(TAG, "update: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ResUpdateUser>, t: Throwable) {

            }

        })

        // delete
        retrofitService.deleteUser(1).enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    Log.d(TAG, "delete: ${response.body()} ")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

        })

    }
}