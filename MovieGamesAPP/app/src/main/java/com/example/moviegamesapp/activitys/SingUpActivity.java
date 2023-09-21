package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviegamesapp.R;
import com.example.moviegamesapp.database.DatabaseUserManager;

public class SingUpActivity extends AppCompatActivity {

    private EditText editTextNameSU, editTextUsernameSU, editTextPasswordSU;
    private Button buttonSingUp;
    private DatabaseUserManager databaseUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        editTextNameSU =  findViewById(R.id.editTextNameSU);
        editTextUsernameSU =  findViewById(R.id.editTextUsernameSU);
        editTextPasswordSU =  findViewById(R.id.editTextPasswordSU);
        databaseUserManager = new DatabaseUserManager(this);
        buttonSingUp = findViewById(R.id.buttonSingUp);

        buttonSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextNameSU.getText().toString();
                String username = editTextUsernameSU.getText().toString();
                String password = editTextPasswordSU.getText().toString();
                databaseUserManager.insertUser(name, username, password);
                databaseUserManager.readUsers();
                Intent next = new Intent(SingUpActivity.this, MainActivity.class);
                startActivity(next);
            }
        });
    }
}