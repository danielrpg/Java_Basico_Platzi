package platzi.play.contenido;

public class Pelicula extends Contenido {
    public Pelicula(String titulo, int duracion, Genero genero, Idioma idioma, Calidad calidad, double calificacion) {
        super(titulo, duracion, genero, idioma, calidad, calificacion); //con esta instruction le decimos que use la clase padre
    }
}
