package com.example.apiintegrationvolley

import Adapter
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity(), Adapter.OnItemClickListener {

    val url = "https://api.github.com/users"
    var userInfoItem = arrayListOf<userInfoItem>() // Declare the list
    val userInfo = arrayListOf<userInfoItem>() // List to hold user info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Declare RecyclerView
        val recycler = findViewById<RecyclerView>(R.id.recyclerView)

        val stringRequest = StringRequest(url, { response ->
            val gsonBuilder = GsonBuilder()
            val gson = gsonBuilder.create()

            // Parse JSON response
            userInfoItem = gson.fromJson(response, Array<userInfoItem>::class.java).toCollection(ArrayList())

            // Add parsed data to userInfo list
            userInfoItem.forEach {
                userInfo.add(it)
            }

            // Create an instance of the Adapter with the click listener
            val adapter = Adapter(this, userInfo, this)
            recycler.layoutManager = GridLayoutManager(this, 2) // Set the grid layout with 2 columns
            recycler.adapter = adapter // Set the adapter


        }, Response.ErrorListener {
            Toast.makeText(this, "Some errors: " + it.toString(), Toast.LENGTH_SHORT).show()
        })

        // Initialize the Volley request queue
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    // Handle item click and pass data to the next activity
    override fun onItemClick(position: Int) {
        val clickedItem = userInfo[position]

        // Create an intent and put extras (data)
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("textView", clickedItem.login)
        intent.putExtra("imageView", clickedItem.avatar_url)

        // Start the next activity with the intent
        startActivity(intent)
    }
}
