package com.rojas.androidtestkotlin.domain

import com.rojas.androidtestkotlin.data.model.Movie
import com.rojas.androidtestkotlin.data.model.MovieEntity
import com.rojas.androidtestkotlin.vo.Resource

interface Repo {
    suspend fun getMovieList(): Resource<List<Movie>>
    suspend fun getMoviesDatabase(): Resource<List<MovieEntity>>
    suspend fun insertMovie(movie:MovieEntity)
}