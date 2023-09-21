package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviegamesapp.GameControllerSingleton;
import com.example.moviegamesapp.GlobalSingleton;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.model.Clue;
import com.example.moviegamesapp.model.Game;
import com.example.moviegamesapp.model.Riddle;

import java.util.LinkedList;

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

        Clue clue1 = listClues.get(0);
        Clue clue2 = listClues.get(1);
        Clue clue3 = listClues.get(2);
        Clue clue4 = listClues.get(3);

        //RELLENAR LA INFORMACION EN LOS ELEMENTOS DEL A APLICACION
        textView.setText(game.getName());
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