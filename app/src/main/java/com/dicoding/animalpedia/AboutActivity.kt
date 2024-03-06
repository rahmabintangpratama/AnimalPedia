package com.dicoding.animalpedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val tvName = findViewById<TextView>(R.id.tv_about_name)
        tvName.text = getString(R.string.about_name)

        val tvDescription = findViewById<TextView>(R.id.tv_about_description)
        tvDescription.text = getString(R.string.about_description)

        val btnBack = findViewById<Button>(R.id.btn_back)
        btnBack?.setOnClickListener {
            onBackPressed()
        }
    }
}