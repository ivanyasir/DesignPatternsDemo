package com.example.movielist.mvc;

import com.google.gson.annotations.SerializedName;

public class ResultsModel {

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("id")
    private int id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private float voewAverage;

    @SerializedName("vote_count")
    private int vodeCount;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getVoewAverage() {
        return voewAverage;
    }

    public void setVoewAverage(float voewAverage) {
        this.voewAverage = voewAverage;
    }

    public int getVodeCount() {
        return vodeCount;
    }

    public void setVodeCount(int vodeCount) {
        this.vodeCount = vodeCount;
    }

    @Override
    public String toString() {
        return "ResultsModel{" +
                "adult=" + adult +
                ", id=" + id +
                ", originalTitle=" + originalTitle +
                ", overview=" + overview +
                ", popularity=" + popularity +
                ", posterPath=" + posterPath +
                ", releaseDate=" + releaseDate +
                ", voewAverage=" + voewAverage +
                ", vodeCount=" + vodeCount +
                '}';
    }
}
