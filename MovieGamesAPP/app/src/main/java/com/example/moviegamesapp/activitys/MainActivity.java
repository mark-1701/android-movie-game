package com.example.moviegamesapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviegamesapp.singletonclasses.AddGameSingleton;
import com.example.moviegamesapp.singletonclasses.GameControllerSingleton;
import com.example.moviegamesapp.singletonclasses.GlobalSingleton;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.database.DatabaseAcess;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    private TextView textViewPassword, textViewSingUp;
    private EditText editTextUsernameLogin, editTextPasswordLogin;
    private Button buttonLogin;
    private ImageButton imageButtonGoogleLogin;
    private DatabaseAcess databaseAcess;
    GameControllerSingleton gameControllerSingleton;
    GlobalSingleton globalSingleton;
    AddGameSingleton addGameSingleton;

    private GoogleSignInClient client;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewSingUp = findViewById(R.id.textViewSingUp);
        editTextUsernameLogin = findViewById(R.id.editTextUsernameLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);
        buttonLogin = findViewById(R.id.buttonLogin);
        imageButtonGoogleLogin = findViewById(R.id.imageButtonGoogleLogin);
        databaseAcess = new DatabaseAcess(this);

        // CONFIGURACIONES INICIO SESION CON GOOGLE
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this, options);
        auth = FirebaseAuth.getInstance();

        //INSTANCIA INICIAL SINGLETON
        gameControllerSingleton = GameControllerSingleton.getInstance();
        globalSingleton = GlobalSingleton.getInstance(this);
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

        imageButtonGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = client.getSignInIntent();
                startActivityForResult(intent, 1234);

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsernameLogin.getText().toString();
                String password = editTextPasswordLogin.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes de llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (databaseAcess.searchInformation(username, password)) {
                        databaseAcess.closeDatabase();
                        //CARGAR EL NOMBRE DE USUARIO EN LA VARIABLE GLOBAL
                        GlobalSingleton.userName = username;
                        //MENSAJE DE BIENVENIDA
                        Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        Intent next = new Intent(MainActivity.this,MenuActivity.class);
                        startActivity(next);
//                        finish();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    FirebaseUser user = auth.getCurrentUser();
                                    //CARGAR EL NOMBRE DE USUARIO EN LA VARIABLE GLOBAL
                                    GlobalSingleton.userName = user.getDisplayName();
                                    //MENSAJE DE BIENVENIDA
                                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}