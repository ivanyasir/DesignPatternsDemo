package com.example.movielist.mvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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
import com.example.movielist.mvc.ListMovieAdapter;
import com.example.movielist.mvc.ListMoviesModel;
import com.example.movielist.mvc.MainActivity;
import com.example.movielist.mvc.Network.ApiEndpoint;
import com.example.movielist.mvc.Network.Services;
import com.example.movielist.mvc.constants.Contants;
import com.example.movielist.mvvm.MainActivityMVVM;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivityMVP extends AppCompatActivity implements MainContract.View{

    private RecyclerView rv1;
    private ProgressBar progressBar;
    private ListMovieAdapter adapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvp);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().setTitle("MovieList MVP");

        progressBar = findViewById(R.id.progressbar);
        rv1 = findViewById(R.id.rv1);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        mainPresenter = new MainPresenter(this);
        mainPresenter.getData();

    }

    @Override
    public void onLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onMessage(String messages) {
        Toast.makeText(this, messages, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(ListMoviesModel model) {
        adapter = new ListMovieAdapter(MainActivityMVP.this, model.getResults());
        rv1.setAdapter(adapter);
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
        mainPresenter.onDestroy();
        super.onDestroy();
    }
}