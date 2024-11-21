package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class MainActivity extends AppCompatActivity{
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.barraProgressBar);
        progressBar.setVisibility(View.GONE);
        if (auth.getCurrentUser()!=null){
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Hola, prueba", Toast.LENGTH_SHORT).show();
        }

    }
    public void login(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void regristro(View view) {
        Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

}
