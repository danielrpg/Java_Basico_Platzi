package platzi.play.contenido;

public enum Calidad {
    BAJA ("Calidad Baja", 240, "240p"),
    MEDIA ("Calidad Media", 360, "360p"),
    ALTA ("Calidad Alta", 720, "720p"),
    HD ("Calidad HD", 1080, "1080p");

    private final String descripcion;
    private final int codeCalidad;
    private final String codeCalidadTexto;

    Calidad(String descripcion, int codeCalidad, String codeCalidadTexto) {
        this.descripcion = descripcion;
        this.codeCalidad = codeCalidad;
        this.codeCalidadTexto = codeCalidadTexto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCodeCalidad() {
        return codeCalidad;
    }

    public String getCodeCalidadTexto() {
        return codeCalidadTexto;
    }
}
