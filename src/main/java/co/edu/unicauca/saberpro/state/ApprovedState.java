package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

public class ApprovedState implements IQuestionState {

    @Override
    public void editar(Question question, String nuevoEnunciado) {
        throw new IllegalStateException("Una pregunta aprobada no puede editarse.");
    }

    @Override
    public void enviarARevision(Question question) {
        throw new IllegalStateException("Una pregunta aprobada no requiere nueva revision.");
    }

    @Override
    public void revisar(Question question) {
        throw new IllegalStateException("Una pregunta aprobada ya fue revisada.");
    }

    @Override
    public void aprobar(Question question) {
        throw new IllegalStateException("La pregunta ya esta aprobada.");
    }

    @Override
    public void solicitarCorrecciones(Question question) {
        question.setEstado(new DraftState());
    }

    @Override
    public String getNombre() {
        return "Aprobada";
    }
}
