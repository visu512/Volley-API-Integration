package com.example.apiintegrationvolley

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        val imageView: ImageView = findViewById(R.id.imageView)
        val textView: TextView  = findViewById(R.id.textView)

        // Get data from the intent
        val login = intent.getStringExtra("textView")
        val avatarUrl = intent.getStringExtra("imageView")

        // Display the data
        textView.text = login
        Glide.with(this).load(avatarUrl).into(imageView)
    }
}