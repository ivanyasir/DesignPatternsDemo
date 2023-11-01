package com.example.movielist.mvc;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListMoviesModel {

    @SerializedName("page")
    private String page;

    @SerializedName("results")
    private List<ResultsModel> results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<ResultsModel> getResults() {
        return results;
    }

    public void setResults(List<ResultsModel> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ListMoviesModel{" +
                "page='" + page + '\'' +
                ", results=" + results +
                '}';
    }
}

