package com.dicoding.moviepedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val name: String,
    val description: String,
    val photo: Int,
    val genre: String,
    val year: String,
    val director: String
) : Parcelable