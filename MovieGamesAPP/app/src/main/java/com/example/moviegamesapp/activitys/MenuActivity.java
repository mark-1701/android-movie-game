package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moviegamesapp.R;
import com.example.moviegamesapp.database.DatabaseGamesManager;
import com.example.moviegamesapp.model.CustomAdapter;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private Button buttonAddGameMenu;
    private DatabaseGamesManager databaseGamesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        buttonAddGameMenu = findViewById(R.id.buttonAddGameMenu);
        recyclerView = findViewById(R.id.recyclerViewList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        //INSTANCIA AL MANEJADOR DE LA BASE DE DATOS DE PARTIDAS
        databaseGamesManager = new DatabaseGamesManager(this);

        //asignando layout
        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManger);

        customAdapter = new CustomAdapter(databaseGamesManager.listGames(), this);
        recyclerView.setAdapter(customAdapter);

        //CERRAR LA BASE DE DATOS UNA VEZ USADA
        databaseGamesManager.closeDatabase();

        buttonAddGameMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MenuActivity.this, AddGameActivity.class);
                startActivity(next);
            }
        });
    }
}