package platzi.play.contenido;

public class Pelicula extends Contenido {
    public Pelicula(String titulo, int duracion, Genero genero, Idioma idioma, Calidad calidad, double calificacion) {
        super(titulo, duracion, genero, idioma, calidad, calificacion); //con esta instruction le decimos que use la clase padre
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo la pelicula " + this.getTitulo());
    }

    @Override
    public String obtenerFechaTecnica() {
        return this.getTitulo() + " ("+ this.getFechaEstreno().getYear() + ") \n" +
                "Genero: " + this.getGenero() + "\n"+
                "Calificacion: " + this.getCalificacion() + "/5\n" +
                "Duracion: " + this.getDuracion() + " minutos";
    }
}
