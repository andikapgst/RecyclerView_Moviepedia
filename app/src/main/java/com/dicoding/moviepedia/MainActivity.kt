package com.dicoding.moviepedia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFilms: RecyclerView
    private val list = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFilms = findViewById(R.id.rv_films)
        rvFilms.setHasFixedSize(true)

        list.addAll(getListFilms())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Recycle")
    private fun getListFilms(): ArrayList<Film> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataGenre = resources.getStringArray(R.array.film_genre)
        val dataYear = resources.getStringArray(R.array.year)
        val dataDirector = resources.getStringArray(R.array.film_director)
        val listFilm = ArrayList<Film>()
        for (i in dataName.indices) {
            val film = Film(dataName[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1),
                dataGenre[i],
                dataYear[i],
                dataDirector[i])
            listFilm.add(film)
        }
        return listFilm
    }

    private fun showRecyclerList() {
        rvFilms.layoutManager = LinearLayoutManager(this)
        val listFilmAdapter = ListFilmAdapter(list)
        rvFilms.adapter = listFilmAdapter

        listFilmAdapter.setOnItemClickListener(object : ListFilmAdapter.OnItemClickListener {
            override fun onItemClicked(data: Film) {
                showSelectedFilm(data)
            }
        })
    }

    private fun showSelectedFilm(film: Film) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("film", film)
        startActivity(intent)
    }
}