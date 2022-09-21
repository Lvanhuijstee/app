package com.example.accesment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ListView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.accesment.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var  nasaAdapter: NasaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressbar.isVisible = true
            val response = try {
                RetrofitInstance.api.getNasa()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.progressbar.isVisible = false
                return@launchWhenCreated

            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressbar.isVisible = false
                return@launchWhenCreated

            }
            if(response.isSuccessful && response.body() != null){
                nasaAdapter.todos = response.body()!!
            }else{
                Log.e(TAG, "Responce not successfull")
            }
            binding.progressbar.isVisible = false
        }
    }

    private fun setupRecyclerView() = binding.LatestImg.apply {
        nasaAdapter = NasaAdapter()
        adapter= nasaAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}