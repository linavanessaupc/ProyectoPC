package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import co.edu.unipiloto.loginRegisterPetConnect.Controlador.Receta;
import co.edu.unipiloto.loginRegisterPetConnect.Controlador.RecetaAdapter;
import co.edu.unipiloto.loginRegisterPetConnect.R;

public class RecetasComidaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button btnNuevaReceta;
    private RecetaAdapter adapter;
    private List<Receta> listaRecetas;

    private FirebaseFirestore db;
    private CollectionReference recetasRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas_comida);

        // Vinculación de vistas
        recyclerView = findViewById(R.id.recyclerViewRecetas);
        progressBar = findViewById(R.id.progressBarRecetas);
        btnNuevaReceta = findViewById(R.id.btnNuevaReceta);

        // Configuración de Firebase
        db = FirebaseFirestore.getInstance();
        recetasRef = db.collection("recetas");

        // Configuración del RecyclerView
        listaRecetas = new ArrayList<>();
        adapter = new RecetaAdapter(listaRecetas, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Cargar recetas desde Firestore
        cargarRecetas();

        // Navegar a la actividad para publicar recetas
        btnNuevaReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecetasComidaActivity.this, PublicarRecetaActivity.class);
                startActivity(intent);
            }
        });

    }

    private void cargarRecetas() {
        progressBar.setVisibility(View.VISIBLE);

        recetasRef.get()
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);

                    if (task.isSuccessful() && task.getResult() != null) {
                        listaRecetas.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            Receta receta = doc.toObject(Receta.class);
                            if (receta != null) {
                                listaRecetas.add(receta);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error al cargar recetas", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
