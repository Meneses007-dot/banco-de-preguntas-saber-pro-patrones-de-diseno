package co.edu.unicauca.saberpro.domain.model;

public class Classification {

    private String competencia;
    private String nivel;

    public Classification(String competencia, String nivel) {
        this.competencia = competencia;
        this.nivel = nivel;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Classification{competencia='" + competencia + "', nivel='" + nivel + "'}";
    }
}
