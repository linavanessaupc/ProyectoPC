package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import co.edu.unipiloto.loginRegisterPetConnect.Controlador.Mascota;
import co.edu.unipiloto.loginRegisterPetConnect.Controlador.MascotaAdapter;
import co.edu.unipiloto.loginRegisterPetConnect.R;

public class ConsultarMascotaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MascotaAdapter mascotaAdapter;
    private List<Mascota> listaMascotas;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_mascota);

        recyclerView = findViewById(R.id.recyclerViewMascotas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaMascotas = new ArrayList<>();
        mascotaAdapter = new MascotaAdapter(listaMascotas, this);
        recyclerView.setAdapter(mascotaAdapter);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        obtenerMascotas();
    }

    private void obtenerMascotas() {
        FirebaseUser usuarioActual = mAuth.getCurrentUser();

        if (usuarioActual != null) {
            String userId = usuarioActual.getUid();

            db.collection("mascotas")
                    .whereEqualTo("userId", userId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Mascota mascota = document.toObject(Mascota.class);
                                listaMascotas.add(mascota);
                            }
                            mascotaAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ConsultarMascotaActivity.this, "Error al obtener las mascotas", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }
    }

}
