package com.rojas.androidtestkotlin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rojas.androidtestkotlin.data.model.MovieEntity
import com.rojas.androidtestkotlin.domain.Repo
import com.rojas.androidtestkotlin.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo:Repo):ViewModel() {

    val fetchMovieList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getMovieList())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun saveMovie(movie:MovieEntity){
        viewModelScope.launch {
            repo.insertMovie(movie)
        }
    }

    fun getAllMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getMoviesDatabase())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}