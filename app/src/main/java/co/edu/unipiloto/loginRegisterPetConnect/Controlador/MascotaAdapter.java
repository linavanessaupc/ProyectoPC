package co.edu.unipiloto.loginRegisterPetConnect.Controlador;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private List<Mascota> listaMascotas;
    private Context context;

    public MascotaAdapter(List<Mascota> listaMascotas, Context context) {
        this.listaMascotas = listaMascotas;
        this.context = context;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mascota, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        Mascota mascota = listaMascotas.get(position);
        holder.nombreMascota.setText(mascota.getNombre());
        holder.tipoMascota.setText(mascota.getTipo());
        holder.razaMascota.setText(mascota.getRaza());

        holder.buttonVerDetalle.setOnClickListener(v -> {
            if (context != null) {
                Intent intent = new Intent(context, DetalleMascota.class);
                intent.putExtra("nombre", mascota.getNombre());
                intent.putExtra("tipo", mascota.getTipo());
                intent.putExtra("raza", mascota.getRaza());
                context.startActivity(intent);
            } else {
                Log.e("MascotaAdapter", "Context is null");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaMascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreMascota, tipoMascota, razaMascota;
        Button buttonVerDetalle;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreMascota = itemView.findViewById(R.id.nombreMascota);
            tipoMascota = itemView.findViewById(R.id.tipoMascota);
            razaMascota = itemView.findViewById(R.id.razaMascota);
            buttonVerDetalle = itemView.findViewById(R.id.buttonVerDetalle);
        }
    }
}
