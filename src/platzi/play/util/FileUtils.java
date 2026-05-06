package platzi.play.util;

import platzi.play.contenido.*;

import javax.print.Doc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static final String NOMBRE_ARCHIVO = "contenido.txt";
    public static final String SEPARADOR = "|";

    public static void escribirArchivo(Contenido contenido) {
        String linea = String.join(SEPARADOR,
                contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()),
                contenido.getGenero().name(),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno().toString(),
                contenido.getIdioma().name(),
                contenido.getCalidad().name()
        );

        String lineaFinal;

        if (contenido instanceof Documental documental) {
            // Documental documental = (Documental) contenido;
            lineaFinal = "DOCUMENTAL" + SEPARADOR + linea + SEPARADOR + documental.getNarrador();
        } else {
            lineaFinal = "PELICULA" + SEPARADOR + linea;
        }

        try {
            Files.writeString(Paths.get(NOMBRE_ARCHIVO),  // le mandamos el nombre del archivo el path
                        lineaFinal + System.lineSeparator(), // mandamos la linea mas un separador de linea
                    StandardOpenOption.CREATE, // le decimos que cree el archivo si no existe
                    StandardOpenOption.APPEND // agregamos el archivo no lo sobreescribimos
            );
        } catch (IOException e) {
            System.out.println("Error escribiendo el archivo " + e.getMessage());
        }
    }

    public static List<Contenido> leerContenido() {
        List<Contenido> peliculasDesdeArchivo = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));

            lineas.forEach(linea -> {
                String[] datos = linea.split("\\" + SEPARADOR);
                String tipoContenido = datos[0];

                if (("PELICULA".equals(tipoContenido) && datos.length == 9) || ("DOCUMENTAL".equals(tipoContenido) && datos.length == 9)) {
                    String tipo = datos[0];
                    String titulo = datos[1];
                    int duracion = Integer.parseInt(datos[2]);
                    Genero genero = Genero.valueOf(datos[3].toUpperCase());
                    double calificacion = datos[4].isBlank() ? 0 : Double.parseDouble(datos[4]);
                    LocalDate fechaEstreno = datos[5].isBlank() ? null : LocalDate.parse(datos[5]);
                    Idioma idioma = datos[6].isBlank() ? Idioma.INGLES : Idioma.valueOf(datos[6].toUpperCase());
                    Calidad calidad = datos[7].isBlank() ? Calidad.BAJA : Calidad.valueOf(datos[7].toUpperCase());

                    Contenido  contenido;

                    if ("PELICULA".equals(tipoContenido)) {
                        contenido = new Pelicula(titulo, duracion, genero, idioma, calidad, calificacion);
                    } else {
                        contenido = new Documental(titulo, duracion, genero, idioma, calidad, calificacion, datos[8]);
                    }

                    contenido.setFechaEstreno(fechaEstreno);
                    peliculasDesdeArchivo.add(contenido);
                }
            });
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo " + e.getMessage());
        }

        return  peliculasDesdeArchivo;
    }
}
