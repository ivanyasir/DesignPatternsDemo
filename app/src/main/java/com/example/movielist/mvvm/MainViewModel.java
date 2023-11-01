package com.example.movielist.mvvm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movielist.mvc.ListMoviesModel;
import com.example.movielist.mvc.Network.ApiEndpoint;
import com.example.movielist.mvc.Network.Services;
import com.example.movielist.mvc.ResultsModel;
import com.example.movielist.mvc.constants.Contants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainViewModel extends ViewModel { // sebagai viewmodel
    private MutableLiveData<List<ResultsModel>> itemListLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public LiveData<List<ResultsModel>> getItemList() {
        if (itemListLiveData.getValue() == null) {
            Log.d("log3", "getValue = null");
            loadItems();
        }
        return itemListLiveData;
    }

    public LiveData<Boolean> getIsLoading() {
        return loading;
    }

    private void loadItems() {
        loading.setValue(true);
        Log.d("log3", "loadItems");
        // Fetch data from a repository or API and populate itemListLiveData
        Log.d("log3", "getData");
        Retrofit api = Services.getClient();
        Call<ListMoviesModel> call = api.create(ApiEndpoint.class).getMovieList("Bearer "+ Contants.tokenBearer);
        call.enqueue(new Callback<ListMoviesModel>() {
            @Override
            public void onResponse(@NonNull Call<ListMoviesModel> call, @NonNull Response<ListMoviesModel> response) {
                loading.setValue(false);
                if (response.isSuccessful()) {

                    Log.e("lo2", response.body().toString());
                    itemListLiveData.setValue(response.body().getResults());
                } else {
                    Log.e("log2",""+ response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ListMoviesModel> call, @NonNull Throwable t) {
                loading.setValue(false);
                Log.e("log2", t.getMessage());
            }
        });
    }

}
