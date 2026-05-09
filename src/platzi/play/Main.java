package platzi.play;

import platzi.play.contenido.*;
import platzi.play.contenido.Calidad;
import platzi.play.contenido.Genero;
import platzi.play.contenido.Idioma;
import platzi.play.contenido.Contenido;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.FileUtils;
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
    public static final int BUSCAR_POR_TIPO = 10;
    public static final int ELIMINAR = 11;
    public static final int SALIR = 12;

    static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE);
        System.out.println(NOMBRE + " v" + VERSION);

        cargarPeliculas(plataforma);
        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de contenido! \n");
        plataforma.getContenidoPromocionable().forEach(promocionable -> System.out.println(promocionable.promocionar()));

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
                    10. Buscar por tipo
                    11. Eliminar
                    12. Salir
                    """);
            System.out.println("Opcion elegida: " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("Que tipo de contenido quieres agregar? 1. Pelicula \n 2. Documental ");
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    Idioma idioma = ScannerUtils.capturarIdioma("Idioma del Contenido");
                    Calidad calida = ScannerUtils.capturarCalidad("Calidad del Contenido");
                    double calificacion = ScannerUtils.capturarDouble("Calificacion del contenido");

                    try {
                        Contenido contenido;
                        if (tipoDeContenido == 1) {
                            contenido = new Pelicula(nombre, duracion, genero, idioma, calida, calificacion);
                        } else {
                            String narrador = ScannerUtils.capturarTexto("Narrador del documental");
                            contenido = new Documental(nombre, duracion, genero, idioma, calida, calificacion, narrador);
                        }
                        FileUtils.escribirArchivo(contenido);
                        plataforma.agregar(contenido);
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
                    Contenido contenido = plataforma.buscarPorTitulo(nombreBuscar);

                    if(contenido != null) {
                        System.out.println(contenido.obtenerFechaTecnica());
                    } else {
                        System.out.println(nombreBuscar + " no existe dentro de la "+ plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    List<Contenido> peliculasPorGenero = plataforma.buscarPorGenero(genero);
                    System.out.println(peliculasPorGenero.size() + " peliculas del contenido");

                    peliculasPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFechaTecnica() + "\n"));
                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("Cantidad del contenido a mostrar");

                    List<Contenido> contenidoPupulares = plataforma.getPopulares(cantidad);
                    contenidoPupulares.forEach(contenido -> System.out.println(contenido.obtenerFechaTecnica() + "\n"));
                }
                case VER_POPULARES_MAYORES_A_4 -> {
                    List<Contenido> contenidoPupularesMayoresA4 = plataforma.getPopularesMayoresA4();
                    contenidoPupularesMayoresA4.forEach(contenido -> System.out.println(contenido.obtenerFechaTecnica() + "\n"));
                }
                case PELICULA_MAS_LARGA -> {
                    Contenido contenidoMasLarga = plataforma.getPeliculaMasLarga();
                    System.out.println(contenidoMasLarga.obtenerFechaTecnica() + "\n\n");
                }
                case PELICULA_MAS_CORTA -> {
                    Contenido contenidoMasCorta = plataforma.getPeliculaMasCorta();
                    System.out.println(contenidoMasCorta.obtenerFechaTecnica() + "\n\n");
                }
                case REPRODUCIR -> {
                    String nombreAVer = ScannerUtils.capturarTexto("Nombre del contenido a reproducir");
                    Contenido contenido = plataforma.buscarPorTitulo(nombreAVer);
                    if (contenido != null) {
                        plataforma.reproducir(contenido);
                    } else {
                        System.out.println(nombreAVer + " no existe dentro de la "+ plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_TIPO -> {
                   int tipoContenido = ScannerUtils.capturarNumero("Que tipo de contenido quieres agregar? \n 1. Pelicula \n 2. Documental");

                   if (tipoContenido == 1) {
                       List<Pelicula> peliculas = plataforma.getPeliculas();
                       peliculas.forEach(contenido -> System.out.println(contenido.obtenerFechaTecnica() + "\n"));
                   } else {
                       List<Documental> documentales = plataforma.getDocumentales();
                       documentales.forEach(contenido -> System.out.println(contenido.obtenerFechaTecnica() + "\n"));
                   }
                }
                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturarTexto("Nombre del contenido a Eliminar");
                    Contenido contenido = plataforma.buscarPorTitulo(nombreAEliminar);

                    if(contenido != null) {
                        plataforma.eliminar(contenido);
                        System.out.println(nombreAEliminar + " eliminado!");
                    } else {
                        System.out.println(nombreAEliminar + " no existe dentro de la "+ plataforma.getNombre());
                    }
                }
                case SALIR -> System.exit(0);

                default -> throw new IllegalStateException("Unexpected value: " + opcionElegida);
            }
        }
    }

    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.getContenido().addAll(FileUtils.leerContenido());
    }
}
