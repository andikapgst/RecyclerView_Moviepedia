package com.dicoding.moviepedia

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val imgProfile = findViewById<ImageView>(R.id.my_photo)
        val tvName = findViewById<TextView>(R.id.my_name)
        val tvEmail = findViewById<TextView>(R.id.my_email)

        imgProfile.setImageResource(R.drawable.my_pict)
        tvName.text = resources.getString(R.string.profile_name)
        tvEmail.text = resources.getString(R.string.email)
    }
}