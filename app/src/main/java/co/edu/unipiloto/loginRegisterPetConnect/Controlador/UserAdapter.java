package co.edu.unipiloto.loginRegisterPetConnect.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.unipiloto.loginRegisterPetConnect.Controlador.User;
import co.edu.unipiloto.loginRegisterPetConnect.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsuarioViewHolder> {

    private List<User> listaUsuarios;
    private Context context;

    public UserAdapter(List<User> listaUsuarios, Context context) {
        this.listaUsuarios = listaUsuarios;
        this.context = context;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        User usuario = listaUsuarios.get(position);
        holder.textViewUsername.setText(usuario.getUsername());
        holder.textViewEmail.setText(usuario.getEmail());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUsername, textViewEmail;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }
}