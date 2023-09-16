package com.strainteam.mispropinasisa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.strainteam.mispropinasisa.R
import com.strainteam.mispropinasisa.databinding.ActivityDetPropinaBinding

class DetPropina : AppCompatActivity() {
    private lateinit var binding : ActivityDetPropinaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetPropinaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}