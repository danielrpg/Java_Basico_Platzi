package platzi.play;

import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Plataforma;
import platzi.play.plataforma.Usuario;
import platzi.play.util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

// import java.util.Scanner;

public class Main {
    public static final String VERSION = "1.0.1";
    public static final String NOMBRE = "Platzi Play";
    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE);
        System.out.println(NOMBRE + " v" + VERSION);

        String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
        int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
        String genero = ScannerUtils.capturarTexto("Genero del contenido");
        double calificacion = ScannerUtils.capturarDouble("Calificacion del contenido");


       // Pelicula pelicula = new Pelicula();
        Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);
        Pelicula pelicula1 = new Pelicula("Fores Gump", 220, "Accion");
        pelicula.calificar(calificacion);

        plataforma.agregar(pelicula);
        plataforma.agregar(pelicula1);
        System.out.println("Numero de elementos en la plataforma " + plataforma.getContenido().size());
        plataforma.eliminar(pelicula1);

        plataforma.mostrarTitulos();

        //System.out.println(pelicula.obtenerFechaTecnica());

//        long duracionLong = pelicula.duracion; // Aqui duracion es int y lo convertimos a long pero nosotros no hacemos nada
//        int calificacionInt = (int) pelicula.calificacion; // el casteo es explicito por que calificacion es double y al convertirlo
                                                           // a int perdemos sus decimales
       // int numeroDePremios = (int) Long.parseLong("25000000000");

        // Este es el constructor con parametros
        Usuario usuario = new Usuario("Daniel", "daniel@gmail.com");

        usuario.ver(pelicula);
        System.out.println(usuario.fechaRegistro);

    }
}
