package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class RegistrarMascota extends AppCompatActivity {

    EditText etNombreMascota, etRazaMascota, etPesoMascota, etEdadMascota;
    Button btnGuardarMascota;
    private FirebaseAuth auth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etNombreMascota = findViewById(R.id.etNombreMascota);
        etRazaMascota = findViewById(R.id.etRazaMascota);
        etPesoMascota = findViewById(R.id.etPesoMascota);
        etEdadMascota = findViewById(R.id.etEdadMascota);
        btnGuardarMascota = findViewById(R.id.btnGuardarMascota);

        btnGuardarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombreMascota.getText().toString();
                String raza = etRazaMascota.getText().toString();
                String peso = etPesoMascota.getText().toString();
                String edad = etEdadMascota.getText().toString();

                if (!nombre.isEmpty() && !raza.isEmpty() && !peso.isEmpty() && !edad.isEmpty()) {
                    guardarMascota(nombre, raza, peso, edad);
                } else {
                    Toast.makeText(RegistrarMascota.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void guardarMascota(String nombre, String raza, String peso, String edad) {
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            Map<String, Object> mascota = new HashMap<>();
            mascota.put("nombre", nombre);
            mascota.put("raza", raza);
            mascota.put("peso", peso);
            mascota.put("edad", edad);
            mascota.put("userId", user.getUid());

            db.collection("mascotas")
                    .add(mascota)
                    .addOnSuccessListener(documentReference -> {
                        String mascotaId = documentReference.getId();
                        mascota.put("id", mascotaId);

                        db.collection("mascotas").document(mascotaId).set(mascota)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(RegistrarMascota.this, "Mascota registrada exitosamente", Toast.LENGTH_SHORT).show();
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(RegistrarMascota.this, "Error al actualizar la mascota con ID: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(RegistrarMascota.this, "Error al registrar la mascota: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(RegistrarMascota.this, "No se ha podido identificar al usuario", Toast.LENGTH_SHORT).show();
        }
    }

}
