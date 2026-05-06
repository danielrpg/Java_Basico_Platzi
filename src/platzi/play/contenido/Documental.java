package platzi.play.contenido;

public class Documental extends Contenido {
    private String narrador;

    public Documental(String titulo, int duracion, Genero genero, Idioma idioma, Calidad calidad) {
        super(titulo, duracion, genero, idioma, calidad);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo el documento " + getTitulo() + " narrado por " + getNarrador() + "....");
    }

    public Documental(String titulo, int duracion, Genero genero, Idioma idioma, Calidad calidad, double calificacion, String narrador) {
        super(titulo, duracion, genero, idioma, calidad, calificacion);
        this.narrador = narrador;
    }

    public String getNarrador() {
        return narrador;
    }
}
