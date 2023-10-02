package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviegamesapp.database.RealtimeResultsDatabase;
import com.example.moviegamesapp.model.Result;
import com.example.moviegamesapp.singletonclasses.GameControllerSingleton;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.singletonclasses.GlobalSingleton;

import java.time.LocalDateTime;

public class ScoreActivity extends AppCompatActivity {

    private TextView textViewResultsScore;
    private Button buttonBackToMenu;
    private RealtimeResultsDatabase realtimeResultsDatabase = new RealtimeResultsDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //GUARDAR EL RESULTADO EN FIREBASE
        saveResult();
        //MAPEAR LA INFORMACION
        textViewResultsScore = findViewById(R.id.textViewResultsScore);
        buttonBackToMenu = findViewById(R.id.buttonBackToMenu);
        //CARGAR EL PUNTAJE COMO ENTERO
        int score = (int) GameControllerSingleton.getScore();

        //IMPRIMIR EL RESULTADO Y RESTAURAR LOS VALORES DEL GAMECONTROLLERGAME
        textViewResultsScore.setText("Puntaje: " + score + " pts");
        GameControllerSingleton.restartGame();

        buttonBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveResult() {
        String username = GlobalSingleton.userName;
        double score = GameControllerSingleton.getScore();
        String game = GameControllerSingleton.getGame().getGameName();
        LocalDateTime date = LocalDateTime.now();
        Result result = new Result(username, score, game, date);
        realtimeResultsDatabase.add(result).addOnSuccessListener(suc ->
        {
            Toast.makeText(this, "Se registro en Firebase con exito", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(er ->
        {
            Toast.makeText(this, er.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}