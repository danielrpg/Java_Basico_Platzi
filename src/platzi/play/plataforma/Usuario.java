package platzi.play.plataforma;

import platzi.play.contenido.Pelicula;

import java.time.LocalDateTime;

public class Usuario {
    public String nombre;
    public String email;
    public LocalDateTime fechaRegistro; // Aca usamos LocalDateTime por que necesitamos los minutos segundos

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = LocalDateTime.now();
    }

    public void ver(Pelicula pelicula) {
        System.out.println(nombre + " esta viendo ...");
        pelicula.reproducir();
    }
}
