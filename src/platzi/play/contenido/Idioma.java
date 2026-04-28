package platzi.play.contenido;

public enum Idioma {
    ESPANOL ("Español", "ES"),
    INGLES ("Ingles", "EN"),
    PORTUGUES ("Portugues", "PT");

    private final String descripcion;
    private final String codigo;

    Idioma(String descripcion, String codigo) {
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigo() {
        return codigo;
    }
}
