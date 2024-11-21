package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class EncontrarVeterinario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encontrar_veterinario);
    }

    public void onShowLocationChapinero(View view) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=4.653908,-74.062331(Veterinaria+Chapinero,+Bogotá)");
        openMap(gmmIntentUri);
    }

    public void onShowLocationUsaquen(View view) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=4.706376,-74.030625(Veterinaria+Usaquén,+Bogotá)");
        openMap(gmmIntentUri);
    }

    public void onShowLocationSuba(View view) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=4.748947,-74.107995(Veterinaria+Suba,+Bogotá)");
        openMap(gmmIntentUri);
    }

    public void onShowLocationTeusaquillo(View view) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=4.637056,-74.084160(Veterinaria+Teusaquillo,+Bogotá)");
        openMap(gmmIntentUri);
    }

    public void onShowLocationNY(View view) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=40.730610,-73.935242(Veterinaria+Nueva+York,+EE.UU)");
        openMap(gmmIntentUri);
    }

    private void openMap(Uri uri) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        try {
            startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Por favor, instala una aplicación de mapas", Toast.LENGTH_LONG).show();
        }
    }
}
