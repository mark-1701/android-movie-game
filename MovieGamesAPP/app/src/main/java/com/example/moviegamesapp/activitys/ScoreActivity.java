package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moviegamesapp.GameControllerSingleton;
import com.example.moviegamesapp.R;

public class ScoreActivity extends AppCompatActivity {

    TextView textViewResultsScore;
    Button buttonBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        textViewResultsScore = findViewById(R.id.textViewResultsScore);
        buttonBackToMenu = findViewById(R.id.buttonBackToMenu);

        textViewResultsScore.setText("Puntaje: " + GameControllerSingleton.getScore() + " pts");
        GameControllerSingleton.restartGame();

        buttonBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}