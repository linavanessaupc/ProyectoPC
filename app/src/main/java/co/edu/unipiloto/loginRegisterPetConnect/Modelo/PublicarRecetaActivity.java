package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class PublicarRecetaActivity extends AppCompatActivity {

    private EditText etTituloReceta, etIngredientes, etPreparacion;
    private Button btnPublicarReceta;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_receta);

        etTituloReceta = findViewById(R.id.etTituloReceta);
        etIngredientes = findViewById(R.id.etIngredientes);
        etPreparacion = findViewById(R.id.etPreparacion);
        btnPublicarReceta = findViewById(R.id.btnPublicarReceta);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnPublicarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = etTituloReceta.getText().toString().trim();
                String ingredientes = etIngredientes.getText().toString().trim();
                String preparacion = etPreparacion.getText().toString().trim();

                if (TextUtils.isEmpty(titulo) || TextUtils.isEmpty(ingredientes) || TextUtils.isEmpty(preparacion)) {
                    Toast.makeText(PublicarRecetaActivity.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                publicarReceta(titulo, ingredientes, preparacion);
            }
        });
    }

    private void publicarReceta(String titulo, String ingredientes, String preparacion) {
        if (mAuth.getCurrentUser() != null) {
            String userId = mAuth.getCurrentUser().getUid();

            Map<String, Object> receta = new HashMap<>();
            receta.put("titulo", titulo);
            receta.put("ingredientes", ingredientes);
            receta.put("preparacion", preparacion);
            receta.put("userId", userId);

            db.collection("recetas")
                    .add(receta)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(PublicarRecetaActivity.this, "Receta publicada con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(PublicarRecetaActivity.this, "Error al publicar receta: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "Debe iniciar sesión para publicar una receta", Toast.LENGTH_SHORT).show();
        }
    }
}

