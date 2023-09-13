package com.strainteam.mispropinasisa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.strainteam.mispropinasisa.databinding.ActivityMainBinding
import com.strainteam.mispropinasisa.viewmodel.HistorialViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val historialViewModel: HistorialViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        historialViewModel.onCreate()

        historialViewModel.historialList.observe(this, Observer {
            Toast.makeText(this, it.lista.size.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}