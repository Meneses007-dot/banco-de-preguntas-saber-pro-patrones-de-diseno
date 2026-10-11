package co.edu.unicauca.saberpro.domain.model;

public class Context {

    private String texto;
    private String fuente;

    public Context(String texto, String fuente) {
        this.texto = texto;
        this.fuente = fuente;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    @Override
    public String toString() {
        return "Context{texto='" + texto + "', fuente='" + fuente + "'}";
    }
}
