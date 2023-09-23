package com.example.moviegamesapp.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviegamesapp.singletonclasses.GameControllerSingleton;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.activitys.GameActivity;
import com.example.moviegamesapp.activitys.MenuActivity;
import com.example.moviegamesapp.database.DatabaseGamesManager;

import java.util.LinkedList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHOlder> {
    private LinkedList<Game> listGames;
    private Context context;
    private DatabaseGamesManager databaseGamesManager;

    public CustomAdapter(LinkedList<Game> lista, Context con){
        this.listGames = lista;
        this.context = con;
        databaseGamesManager = new DatabaseGamesManager(context);
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_layout, parent, false);
        return new ViewHOlder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHOlder holder, int position) {

        // cargar la informacion
        TextView textViewName = holder.cardView.findViewById(R.id.textView3);

        //porque tengo error al intentar agregar el boton
        ImageButton buttonPlay = holder.cardView.findViewById(R.id.buttonPlay);
        ImageButton buttonDelete = holder.cardView.findViewById(R.id.buttonDelete);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GlobalSingleton.listGames.remove(position);
                databaseGamesManager.disableGame(listGames.get(position).getGameName(), false);
                Intent next = new Intent(holder.itemView.getContext(), MenuActivity.class);
                holder.itemView.getContext().startActivity(next);
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DESDE AQUI YA EMPIEZA EL JUEGO
                //CARGA EL JUEGO
                GameControllerSingleton.setGame(listGames.get(position));
                //CARGA EL ACERTIJO
                GameControllerSingleton.changeRiddle();
                Intent next = new Intent(holder.itemView.getContext(), GameActivity.class);
                holder.itemView.getContext().startActivity(next);
            }
        });

        textViewName.setText(listGames.get(position).getGameName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(holder.itemView.getContext(), GameActivity.class);
                //holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGames.size();
    }

    public class ViewHOlder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public ViewHOlder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
