package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moviegamesapp.GlobalSingleton;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.model.CustomAdapter;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private Button buttonAddGameMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonAddGameMenu = findViewById(R.id.buttonAddGameMenu);
        recyclerView = findViewById(R.id.recyclerViewList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        //asignando layout
        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManger);

        customAdapter = new CustomAdapter(GlobalSingleton.getListGames(), this);
        recyclerView.setAdapter(customAdapter);

        buttonAddGameMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MenuActivity.this, AddGameActivity.class);
                startActivity(next);
            }
        });
    }
}