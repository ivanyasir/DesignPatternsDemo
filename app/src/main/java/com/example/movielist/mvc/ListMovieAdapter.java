package com.example.movielist.mvc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.movielist.R;

import java.util.List;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder> {

    private final Context context;
    private final List<ResultsModel> dataList;

    public ListMovieAdapter(Context context, List<ResultsModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ListMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieAdapter.ViewHolder holder, int position) {
        holder.tv_title.setText(dataList.get(position).getOriginalTitle());
        holder.tv_date.setText(dataList.get(position).getReleaseDate());
        holder.tv_rating.setText(""+dataList.get(position).getVoewAverage());
        holder.tv_genres.setText(""+dataList.get(position).getId());
        holder.tv_desc.setText(dataList.get(position).getOverview());

        // create loading bar inside of image
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/original/"+dataList.get(position).getPosterPath())
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv_title, tv_date, tv_rating, tv_genres, tv_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            tv_genres = itemView.findViewById(R.id.tv_genres);
            tv_desc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
