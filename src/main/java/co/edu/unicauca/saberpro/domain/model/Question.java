package co.edu.unicauca.saberpro.domain.model;

import co.edu.unicauca.saberpro.state.DraftState;
import co.edu.unicauca.saberpro.state.IQuestionState;

import java.util.ArrayList;
import java.util.List;


public class Question {

    private Context context;
    private String enunciado;
    private final List<Option> opciones = new ArrayList<>();
    private Justification justificacion;
    private Classification clasificacion;
    private IQuestionState estado;

    public Question() {
        this.estado = new DraftState();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<Option> getOpciones() {
        return opciones;
    }

    public Justification getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(Justification justificacion) {
        this.justificacion = justificacion;
    }

    public Classification getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Classification clasificacion) {
        this.clasificacion = clasificacion;
    }

    public IQuestionState getEstado() {
        return estado;
    }

    public void setEstado(IQuestionState estado) {
        this.estado = estado;
    }

    public String getNombreEstado() {
        return estado.getNombre();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (context != null) {
            sb.append("Contexto: ").append(context.getTexto()).append("\n");
        }
        sb.append("Enunciado: ").append(enunciado).append("\n");
        for (int i = 0; i < opciones.size(); i++) {
            char letra = (char) ('A' + i);
            sb.append(letra).append(") ").append(opciones.get(i).getTexto()).append("\n");
        }
        sb.append("Estado: ").append(estado.getNombre());
        return sb.toString();
    }
}
