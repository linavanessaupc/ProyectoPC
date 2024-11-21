package co.edu.unipiloto.loginRegisterPetConnect.Controlador;

public class Mascota {
    private String id;
    private String nombre;
    private String tipo;
    private String raza;

    public Mascota() {
        // Constructor vacío para Firebase
    }

    public Mascota(String nombre, String tipo, String raza) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
