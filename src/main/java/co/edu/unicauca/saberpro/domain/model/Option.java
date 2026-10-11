package co.edu.unicauca.saberpro.domain.model;

public class Option {

    private String texto;
    private boolean esCorrecta;

    public Option(String texto, boolean esCorrecta) {
        this.texto = texto;
        this.esCorrecta = esCorrecta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    @Override
    public String toString() {
        return "Option{texto='" + texto + "', esCorrecta=" + esCorrecta + "}";
    }
}
