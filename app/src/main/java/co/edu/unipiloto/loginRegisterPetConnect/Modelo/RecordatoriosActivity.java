package co.edu.unipiloto.loginRegisterPetConnect.Modelo;


import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.edu.unipiloto.loginRegisterPetConnect.Controlador.Cita;
import co.edu.unipiloto.loginRegisterPetConnect.Controlador.CitaAdapter;
import co.edu.unipiloto.loginRegisterPetConnect.R;

public class RecordatoriosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CitaAdapter adapter;
    private List<Cita> listaCitas;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);

        // Inicializar vista y variables
        recyclerView = findViewById(R.id.recyclerViewRecordatorios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaCitas = new ArrayList<>();
        adapter = new CitaAdapter(listaCitas, this);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        crearCanalNotificaciones();

        cargarCitas();
    }

    private void cargarCitas() {
        if (mAuth.getCurrentUser() != null) {
            db.collection("citas")
                    .whereEqualTo("userId", mAuth.getCurrentUser().getUid())
                    .get()
                    .addOnSuccessListener(querySnapshot -> {
                        listaCitas.clear();
                        for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                            Cita cita = doc.toObject(Cita.class);
                            if (cita != null) {
                                listaCitas.add(cita);
                                programarNotificacion(cita);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    })
                    .addOnFailureListener(e -> {
                        Log.e("RecordatoriosActivity", "Error al cargar las citas: ", e);
                        Toast.makeText(this, "Error al cargar citas.", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "Por favor inicie sesión primero.", Toast.LENGTH_SHORT).show();
        }
    }

    private void programarNotificacion(Cita cita) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date fechaHora = sdf.parse(cita.getFechaCita() + " " + cita.getHoraCita());
            if (fechaHora != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaHora);

                Intent intent = new Intent(this, NotificationReceiver.class);
                intent.putExtra("titulo", "Recordatorio de cita");
                intent.putExtra("mensaje", "Tienes una cita para " + cita.getMotivoCita() + " con " + cita.getNombreMascota());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        this, cita.hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                if (alarmManager != null) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
            }
        } catch (ParseException e) {
            Log.e("RecordatoriosActivity", "Error al parsear la fecha y hora: ", e);
        }
    }

    private void crearCanalNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "CITAS_CHANNEL",
                    "Notificaciones de Citas",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Canal para recordatorios de citas");

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }
}

