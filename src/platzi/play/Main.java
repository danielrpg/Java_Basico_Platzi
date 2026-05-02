package platzi.play;

import platzi.play.contenido.*;
import platzi.play.contenido.Calidad;
import platzi.play.contenido.Genero;
import platzi.play.contenido.Idioma;
import platzi.play.contenido.Pelicula;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.ScannerUtils;
import java.util.List;

public class Main {
    public static final String VERSION = "1.0.1";
    public static final String NOMBRE = "Platzi Play";
    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int VER_POPULARES_MAYORES_A_4 = 6;
    public static final int PELICULA_MAS_LARGA = 7;
    public static final int PELICULA_MAS_CORTA = 8;
    public static final int REPRODUCIR = 9;
    public static final int ELIMINAR = 10;
    public static final int SALIR = 11;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE);
        System.out.println(NOMBRE + " v" + VERSION);

        cargarPeliculas(plataforma);
        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de contenido! \n");

        while(true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    INGRESE UNA DE LAS SIGUIENTES OPCIONES:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver populares
                    6. Ver populares mayores a 4
                    7. Pelicula mas larga
                    8. Pelicula mas corta
                    9. Reproducir
                    10. Eliminar
                    11. Salir
                    """);
            System.out.println("Opcion elegida: " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    Idioma idioma = ScannerUtils.capturarIdioma("Idioma del Contenido");
                    Calidad calida = ScannerUtils.capturarCalidad("Calidad del Contenido");
                    double calificacion = ScannerUtils.capturarDouble("Calificacion del contenido");
                    Pelicula pelicula = new Pelicula(nombre, duracion, genero, idioma, calida, calificacion);

                    try {
                        plataforma.agregar(pelicula);
                    } catch (PeliculaExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case MOSTRAR_TODO -> {
                    List<ResumenContenido> contenidosResumidos = plataforma.getResumencontenido();
                    contenidosResumidos.forEach(resumen -> System.out.println(resumen.toString()));
                }
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
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    List<Pelicula> peliculasPorGenero = plataforma.buscarPorGenero(genero);
                    System.out.println(peliculasPorGenero.size() + " peliculas del contenido");

                    peliculasPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFechaTecnica() + "\n"));
                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("Cantidad del contenido a mostrar");

                    List<Pelicula> contenidoPupulares = plataforma.getPopulares(cantidad);
                    contenidoPupulares.forEach(contenido -> System.out.println(contenido.obtenerFechaTecnica() + "\n"));
                }
                case VER_POPULARES_MAYORES_A_4 -> {
                    List<Pelicula> contenidoPupularesMayoresA4 = plataforma.getPopularesMayoresA4();
                    contenidoPupularesMayoresA4.forEach(contenido -> System.out.println(contenido.obtenerFechaTecnica() + "\n"));
                }
                case PELICULA_MAS_LARGA -> {
                    Pelicula peliculaMasLarga = plataforma.getPeliculaMasLarga();
                    System.out.println(peliculaMasLarga.obtenerFechaTecnica() + "\n\n");
                }
                case PELICULA_MAS_CORTA -> {
                    Pelicula peliculaMasCorta = plataforma.getPeliculaMasCorta();
                    System.out.println(peliculaMasCorta.obtenerFechaTecnica() + "\n\n");
                }
                case REPRODUCIR -> {
                    String nombreAVer = ScannerUtils.capturarTexto("Nombre del contenido a reproducir");
                    Pelicula contenido = plataforma.buscarPorTitulo(nombreAVer);
                    if (contenido != null) {
                        plataforma.reproducir(contenido);
                    } else {
                        System.out.println(nombreAVer + " no existe dentro de la "+ plataforma.getNombre());
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
        plataforma.agregar(new Pelicula("Shrek", 90, Genero.ANIMADA, Idioma.INGLES, Calidad.HD));
        plataforma.agregar(new Pelicula("Inception", 148, Genero.ANIMADA, Idioma.INGLES, Calidad.BAJA));
        plataforma.agregar(new Pelicula("John Wick", 101, Genero.ACCION, Idioma.ESPANOL, Calidad.ALTA, 4.6));
        plataforma.agregar(new Pelicula("EL conjuro", 190, Genero.TERROR, Idioma.ESPANOL, Calidad.ALTA,3.5));
        plataforma.agregar(new Pelicula("coco", 190, Genero.ANIMADA, Idioma.ESPANOL, Calidad.ALTA,4.7));
        plataforma.agregar(new Pelicula("Joker", 190, Genero.DRAMA, Idioma.ESPANOL, Calidad.ALTA,5));
        plataforma.agregar(new Pelicula("Avengers: Endgame", 181, Genero.ACCION, Idioma.ESPANOL, Calidad.ALTA,4.2));
        plataforma.agregar(new Pelicula("Interstellar", 181, Genero.CIENCIA_FICCION, Idioma.ESPANOL, Calidad.ALTA,4.5));
    }
}
