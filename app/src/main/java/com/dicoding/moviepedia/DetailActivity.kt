package com.dicoding.moviepedia

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        @Suppress("DEPRECATION")
        val film = intent.getParcelableExtra<Film>("film")

        val ivDetailPhoto: ImageView = findViewById(R.id.img_poster)
        val tvDetailTitle: TextView = findViewById(R.id.tv_title)
        val tvDetailGenre: TextView = findViewById(R.id.data_genre)
        val tvDetailYear: TextView = findViewById(R.id.data_year)
        val tvDetailDirector: TextView = findViewById(R.id.data_director)
        val tvDetailDescription: TextView = findViewById(R.id.description)
        val shareBtn: Button = findViewById(R.id.action_share)

        if (film != null) {
            ivDetailPhoto.setImageResource(film.photo)
            tvDetailTitle.text = film.name
            tvDetailGenre.text = film.genre
            tvDetailYear.text = film.year
            tvDetailDirector.text = film.director
            tvDetailDescription.text = film.description

            shareBtn.setOnClickListener {
                val bitmapDrawable = ivDetailPhoto.drawable as BitmapDrawable
                val bitmap = bitmapDrawable.bitmap
                shareImageText(bitmap)
            }
        }
    }
        private fun shareImageText(bitmap: Bitmap?) {
            val uri = getImageToShare(bitmap)
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.putExtra(Intent.EXTRA_TEXT, "Sharing Movie Poster")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
            intent.type = "image/png"
            startActivity(Intent.createChooser(intent, "Share Poster via"))
        }

        private fun getImageToShare(bitmap: Bitmap?): Uri? {
            val imageFolder = File(cacheDir, "images")
            var uri: Uri? = null
            try {
                imageFolder.mkdirs()
                val file = File(imageFolder, "poster.png")
                val outputStream = FileOutputStream(file)
                bitmap?.compress(Bitmap.CompressFormat.PNG, 95, outputStream)
                outputStream.flush()
                outputStream.close()
                uri = FileProvider.getUriForFile(this, "com.dicoding.moviepedia", file)
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
            return uri
        }
}