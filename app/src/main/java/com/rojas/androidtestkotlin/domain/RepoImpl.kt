package com.rojas.androidtestkotlin.domain

import com.rojas.androidtestkotlin.data.DataSource
import com.rojas.androidtestkotlin.data.model.Movie
import com.rojas.androidtestkotlin.data.model.MovieEntity
import com.rojas.androidtestkotlin.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {

    override suspend fun getMovieList(): Resource<List<Movie>> {
        return dataSource.getUpcoming()
    }

    override suspend fun getMoviesDatabase(): Resource<List<MovieEntity>> {
        return dataSource.getAllMoviesRoom()
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        dataSource.insertMovieRoom(movie)
    }

}