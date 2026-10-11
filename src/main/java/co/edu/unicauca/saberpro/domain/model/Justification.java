package co.edu.unicauca.saberpro.domain.model;

public class Justification {

    private String texto;

    public Justification(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Justification{texto='" + texto + "'}";
    }
}
