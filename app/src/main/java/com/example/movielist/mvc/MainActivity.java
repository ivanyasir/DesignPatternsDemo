package com.example.movielist.mvc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movielist.R;
import com.example.movielist.mvc.Network.ApiEndpoint;
import com.example.movielist.mvc.Network.Services;
import com.example.movielist.mvc.constants.Contants;
import com.example.movielist.mvp.MainActivityMVP;
import com.example.movielist.mvvm.MainActivityMVVM;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv1;
    private ListMovieAdapter adapter;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("MovieList MVC");

        loading = findViewById(R.id.progressbar);
        rv1 = findViewById(R.id.rv1);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    private void getData() {
        loading.setVisibility(View.VISIBLE);
        Retrofit api = Services.getClient();
        Call<ListMoviesModel> call = api.create(ApiEndpoint.class).getMovieList("Bearer "+ Contants.tokenBearer);
        call.enqueue(new Callback<ListMoviesModel>() {
            @Override
            public void onResponse(@NonNull Call<ListMoviesModel> call, @NonNull Response<ListMoviesModel> response) {
                loading.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    adapter = new ListMovieAdapter(MainActivity.this, response.body().getResults());
                    rv1.setAdapter(adapter);
                    Log.e("log", response.body().toString());
                } else {
                    Log.e("log",""+ response.code());
                    Toast.makeText(MainActivity.this, "Gagal "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ListMoviesModel> call, @NonNull Throwable t) {
                loading.setVisibility(View.GONE);
                Log.e("log", t.getMessage());
                Toast.makeText(MainActivity.this, "Gagal "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_mvc) {
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        } else if (itemId == R.id.action_mvp) {
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivityMVP.class);
            startActivity(i);
        } else if (itemId == R.id.action_mvvm)  {
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivityMVVM.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter = null;
            rv1.setAdapter(null);
        }
    }
}