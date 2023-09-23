package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviegamesapp.singletonclasses.AddGameSingleton;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.database.DatabaseGamesManager;
import com.example.moviegamesapp.model.Clue;

import java.util.LinkedList;

public class AddRiddleActivity extends AppCompatActivity {

    private Button buttonContinueAddRiddle;
    private EditText editTextEmojisAddRiddle, editTextPCorrectAnswerAddRiddle, editTextP2AddRiddle, editTextP3AddRiddle, editTextP4AddRiddle;
    private LinkedList<Clue> listClues;
    private DatabaseGamesManager databaseGamesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_riddle);
        buttonContinueAddRiddle = findViewById(R.id.buttonContinueAddRiddle);
        editTextEmojisAddRiddle = findViewById(R.id.editTextEmojisAddRiddle);
        editTextPCorrectAnswerAddRiddle = findViewById(R.id.editTextPCorrectAnswerAddRiddle);
        editTextP2AddRiddle = findViewById(R.id.editTextP2AddRiddle);
        editTextP3AddRiddle = findViewById(R.id.editTextP3AddRiddle);
        editTextP4AddRiddle = findViewById(R.id.editTextP4AddRiddle);
        listClues = new LinkedList<>();

        //INSTANCIA AL MANEJADOR DE LA BASE DE DATOS DE PARTIDAS
        databaseGamesManager = new DatabaseGamesManager(this);

        //SABER NUMERO DE INTENTOS POSIBLES
        Toast.makeText(this, "+"+ AddGameSingleton.numberOfRiddles+ " +", Toast.LENGTH_SHORT).show();

        buttonContinueAddRiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newRiddleId = databaseGamesManager.generateRandomNumbers();
                String emojis = editTextEmojisAddRiddle.getText().toString().trim();
                String correctAnswer = editTextPCorrectAnswerAddRiddle.getText().toString().trim();
                String riddle2 = editTextP2AddRiddle.getText().toString().trim();
                String riddle3 = editTextP3AddRiddle.getText().toString().trim();
                String riddle4 = editTextP4AddRiddle.getText().toString().trim();

                if (emojis.isEmpty() || correctAnswer.isEmpty() || riddle2.isEmpty() || riddle3.isEmpty() || riddle4.isEmpty()) {
                    Toast.makeText(AddRiddleActivity.this, "Debes de llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    //NUEVA LOGICA
                    databaseGamesManager.insertClues(newRiddleId, correctAnswer);
                    databaseGamesManager.insertClues(newRiddleId, riddle2);
                    databaseGamesManager.insertClues(newRiddleId, riddle3);
                    databaseGamesManager.insertClues(newRiddleId, riddle4);
                    databaseGamesManager.insertRiddle(newRiddleId, AddGameSingleton.gameName, emojis, correctAnswer);

                    //CAMBIAR AL MENU O SEGUIR AGREGANDO ACERTIJOS
                    if (AddGameSingleton.numberOfRiddles == 0) {
                        databaseGamesManager.closeDatabase();
                        AddGameSingleton.restartAddGame();
                        Intent next = new Intent(AddRiddleActivity.this, MenuActivity.class);
                        startActivity(next);
                    } else {
                        AddGameSingleton.numberOfRiddles--;
                        Intent next = new Intent(AddRiddleActivity.this, AddRiddleActivity.class);
                        startActivity(next);
                    }
                }
            }
        });
    }
}