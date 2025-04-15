package com.wilfredo.poke.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.wilfredo.poke.app.databinding.ActivityMainBinding
import com.wilfredo.poke.module.expose.Expose

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openApp.setOnClickListener {
            Expose.openModule(this)
        }
    }
}