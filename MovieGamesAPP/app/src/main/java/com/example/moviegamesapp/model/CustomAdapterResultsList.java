package com.example.moviegamesapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviegamesapp.R;

import org.w3c.dom.Text;

import java.util.LinkedList;

public class CustomAdapterResultsList extends RecyclerView.Adapter<CustomAdapter.ViewHOlder>{
    private LinkedList<Result> resultsList;
    private Context context;

    public CustomAdapterResultsList(LinkedList<Result> resultsList, Context context) {
        this.resultsList = resultsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_results_list, parent, false);
        return new CustomAdapter.ViewHOlder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHOlder holder, int position) {
        //MAPEO DE LOS COMPONENTES
        TextView textViewUsernameCardItemResultsList = holder.cardView.findViewById(R.id.textViewUsernameCardItemResultsList);
        TextView textViewScoreCardItemResultsList = holder.cardView.findViewById(R.id.textViewScoreCardItemResultsList);
        TextView textViewGameCardItemResultsList = holder.cardView.findViewById(R.id.textViewGameCardItemResultsList);
        TextView textViewDateCardItemResultsList = holder.cardView.findViewById(R.id.textViewDateCardItemResultsList);
        //RELLENAR LOS COMPONENTES CON INFORMACION
        textViewUsernameCardItemResultsList.setText(resultsList.get(position).getUsername());
        textViewScoreCardItemResultsList.setText(String.valueOf(resultsList.get(position).getScore()));
        textViewGameCardItemResultsList.setText(resultsList.get(position).getGame());
        textViewDateCardItemResultsList.setText(resultsList.get(position).getDate().toString());
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class ViewHOlder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public ViewHOlder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
