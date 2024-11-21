package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;

import co.edu.unipiloto.loginRegisterPetConnect.Controlador.Cita;
import co.edu.unipiloto.loginRegisterPetConnect.R;

public class AgendaCitasActivity extends AppCompatActivity {
    private EditText edtNombreMascota, edtFechaCita, edtHoraCita, edtMotivoCita;
    private Button btnAgendarCita;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_cita);

        edtNombreMascota = findViewById(R.id.edtNombreMascota);
        edtFechaCita = findViewById(R.id.edtFechaCita);
        edtHoraCita = findViewById(R.id.edtHoraCita);
        edtMotivoCita = findViewById(R.id.edtMotivoCita);
        btnAgendarCita = findViewById(R.id.btnAgendarCita);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        btnAgendarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreMascota = edtNombreMascota.getText().toString().trim();
                String fechaCita = edtFechaCita.getText().toString().trim();
                String horaCita = edtHoraCita.getText().toString().trim();
                String motivoCita = edtMotivoCita.getText().toString().trim();

                if (nombreMascota.isEmpty() || fechaCita.isEmpty() || horaCita.isEmpty() || motivoCita.isEmpty()) {
                    Toast.makeText(AgendaCitasActivity.this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mAuth.getCurrentUser() != null) {
                    Cita cita = new Cita(nombreMascota, fechaCita, horaCita, motivoCita);
                    CollectionReference citasRef = db.collection("citas");
                    citasRef.add(cita)
                            .addOnSuccessListener(documentReference -> {
                                Toast.makeText(AgendaCitasActivity.this, "Cita agendada correctamente.", Toast.LENGTH_SHORT).show();
                                finish();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(AgendaCitasActivity.this, "Error al agendar la cita: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                } else {
                    Toast.makeText(AgendaCitasActivity.this, "Por favor inicie sesión primero.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}




