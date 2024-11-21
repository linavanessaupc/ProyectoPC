package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unipiloto.loginRegisterPetConnect.Controlador.User;
import co.edu.unipiloto.loginRegisterPetConnect.R;

public class VerPerfilActivity extends AppCompatActivity {

    private TextView textViewUsername, textViewEmail, textViewUserId;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);

        // Inicializar Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Conectar elementos del layout
        textViewUsername = findViewById(R.id.textViewUsernamePerfil);
        textViewEmail = findViewById(R.id.textViewEmailPerfil);
        textViewUserId = findViewById(R.id.textViewUserIdPerfil);

        // Obtener usuario actual
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            cargarDatosUsuario(userId);
        } else {
            Toast.makeText(this, "No hay ningún usuario conectado", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void cargarDatosUsuario(String userId) {
        db.collection("users").document(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document != null && document.exists()) {
                    User usuario = document.toObject(User.class);
                    if (usuario != null) {
                        textViewUsername.setText(usuario.getUsername());
                        textViewEmail.setText(usuario.getEmail());
                        textViewUserId.setText(userId);
                    }
                } else {
                    Toast.makeText(this, "No se encontraron datos del usuario", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Error al obtener los datos del usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
