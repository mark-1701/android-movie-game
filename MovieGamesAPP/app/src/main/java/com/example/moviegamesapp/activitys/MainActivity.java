package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviegamesapp.AddGameSingleton;
import com.example.moviegamesapp.GameControllerSingleton;
import com.example.moviegamesapp.GlobalSingleton;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.database.DatabaseAcess;
import com.example.moviegamesapp.database.DatabaseUserManager;

public class MainActivity extends AppCompatActivity {

    private TextView textViewPassword, textViewSingUp;
    private EditText editTextUsernameLogin, editTextPasswordLogin;
    private Button buttonLogin;
    private DatabaseAcess databaseAcess;
    GameControllerSingleton gameControllerSingleton;
    GlobalSingleton globalSingleton;
    AddGameSingleton addGameSingleton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewSingUp = findViewById(R.id.textViewSingUp);
        editTextUsernameLogin = findViewById(R.id.editTextUsernameLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);

        buttonLogin = findViewById(R.id.buttonLogin);
        databaseAcess = new DatabaseAcess(this);

        //INSTANCIA INICIAL SINGLETON
        gameControllerSingleton = GameControllerSingleton.getInstance();
        globalSingleton = GlobalSingleton.getInstance();
        addGameSingleton = AddGameSingleton.getInstancia();

        /*SUBRAYADO DE LOS TEXTVIEW DEL LOGIN*/
        String text1 = "Crear una cuenta";
        SpannableString content1 = new SpannableString(text1);
        content1.setSpan(new UnderlineSpan(), 0, text1.length(), 0);

        textViewSingUp.setText(content1);

        textViewSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this,SingUpActivity.class);
                startActivity(next);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsernameLogin.getText().toString();
                String password = editTextPasswordLogin.getText().toString();
                if (databaseAcess.searchInformation(username, password)) {
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent next = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(next);
                }
            }
        });
    }
}