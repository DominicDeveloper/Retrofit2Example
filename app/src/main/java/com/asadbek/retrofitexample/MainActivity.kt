package com.asadbek.retrofitexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.asadbek.retrofitexample.databinding.ActivityMainBinding
import com.asadbek.retrofitexample.models.listUsers.UserResponce
import com.asadbek.retrofitexample.retrofit.Common
import com.asadbek.retrofitexample.retrofit.RetrofitService
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


    }
}