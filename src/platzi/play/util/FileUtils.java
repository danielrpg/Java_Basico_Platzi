package platzi.play.util;

import platzi.play.contenido.*;

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
        try {
            Files.writeString(Paths.get(NOMBRE_ARCHIVO),  // le mandamos el nombre del archivo el path
                        linea + System.lineSeparator(), // mandamos la linea mas un separador de linea
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
                String[] datos = linea.split(SEPARADOR);
                if (datos.length == 5) {
                    String titulo = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    Genero genero = Genero.valueOf(datos[2].toUpperCase());
                    double calificacion = datos[3].isBlank() ? 0 : Double.parseDouble(datos[3]);
                    LocalDate fechaEstreno = datos[4].isBlank() ? null : LocalDate.parse(datos[4]);

                    Contenido contenido = new Contenido(titulo, duracion, genero, Idioma.ESPANOL, Calidad.ALTA, calificacion);
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
