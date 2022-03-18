package com.rojas.androidtestkotlin.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("title")
    val name: String = "",
    val id: String = "",
    @SerializedName("poster_path")
    val image: String = "",
    val overview: String = ""
) : Parcelable

data class MovieList(
    @SerializedName("results")
    val movieList: List<Movie>
)

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val movieId: String,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "image")
    val image: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = ""
)