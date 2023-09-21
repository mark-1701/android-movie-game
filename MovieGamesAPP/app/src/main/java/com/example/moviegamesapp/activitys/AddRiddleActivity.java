package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviegamesapp.AddGameSingleton;
import com.example.moviegamesapp.GameControllerSingleton;
import com.example.moviegamesapp.GlobalSingleton;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.model.Clue;
import com.example.moviegamesapp.model.Game;
import com.example.moviegamesapp.model.Riddle;

import java.util.LinkedList;

public class AddRiddleActivity extends AppCompatActivity {

    private Button buttonContinueAddRiddle;
    private EditText editTextEmojisAddRiddle, editTextPCorrectAnswerAddRiddle, editTextP2AddRiddle, editTextP3AddRiddle, editTextP4AddRiddle;
    private LinkedList<Clue> listClues;

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

        //SABER NUMERO DE INTENTOS POSIBLES
        Toast.makeText(this, "+"+ AddGameSingleton.numberOfRiddles+ " +", Toast.LENGTH_SHORT).show();

        buttonContinueAddRiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //AGREGAR ACERTIJO CON SUS PISTAS
                String emojis = editTextEmojisAddRiddle.getText().toString().trim();
                String correctAnswer = editTextPCorrectAnswerAddRiddle.getText().toString().trim();
                listClues.add(new Clue(1, correctAnswer));
                listClues.add(new Clue(2, editTextP2AddRiddle.getText().toString().trim()));
                listClues.add(new Clue(3, editTextP3AddRiddle.getText().toString().trim()));
                listClues.add(new Clue(4, editTextP4AddRiddle.getText().toString().trim()));
                AddGameSingleton.listRiddle.add(new Riddle(1, emojis, correctAnswer,  listClues));

                //CAMBIAR AL MENU O SEGUIR AGREGANDO ACERTIJOS
                if (AddGameSingleton.numberOfRiddles == 0) {
                    //AGREGAR DATOS AL OBJETO GAME
                    AddGameSingleton.game.setIdGame(0);
                    AddGameSingleton.game.setListRiddles(AddGameSingleton.listRiddle);

                    GlobalSingleton.listGames.add(AddGameSingleton.game);
                    AddGameSingleton.restartAddGame();
                    Intent next = new Intent(AddRiddleActivity.this, MenuActivity.class);
                    startActivity(next);
                } else {
                    AddGameSingleton.numberOfRiddles--;
                    Intent next = new Intent(AddRiddleActivity.this, AddRiddleActivity.class);
                    startActivity(next);
                }
            }
        });
    }
}