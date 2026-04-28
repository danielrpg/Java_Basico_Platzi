package platzi.play.contenido;

import java.time.LocalDate; // Es muy util para trabajar con zonas horarias

public class Pelicula {
    private String titulo; // tipo String sino se asigna simpre va ser null
    private String description; // igual aqui
    private int duracion; // valores enteros -> valor por defecto 0
    private Genero genero; // Aqui tambien
    private LocalDate fechaEstreno;
    private double calificacion; // valores decimales
    private boolean disponible; // boolean true o false -> valor por defecto false

    public Pelicula(String titulo, int duracion, Genero genero) {
        this.titulo = titulo; // aqui inicializamos los datos con el valor que viene de parametro
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponible = true;
    }

    public Pelicula(String titulo, int duracion, Genero genere, double calificacion) {
        this(titulo, duracion, genere); // reasigna usando el anterior constructor
        this.calificar(calificacion);
    }

    public void reproducir() {
        System.out.println("Reproducir " + titulo);
    }

    public String obtenerFechaTecnica() {
        return titulo + " ("+ fechaEstreno.getYear() + ") \n" +
                "Genero: " + genero + "\n"+
                "Calificacion: " + calificacion + "/5";
    }

    public void calificar(double calificacion) {
        if (calificacion >= 0 && calificacion <= 5) {
            this.calificacion = calificacion;
        }
    }

    public boolean esPopular() {
        return calificacion >= 5 && calificacion <= 10;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public Genero getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getDescription() {
        return description;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
