package co.edu.unipiloto.loginRegisterPetConnect.Controlador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.unipiloto.loginRegisterPetConnect.Modelo.DetalleRecetaActivity;
import co.edu.unipiloto.loginRegisterPetConnect.R;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder> {

    private List<Receta> listaRecetas;
    private Context context;

    public RecetaAdapter(List<Receta> listaRecetas, Context context) {
        this.listaRecetas = listaRecetas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        return new RecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
        Receta receta = listaRecetas.get(position);
        holder.tituloReceta.setText(receta.getTitulo());
        holder.descripcionReceta.setText(receta.getDescripcion());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleRecetaActivity.class);
            intent.putExtra("titulo", receta.getTitulo());
            intent.putExtra("descripcion", receta.getDescripcion());
            intent.putExtra("ingredientes", receta.getIngredientes());
            intent.putExtra("pasos", receta.getPasos());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    public static class RecetaViewHolder extends RecyclerView.ViewHolder {
        TextView tituloReceta, descripcionReceta;

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloReceta = itemView.findViewById(R.id.tituloReceta);
            descripcionReceta = itemView.findViewById(R.id.descripcionReceta);
        }
    }
}
