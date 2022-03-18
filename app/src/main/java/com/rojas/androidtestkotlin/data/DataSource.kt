package com.rojas.androidtestkotlin.data

import com.rojas.androidtestkotlin.AppDatabase
import com.rojas.androidtestkotlin.data.model.Movie
import com.rojas.androidtestkotlin.data.model.MovieEntity
import com.rojas.androidtestkotlin.vo.Resource
import com.rojas.androidtestkotlin.vo.RetrofitClient

class DataSource(private val appDatabase: AppDatabase) {

    suspend fun getUpcoming():Resource<List<Movie>>{
        return Resource.Success(RetrofitClient.webService.getMoviesUpcoming().movieList)
    }

    suspend fun insertMovieRoom(movie: MovieEntity){
        appDatabase.movieDao().insertMovie(movie)
    }

    suspend fun getAllMoviesRoom(): Resource<List<MovieEntity>>{
        return Resource.Success(appDatabase.movieDao().getAllMovies())
    }
}