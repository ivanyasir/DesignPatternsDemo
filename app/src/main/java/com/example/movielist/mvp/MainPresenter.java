package com.example.movielist.mvp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.movielist.mvc.ListMoviesModel;
import com.example.movielist.mvc.Network.ApiEndpoint;
import com.example.movielist.mvc.Network.Services;
import com.example.movielist.mvc.constants.Contants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter{

    private MainContract.View view;
    Retrofit api = Services.getClient();
    Call<ListMoviesModel> call = api.create(ApiEndpoint.class).getMovieList("Bearer "+ Contants.tokenBearer);

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        view.onLoading(true);
        call.enqueue(new Callback<ListMoviesModel>() {
            @Override
            public void onResponse(@NonNull Call<ListMoviesModel> call, @NonNull Response<ListMoviesModel> response) {
                view.onLoading(false);
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    Log.e("lo2", response.body().toString());
                    view.onResult(response.body());
                } else {
                    Log.e("log2",""+ response.code());
                    view.onMessage(""+response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ListMoviesModel> call, @NonNull Throwable t) {
                view.onLoading(false);
                Log.e("log2", t.getMessage());
                view.onMessage(""+t.getMessage());
            }
        });
    }

    @Override
    public void onDestroy() {
        call.cancel();
    }
}
