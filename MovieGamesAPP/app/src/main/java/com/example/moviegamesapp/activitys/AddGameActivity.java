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

public class AddGameActivity extends AppCompatActivity {
    EditText editTextNameRiddleAddGame, editTextNumberRiddleAddGame;
    Button buttonContinueAddGame;
    private DatabaseGamesManager databaseGamesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        editTextNameRiddleAddGame = findViewById(R.id.editTextNameRiddleAddGame);
        editTextNumberRiddleAddGame = findViewById(R.id.editTextNumberRiddleAddGame);
        buttonContinueAddGame = findViewById(R.id.buttonContinueAddGame);

        //INSTANCIA AL MANEJADOR DE LA BASE DE DATOS DE PARTIDAS
        databaseGamesManager = new DatabaseGamesManager(this);

        //INICIAR CONTADOR NUMERO DE ACERTIJOS
        AddGameSingleton.numberOfRiddles = 0;

        buttonContinueAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gameName = editTextNameRiddleAddGame.getText().toString().trim();
                String numberOfRiddlesString = editTextNumberRiddleAddGame.getText().toString().trim();
                int numberOfRiddles = 1;
                if (!numberOfRiddlesString.isEmpty()) {
                    numberOfRiddles = Integer.valueOf(numberOfRiddlesString);
                    //NO PERMITE 0 INTENTOS
                    if (numberOfRiddles == 0) numberOfRiddles = 1;
                }

                //SOLO VERIFICA EL CAMPO LLENO DEL NOMBRE
                //PARA EL NUMERO DE ACERTIJOS SE CARGA POR DEFECTO AL MENOS UN INTENTO
                if (gameName.isEmpty()) {
                    Toast.makeText(AddGameActivity.this, "Debes de llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    //BUSCAR SI EL NOMBRE YA EXISTE PARA SEGUIR
                    if (databaseGamesManager.searchGameName(gameName)) {
                        databaseGamesManager.insertGame(gameName, true);
                        databaseGamesManager.closeDatabase();
                        //AGREGAR EL NOMBRE Y EL NUMERO DE ACERTIJOS DE FORMA GLOBAL
                        AddGameSingleton.gameName = gameName;
                        AddGameSingleton.numberOfRiddles = numberOfRiddles;

                        //QUITA EL VALOR DE ENTRADA POR EL PRIMER ACTIVITY QUE APARECE
                        AddGameSingleton.numberOfRiddles--;
                        Intent intent = new Intent(AddGameActivity.this, AddRiddleActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}