package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviegamesapp.R;
import com.example.moviegamesapp.database.DatabaseUsersManager;

public class SingUpActivity extends AppCompatActivity {

    private EditText editTextNameSU, editTextUsernameSU, editTextPasswordSU;
    private Button buttonSingUp;
    private DatabaseUsersManager databaseUsersManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        editTextNameSU =  findViewById(R.id.editTextNameSU);
        editTextUsernameSU =  findViewById(R.id.editTextUsernameSU);
        editTextPasswordSU =  findViewById(R.id.editTextPasswordSU);
        databaseUsersManager = new DatabaseUsersManager(this);
        buttonSingUp = findViewById(R.id.buttonSingUp);

        buttonSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextNameSU.getText().toString();
                String username = editTextUsernameSU.getText().toString();
                String password = editTextPasswordSU.getText().toString();

                if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SingUpActivity.this, "Debes de llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    databaseUsersManager.insertUser(name, username, password);
                    databaseUsersManager.closeDatabase();
                    Intent next = new Intent(SingUpActivity.this, MainActivity.class);
                    startActivity(next);
                }
            }
        });
    }
}