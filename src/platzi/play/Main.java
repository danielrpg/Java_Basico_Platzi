package platzi.play;

import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Plataforma;
import platzi.play.plataforma.Usuario;
import platzi.play.util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// import java.util.Scanner;

public class Main {
    public static final String VERSION = "1.0.1";
    public static final String NOMBRE = "Platzi Play";
    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE);
        System.out.println(NOMBRE + " v" + VERSION);

        cargarPeliculas(plataforma);

        while(true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    INGRESE UNA DE LAS SIGUIENTES OPCIONES:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Buscar por genero
                    8. Eliminar
                    9. Salir
                    """);
            System.out.println("Opcion elegida: " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    String genero = ScannerUtils.capturarTexto("Genero del contenido");
                    double calificacion = ScannerUtils.capturarDouble("Calificacion del contenido");
                    Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);
                    plataforma.agregar(pelicula);
                }
                case MOSTRAR_TODO -> plataforma.mostrarTitulos();
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscar = ScannerUtils.capturarTexto("Nombre del contenido");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreBuscar);

                    if(pelicula != null) {
                        System.out.println(pelicula.obtenerFechaTecnica());
                    } else {
                        System.out.println(nombreBuscar + " no existe dentro de la "+ plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    String genero = ScannerUtils.capturarTexto("Genero del contenido");
                    List<Pelicula> peliculasPorGenero = plataforma.buscarPorGenero(genero);
                    System.out.println(peliculasPorGenero.size() + " peliculas del contenido");

                    if(peliculasPorGenero != null) {
                        peliculasPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFechaTecnica() + "\n"));
                    }
                }
                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturarTexto("Nombre del contenido a Eliminar");
                    Pelicula contenido = plataforma.buscarPorTitulo(nombreAEliminar);

                    if(contenido != null) {
                        plataforma.eliminar(contenido);
                        System.out.println(nombreAEliminar + " eliminado!");
                    } else {
                        System.out.println(nombreAEliminar + " no existe dentro de la "+ plataforma.getNombre());
                    }
                }
                case SALIR -> System.exit(0);

            }
        }
    }

    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.agregar(new Pelicula("Shrek", 90, "Animada"));
        plataforma.agregar(new Pelicula("Inception", 148, "Animada"));
        plataforma.agregar(new Pelicula("John Wick", 101, "Acción", 4.6));
        plataforma.agregar(new Pelicula("EL conjuro", 190, "Terror", 3.5));
        plataforma.agregar(new Pelicula("coco", 190, "Animada", 4.7));
        plataforma.agregar(new Pelicula("Joker", 190, "Drama", 5));
        plataforma.agregar(new Pelicula("Avengers: Endgame", 181, "Accion", 4.2));
    }
}
