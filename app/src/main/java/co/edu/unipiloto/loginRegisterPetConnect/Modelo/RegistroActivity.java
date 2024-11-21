package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class RegistroActivity extends AppCompatActivity {

    EditText etUsername, etEmail, etPassword, etConfirmPassword;
    Button btnRegister;
    TextView tvGoToLogin;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();


                if (validarCampos(username, email, password, confirmPassword)) {
                    registrarUsuario(email, password, username);
                }
            }
        });

        tvGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validarCampos(String username, String email, String password, String confirmPassword) {
        boolean valido = true;

        if (username.isEmpty()) {
            etUsername.setError("El nombre de usuario es obligatorio");
            valido = false;
        }

        if (email.isEmpty()) {
            etEmail.setError("El email es obligatorio");
            valido = false;
        }

        if (password.isEmpty()) {
            etPassword.setError("La contraseña es obligatoria");
            valido = false;
        }

        if (confirmPassword.isEmpty()) {
            etConfirmPassword.setError("La confirmación de contraseña es obligatoria");
            valido = false;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Las contraseñas no coinciden");
            valido = false;
        }

        return valido;
    }

    private void registrarUsuario(String email, String password, String username) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            guardarUsuarioEnFirestore(user.getUid(), email, username, password);
                        }
                    } else {
                        Toast.makeText(RegistroActivity.this, "Error al registrar: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void guardarUsuarioEnFirestore(String userId, String email, String username, String password) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("username", username);
        user.put("password", password);

        db.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(RegistroActivity.this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(RegistroActivity.this, "Error al guardar los datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}