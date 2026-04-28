package platzi.play;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;

public class MainHeapStack {
    public static void main(String[] args) {
        Pelicula harryPotter = new Pelicula("Harry Potter", 150, Genero.FANTASIA);
        Pelicula reyLeon = new Pelicula("El Rey Leon", 200, Genero.ANIMADA);

        reyLeon = harryPotter; // aqui cambiamos la referencia abos apuntan al mismo objeto

//        reyLeon.titulo = "El Hobbit";  // por eso el cambio ocurre en ambos objetos

        System.out.println("reyLeon: " + reyLeon.getTitulo());
        System.out.println("harryPotter: " + harryPotter.getTitulo());
    }
}
