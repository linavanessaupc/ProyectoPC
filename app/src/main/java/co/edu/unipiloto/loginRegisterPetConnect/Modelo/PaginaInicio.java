package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class PaginaInicio extends AppCompatActivity {
    Button btnRegistrarMascota;
    Button btnConsultarMascota;
    Button btnVerPerfil;
    Button btnAjustes;
    Button btnCerrarSesion;
    Button btnBuscarVeterinario;
    Button btnAgendaCitas;
    Button btnRecetasComida;
    Button btnForoComunidad;
    Button btnRecordatorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginainicio);

        btnRegistrarMascota = findViewById(R.id.btnRegistrarMascota);
        btnConsultarMascota = findViewById(R.id.btnConsultarMascotas);
        btnVerPerfil = findViewById(R.id.btnVerPerfil);
        btnAjustes = findViewById(R.id.btnAjustes);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnBuscarVeterinario = findViewById(R.id.btnBuscarVeterinario);
        btnAgendaCitas = findViewById(R.id.btnAgendaCitas);
        btnRecetasComida = findViewById(R.id.btnRecetasComida);
        btnForoComunidad = findViewById(R.id.btnForoComunidad);
        btnRecordatorios = findViewById(R.id.btnRecordatorios);

        btnRegistrarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaInicio.this, RegistrarMascota.class);
                startActivity(intent);
            }
        });

        btnConsultarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaInicio.this, ConsultarMascotaActivity.class);
                startActivity(intent);
            }
        });

        btnVerPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaInicio.this, VerPerfilActivity.class);
                startActivity(intent);
            }
        });

        btnAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaInicio.this, AjustesActivity.class);
                startActivity(intent);
            }
        });

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBuscarVeterinario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaInicio.this, EncontrarVeterinario.class);
                startActivity(intent);
            }
        });

        btnAgendaCitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaInicio.this, AgendaCitasActivity.class);
                startActivity(intent);
            }
        });

        btnRecetasComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaInicio.this, RecetasComidaActivity.class);
                startActivity(intent);
            }
        });

        btnForoComunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaInicio.this, ForoComunidadActivity.class);
                startActivity(intent);
            }
        });

        btnRecordatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaInicio.this, RecordatoriosActivity.class);
                startActivity(intent);
            }
        });
    }
}

