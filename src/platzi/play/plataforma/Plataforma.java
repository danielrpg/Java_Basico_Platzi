package platzi.play.plataforma;

import platzi.play.contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>(); // Esta es la forma de inicializar un List
    }

    public void mostrarTitulos() {
        for(Pelicula pelicula : contenido) {
            System.out.println(pelicula.getTitulo());
        }
    }

    public void eliminar(Pelicula pelicula) {
        this.contenido.remove(pelicula);
    }

    public Pelicula buscarPorTitulo(String titulo) {
        for (Pelicula pelicula : contenido) {
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
                return pelicula;
            }
        }
        return null;
    }

    public void agregar(Pelicula pelicula) {
        this.contenido.add(pelicula);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
