package com.example.moviegamesapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.moviegamesapp.R;
import com.example.moviegamesapp.database.RealtimeResultsDatabase;
import com.example.moviegamesapp.model.CustomAdapterMovieList;
import com.example.moviegamesapp.model.CustomAdapterResultsList;
import com.example.moviegamesapp.model.Result;
import com.example.moviegamesapp.singletonclasses.GlobalSingleton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class RealTimeDatabase extends AppCompatActivity {

    private RealtimeResultsDatabase realtimeResultsDatabase = new RealtimeResultsDatabase();
    private ImageButton imageButtonBackRealTimeDatabase;
    private RecyclerView recyclerViewResultsList;
    private CustomAdapterResultsList customAdapterResultsList;
    private LinkedList<Result> resultsList = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_database);
        imageButtonBackRealTimeDatabase = findViewById(R.id.imageButtonBackRealTimeDatabase);
        recyclerViewResultsList = findViewById(R.id.recyclerViewResultsList);
        recyclerViewResultsList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewResultsList.setLayoutManager(linearLayoutManager);

        customAdapterResultsList = new CustomAdapterResultsList(resultsList, this);
        recyclerViewResultsList.setAdapter(customAdapterResultsList);
        loadData();

        imageButtonBackRealTimeDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(RealTimeDatabase.this,MenuActivity.class);
                startActivity(next);
            }
        });
    }

    private void loadData() {

        realtimeResultsDatabase.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        String username = data.child("username").getValue(String.class);
                        Double score = data.child("score").getValue(Double.class);
                        String game = data.child("game").getValue(String.class);
                        //PARA OBTENER EL JSON DE FIREBASE SE RETORNA UN HASHMAP
                        HashMap<String, Object> newHashMap = data.child("date").getValue(new GenericTypeIndicator<HashMap<String, Object>>() {});
                        LocalDateTime localDateTime = getDate(newHashMap);
                        Result result = new Result(username, score, game, localDateTime);
                        resultsList.add(result);
                    }
                    customAdapterResultsList.notifyDataSetChanged();
                } catch (Exception e) {
                    Toast.makeText(RealTimeDatabase.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("MiApp", e.getMessage());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private LocalDateTime getDate(HashMap<String, Object> newHashMap) {
        //DENTRO DEL HASHMAP SE UTILIZAN DATONS DE TIPO LONG, SE UTILIZA UNA CASCADA DE CASTING PARA CONVERTIRLO A INTEGER
        int year = (int) (long) newHashMap.get("year");
        int month = (int) (long) newHashMap.get("monthValue");
        int day = (int) (long) newHashMap.get("dayOfMonth");
        int hour = (int) (long) newHashMap.get("hour");
        int minute = (int) (long) newHashMap.get("minute");
        int second = (int) (long) newHashMap.get("second");
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        return localDateTime;
    }
}