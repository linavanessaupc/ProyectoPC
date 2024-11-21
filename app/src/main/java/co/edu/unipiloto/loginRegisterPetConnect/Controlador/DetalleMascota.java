package co.edu.unipiloto.loginRegisterPetConnect.Controlador;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class DetalleMascota extends AppCompatActivity {
    private TextView textViewNombreMascota;
    private TextView textViewTipoMascota;
    private TextView textViewRazaMascota;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        textViewNombreMascota = findViewById(R.id.textViewNombreMascota);
        textViewTipoMascota = findViewById(R.id.textViewTipoMascota);
        textViewRazaMascota = findViewById(R.id.textViewRazaMascota);

        db = FirebaseFirestore.getInstance();

        String mascotaId = getIntent().getStringExtra("mascotaId");
        if (mascotaId != null) {
            cargarDetalleMascota(mascotaId);
        }
    }

    private void cargarDetalleMascota(String mascotaId) {
        db.collection("mascotas").document(mascotaId).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                Mascota mascota = documentSnapshot.toObject(Mascota.class);
                if (mascota != null) {
                    textViewNombreMascota.setText(mascota.getNombre());
                    textViewTipoMascota.setText(String.valueOf(mascota.getTipo()));
                    textViewRazaMascota.setText(mascota.getRaza());
                }
            }
        });
    }
}

