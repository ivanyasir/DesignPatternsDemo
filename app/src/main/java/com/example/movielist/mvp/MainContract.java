package com.example.movielist.mvp;

import com.example.movielist.mvc.ListMoviesModel;

public interface MainContract {

    interface View {
        void onLoading(boolean loading);
        void onMessage(String messages);
        void onResult(ListMoviesModel model);
    }

    interface Presenter {
        void getData();
        void onDestroy();
    }
}
