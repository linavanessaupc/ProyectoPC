package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class AjustesActivity extends AppCompatActivity {

    private Button btnCerrarSesion, btnEliminarCuenta;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Conectar los botones del layout
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnEliminarCuenta = findViewById(R.id.btnEliminarCuenta);

        // Acción para cerrar sesión
        btnCerrarSesion.setOnClickListener(v -> cerrarSesion());

        // Acción para eliminar la cuenta
        btnEliminarCuenta.setOnClickListener(v -> confirmarEliminacion());
    }

    private void cerrarSesion() {
        auth.signOut();
        Toast.makeText(AjustesActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AjustesActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void confirmarEliminacion() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar cuenta")
                .setMessage("¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.")
                .setPositiveButton("Eliminar", (dialog, which) -> eliminarCuenta())
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void eliminarCuenta() {
        if (auth.getCurrentUser() != null) {
            auth.getCurrentUser().delete().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(AjustesActivity.this, "Cuenta eliminada con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AjustesActivity.this, RegistroActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AjustesActivity.this, "Error al eliminar la cuenta: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

