package platzi.play.plataforma;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.PeliculaExistenteException;

import java.util.*;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;
    private Map<Pelicula, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>(); // Esta es la forma de inicializar un List
        this.visualizaciones = new HashMap<>();
    }

    public void reproducir(Pelicula contenido) {
        int contenidoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido " + contenidoActual + " veces. ");

        this.contarVisualizacion(contenido);
        contenido.reproducir();
    }

    private void contarVisualizacion(Pelicula pelicula) {
        int conteoActual = visualizaciones.getOrDefault(pelicula, 0);
        visualizaciones.put(pelicula, conteoActual + 1);
    }

    public List<String> getTitulos() {
        return contenido.stream()
                .map(Pelicula::getTitulo)
                .toList();
    }

    public List<ResumenContenido> getResumencontenido() {
        return contenido.stream()
                .map(c -> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero()))
                .toList();
    }

    public void eliminar(Pelicula pelicula) {
        this.contenido.remove(pelicula);
    }

    public Pelicula buscarPorTitulo(String titulo) {
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public int getDuracionTotal() {
        return contenido.stream()
                .mapToInt(Pelicula::getDuracion)
                .sum();
    }

    public List<Pelicula> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public List<Pelicula> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    public List<Pelicula> getPopularesMayoresA4() {
        return contenido.stream()
                .filter(contenido -> contenido.getCalificacion() >= 4)
                .toList();
    }

    public Pelicula getPeliculaMasLarga() {
        return contenido.stream()
                .sorted(Comparator.comparing(Pelicula::getDuracion).reversed())
                .findFirst()
                .get();
    }

    public Pelicula getPeliculaMasCorta() {
        return contenido.stream()
                .sorted(Comparator.comparing(Pelicula::getDuracion))
                .findFirst()
                .get();
    }

    public void agregar(Pelicula pelicula) {
        Pelicula contenido = this.buscarPorTitulo(pelicula.getTitulo());
        if (contenido != null) {
            throw new PeliculaExistenteException(pelicula.getTitulo());
        }
        this.contenido.add(pelicula);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
