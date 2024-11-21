package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class DetalleRecetaActivity extends AppCompatActivity {

    private TextView tvTitulo, tvDescripcion, tvIngredientes, tvPasos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_receta);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvIngredientes = findViewById(R.id.tvIngredientes);
        tvPasos = findViewById(R.id.tvPasos);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tvTitulo.setText(extras.getString("titulo"));
            tvDescripcion.setText(extras.getString("descripcion"));
            tvIngredientes.setText(extras.getString("ingredientes"));
            tvPasos.setText(extras.getString("pasos"));
        }
    }
}
