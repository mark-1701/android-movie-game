package com.example.moviegamesapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviegamesapp.R;

import java.util.LinkedList;

public class CustomAdapterMovieList extends RecyclerView.Adapter<CustomAdapter.ViewHOlder>{
    private LinkedList<Movie> listMovies;
    private Context context;

    public CustomAdapterMovieList(LinkedList<Movie> listMovies, Context context) {
        this.listMovies = listMovies;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_movie_list, parent, false);
        return new CustomAdapter.ViewHOlder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHOlder holder, int position) {
        TextView textViewTitle = holder.cardView.findViewById(R.id.textViewTitleCardItem);
        TextView textViewYear = holder.cardView.findViewById(R.id.textViewYearCardItem);
        TextView textViewDescription = holder.cardView.findViewById(R.id.textViewDescriptionCardItem);
        ImageView imageView = holder.cardView.findViewById(R.id.imageViewCardItem);
        textViewTitle.setText(listMovies.get(position).getTitle());
        textViewYear.setText(listMovies.get(position).getDate());
        textViewDescription.setText(listMovies.get(position).getDescription());
        Glide.with(context)
                .load(listMovies.get(position).getImage())
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class ViewHOlder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public ViewHOlder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
