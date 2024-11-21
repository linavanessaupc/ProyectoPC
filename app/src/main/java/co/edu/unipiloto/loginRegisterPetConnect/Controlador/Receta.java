package co.edu.unipiloto.loginRegisterPetConnect.Controlador;

public class Receta {
    private String titulo;
    private String descripcion;
    private String ingredientes;
    private String pasos;

    public Receta() {
        // Constructor vacío requerido para Firestore
    }

    public Receta(String titulo, String descripcion, String ingredientes, String pasos) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }
}

