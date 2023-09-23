package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moviegamesapp.singletonclasses.GameControllerSingleton;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.model.Clue;
import com.example.moviegamesapp.model.Game;
import com.example.moviegamesapp.model.Riddle;

import java.util.LinkedList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button buttonGame1, buttonGame2, buttonGame3, buttonGame4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textView = findViewById(R.id.textViewTitleGame);
        editText = findViewById(R.id.editTextEmojisGame);
        buttonGame1 = findViewById(R.id.buttonGame1);
        buttonGame2 = findViewById(R.id.buttonGame2);
        buttonGame3 = findViewById(R.id.buttonGame3);
        buttonGame4 = findViewById(R.id.buttonGame4);

        //OBTENER LA INFORMACION DE LA VARIABLE ESTATICA GLOBAL
        GameControllerSingleton.getInstance();
        Game game = GameControllerSingleton.getGame();
        Riddle riddle = GameControllerSingleton.getRiddle();
        LinkedList<Clue> listClues = riddle.getListClues();

        //DESORDENAR LA LISTA PARA QUE NO SE IMPRIMAN CON EL MISMO ORDEN

        //COMO FUNCIONA EL ALGORITMO
        //  - Agrega a la lista un elemento de la lista1 a lista2
        //  - remueve el elemento substraido de la lista1 (lista.remove(x))
        //  - el elemento substraido es un numero random entre el tamanio de la lista (random.nextInt(5)) numero random entre 0 - 4
        //  - como parametro de nextInt se agrega lista.size() el numero random estaria entre el rango del tamanio de la lista
        //  - el tamanio de la lista por ejemplo seria 5, y el numero random seria entre 0 a 4, justamente coincide con las posiciones disponibles

        Random random = new Random();
        LinkedList<Clue> unorderedClueList = listClues;
        int clueListSize = listClues.size();

        for (int i = 0; i < clueListSize; i++) {
            unorderedClueList.add(listClues.remove(random.nextInt(listClues.size())));
        }

        Clue clue1 = unorderedClueList.get(0);
        Clue clue2 = unorderedClueList.get(1);
        Clue clue3 = unorderedClueList.get(2);
        Clue clue4 = unorderedClueList.get(3);

        //RELLENAR LA INFORMACION EN LOS ELEMENTOS DEL A APLICACION
        textView.setText(game.getGameName());
        editText.setText(riddle.getEmojis());
        buttonGame1.setText(clue1.getClue());
        buttonGame2.setText(clue2.getClue());
        buttonGame3.setText(clue3.getClue());
        buttonGame4.setText(clue4.getClue());

        buttonGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRiddle(buttonGame1.getText().toString());
            }
        });
        buttonGame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRiddle(buttonGame2.getText().toString());
            }
        });
        buttonGame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRiddle(buttonGame3.getText().toString());
            }
        });
        buttonGame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRiddle(buttonGame4.getText().toString());
            }
        });
    }

    //METODOS CADA LOS UNO LOS BOTONES CUANDO SON SELECCIONADOS
    public void playRiddle (String textbutton) {
        GameControllerSingleton.validateRiddle(textbutton);

        if (GameControllerSingleton.checkLimit()) {
            Intent next = new Intent(GameActivity.this, ScoreActivity.class);
            startActivity(next);
        } else {
            GameControllerSingleton.changeLevel();
            GameControllerSingleton.changeRiddle();
            //TOASTS PARA LLEVAR EL CONTROL DE LA INFORMACION
//                    Toast.makeText(GameActivity.this, "+"+ GameControllerSingleton.getScore()+ " +", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(GameActivity.this, "+"+ GameControllerSingleton.getLevel()+ " +", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(GameActivity.this, "+"+ GameControllerSingleton.getLimitLevel() + " +", Toast.LENGTH_SHORT).show();

            Intent next = new Intent(GameActivity.this, GameActivity.class);
            startActivity(next);
        }
    }
}