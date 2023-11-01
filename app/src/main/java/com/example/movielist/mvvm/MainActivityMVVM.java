package com.example.movielist.mvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.movielist.R;
import com.example.movielist.mvc.ListMovieAdapter;
import com.example.movielist.mvc.MainActivity;
import com.example.movielist.mvp.MainActivityMVP;

public class MainActivityMVVM extends AppCompatActivity { // View

    private MainViewModel mainViewModel;
    private RecyclerView rv1;
    private ListMovieAdapter adapter;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvvm);
        getSupportActionBar().setTitle("MovieList MVVM");

        progressbar = findViewById(R.id.progressbar);
        rv1 = findViewById(R.id.rv1);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getItemList().observe(this, items -> {
            Log.d("log3", "observe");
            adapter = new ListMovieAdapter(this, items);
            rv1.setAdapter(adapter);
        });

        mainViewModel.getIsLoading().observe(this, loading -> {
            if (loading) {
                progressbar.setVisibility(View.VISIBLE);
            } else {
                progressbar.setVisibility(View.GONE);
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