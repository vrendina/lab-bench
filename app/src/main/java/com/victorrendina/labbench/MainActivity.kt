package com.victorrendina.labbench

import android.os.Bundle
import com.victorrendina.labbench.databinding.ActivityMainBinding

class MainActivity : LabActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
