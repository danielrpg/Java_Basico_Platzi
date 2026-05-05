package platzi.play.plataforma;

import platzi.play.contenido.*;
import platzi.play.excepcion.PeliculaExistenteException;

import java.util.*;

public class Plataforma {
    private String nombre;
    private List<Contenido> contenido;
    private Map<Contenido, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>(); // Esta es la forma de inicializar un List
        this.visualizaciones = new HashMap<>();
    }

    public void reproducir(Contenido contenido) {
        int contenidoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido " + contenidoActual + " veces. ");

        this.contarVisualizacion(contenido);
        contenido.reproducir();
    }

    private void contarVisualizacion(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual + 1);
    }

    public List<String> getTitulos() {
        return contenido.stream()
                .map(Contenido::getTitulo)
                .toList();
    }

    public List<ResumenContenido> getResumencontenido() {
        return contenido.stream()
                .map(c -> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero()))
                .toList();
    }

    public void eliminar(Contenido contenido) {
        this.contenido.remove(contenido);
    }

    public Contenido buscarPorTitulo(String titulo) {
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public int getDuracionTotal() {
        return contenido.stream()
                .mapToInt(Contenido::getDuracion)
                .sum();
    }

    public List<Contenido> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public List<Contenido> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    public List<Pelicula> getPeliculas() {
        return contenido.stream()
                .filter(contenido -> contenido instanceof Pelicula)
                .map(contenido -> (Pelicula) contenido)
                .toList();
    }

    public List<Documental> getDocumentales() {
        return contenido.stream()
                .filter(contenido -> contenido instanceof Documental)
                .map(contenido -> (Documental) contenido)
                .toList();
    }

    public List<Contenido> getPopularesMayoresA4() {
        return contenido.stream()
                .filter(contenido -> contenido.getCalificacion() >= 4)
                .toList();
    }

    public Contenido getPeliculaMasLarga() {
        return contenido.stream()
                .sorted(Comparator.comparing(Contenido::getDuracion).reversed())
                .findFirst()
                .get();
    }

    public Contenido getPeliculaMasCorta() {
        return contenido.stream()
                .sorted(Comparator.comparing(Contenido::getDuracion))
                .findFirst()
                .get();
    }

    public void agregar(Contenido pelicula) {
        Contenido contenido = this.buscarPorTitulo(pelicula.getTitulo());
        if (contenido != null) {
            throw new PeliculaExistenteException(pelicula.getTitulo());
        }
        this.contenido.add(pelicula);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }
}
