package co.edu.unipiloto.loginRegisterPetConnect.Controlador;

public class Mensaje {
    private String userId;
    private String texto;
    private long timestamp;

    public Mensaje() {
        // Constructor vacío requerido para Firestore
    }

    public Mensaje(String userId, String texto, long timestamp) {
        this.userId = userId;
        this.texto = texto;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

