package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviegamesapp.AddGameSingleton;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.model.Game;
import com.example.moviegamesapp.model.Riddle;

import java.util.LinkedList;

public class AddGameActivity extends AppCompatActivity {
    EditText editTextNameRiddleAddGame, editTextNumberRiddleAddGame;
    Button buttonContinueAddGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        editTextNameRiddleAddGame = findViewById(R.id.editTextNameRiddleAddGame);
        editTextNumberRiddleAddGame = findViewById(R.id.editTextNumberRiddleAddGame);
        buttonContinueAddGame = findViewById(R.id.buttonContinueAddGame);
        AddGameSingleton.numberOfRiddles = 0;

        Toast.makeText(this, "+"+ AddGameSingleton.numberOfRiddles+ " +", Toast.LENGTH_SHORT).show();

        buttonContinueAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AGREGAR NOMBRE Y NUMERO DE ACERTIJOS DE LA PARTIDA
                AddGameSingleton.game.setName(editTextNameRiddleAddGame.getText().toString().trim());
                AddGameSingleton.numberOfRiddles = Integer.valueOf(editTextNumberRiddleAddGame.getText().toString());
                //NO PERMITE QUE NO HAYAN INTENTOS
                if (AddGameSingleton.numberOfRiddles == 0) AddGameSingleton.numberOfRiddles = 1;
                //QUITA EL VALOR DE ENTRADA POR EL PRIMER ACTIVITY QUE APARECE
                AddGameSingleton.numberOfRiddles--;
                Intent intent = new Intent(AddGameActivity.this, AddRiddleActivity.class);
                startActivity(intent);
            }
        });


    }
}