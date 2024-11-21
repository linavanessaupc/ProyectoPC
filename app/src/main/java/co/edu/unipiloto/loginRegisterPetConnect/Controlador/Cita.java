package co.edu.unipiloto.loginRegisterPetConnect.Controlador;

public class Cita {
    private String nombreMascota;
    private String fechaCita;
    private String horaCita;
    private String motivoCita;

    public Cita() {
        // Constructor vacío requerido para Firebase
    }

    public Cita(String nombreMascota, String fechaCita, String horaCita, String motivoCita) {
        this.nombreMascota = nombreMascota;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.motivoCita = motivoCita;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }
}

