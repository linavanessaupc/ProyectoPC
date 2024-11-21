package co.edu.unipiloto.loginRegisterPetConnect.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.MensajeViewHolder> {

    private List<Mensaje> listaMensajes;
    private Context context;

    public MensajeAdapter(List<Mensaje> listaMensajes, Context context) {
        this.listaMensajes = listaMensajes;
        this.context = context;
    }

    @NonNull
    @Override
    public MensajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mensaje, parent, false);
        return new MensajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MensajeViewHolder holder, int position) {
        Mensaje mensaje = listaMensajes.get(position);
        holder.textoMensaje.setText(mensaje.getTexto());
        holder.fechaMensaje.setText(formatTimestamp(mensaje.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    private String formatTimestamp(long timestamp) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date(timestamp));
    }

    public static class MensajeViewHolder extends RecyclerView.ViewHolder {
        TextView textoMensaje, fechaMensaje;

        public MensajeViewHolder(@NonNull View itemView) {
            super(itemView);
            textoMensaje = itemView.findViewById(R.id.textoMensaje);
            fechaMensaje = itemView.findViewById(R.id.fechaMensaje);
        }
    }
}

