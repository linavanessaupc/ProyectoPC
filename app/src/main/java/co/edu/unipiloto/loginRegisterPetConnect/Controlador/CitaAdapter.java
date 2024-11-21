package co.edu.unipiloto.loginRegisterPetConnect.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.CitaViewHolder> {

    private List<Cita> listaCitas;
    private Context context;

    public CitaAdapter(List<Cita> listaCitas, Context context) {
        this.listaCitas = listaCitas;
        this.context = context;
    }

    @NonNull
    @Override
    public CitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cita, parent, false);
        return new CitaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitaViewHolder holder, int position) {
        Cita cita = listaCitas.get(position);
        holder.nombreMascota.setText(cita.getNombreMascota());
        holder.fechaHora.setText(cita.getFechaCita() + " " + cita.getHoraCita());
        holder.motivo.setText(cita.getMotivoCita());
    }

    @Override
    public int getItemCount() {
        return listaCitas.size();
    }

    public static class CitaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreMascota, fechaHora, motivo;

        public CitaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreMascota = itemView.findViewById(R.id.tvNombreMascota);
            fechaHora = itemView.findViewById(R.id.tvFechaHora);
            motivo = itemView.findViewById(R.id.tvMotivo);
        }
    }
}

