package com.example.movielist.mvc.Network;

import com.example.movielist.mvc.ListMoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiEndpoint {

    @GET("discover/movie?with_genres=35")
    Call<ListMoviesModel> getMovieList(@Header("Authorization") String tokenBearer);

}
