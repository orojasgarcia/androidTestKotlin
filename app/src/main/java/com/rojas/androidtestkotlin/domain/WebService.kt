package com.rojas.androidtestkotlin.domain

import com.rojas.androidtestkotlin.data.model.MovieList
import retrofit2.http.GET

interface WebService {

    @GET("upcoming?api_key=${Constants.API_KEY}&language=es&page=1")
    suspend fun getMoviesUpcoming(): MovieList

    object Constants {
        const val API_KEY = "f2333e7df5a5bfb99b4611ed13635dd8"
    }
}