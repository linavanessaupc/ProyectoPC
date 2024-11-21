package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import co.edu.unipiloto.loginRegisterPetConnect.Controlador.Mensaje;
import co.edu.unipiloto.loginRegisterPetConnect.Controlador.MensajeAdapter;
import co.edu.unipiloto.loginRegisterPetConnect.R;

public class ForoComunidadActivity extends AppCompatActivity {

    private EditText etMensaje;
    private Button btnEnviar;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private CollectionReference foroRef;

    private List<Mensaje> listaMensajes;
    private MensajeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro_comunidad);

        etMensaje = findViewById(R.id.etMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);
        recyclerView = findViewById(R.id.recyclerViewForo);
        progressBar = findViewById(R.id.progressBarForo);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        foroRef = db.collection("foro");

        listaMensajes = new ArrayList<>();
        adapter = new MensajeAdapter(listaMensajes, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        cargarMensajes();

        btnEnviar.setOnClickListener(v -> enviarMensaje());
    }

    private void cargarMensajes() {
        progressBar.setVisibility(View.VISIBLE);

        foroRef.orderBy("timestamp", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);

                    if (task.isSuccessful() && task.getResult() != null) {
                        listaMensajes.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            Mensaje mensaje = doc.toObject(Mensaje.class);
                            listaMensajes.add(mensaje);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error al cargar mensajes", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void enviarMensaje() {
        String texto = etMensaje.getText().toString().trim();
        if (TextUtils.isEmpty(texto)) {
            etMensaje.setError("Escribe un mensaje");
            return;
        }

        String userId = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : "Anónimo";
        Mensaje mensaje = new Mensaje(userId, texto, System.currentTimeMillis());

        foroRef.add(mensaje)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
                    etMensaje.setText("");
                    cargarMensajes();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error al enviar mensaje", Toast.LENGTH_SHORT).show());
    }
}
