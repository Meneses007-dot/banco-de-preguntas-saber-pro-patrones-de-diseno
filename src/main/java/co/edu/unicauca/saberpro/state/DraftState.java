package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

public class DraftState implements IQuestionState {

    @Override
    public void editar(Question question, String nuevoEnunciado) {
        question.setEnunciado(nuevoEnunciado);
    }

    @Override
    public void enviarARevision(Question question) {
        question.setEstado(new PendingReviewState());
    }

    @Override
    public void revisar(Question question) {
        throw new IllegalStateException("Una pregunta en borrador no puede revisarse.");
    }

    @Override
    public void aprobar(Question question) {
        throw new IllegalStateException("Una pregunta en borrador no puede aprobarse.");
    }

    @Override
    public void solicitarCorrecciones(Question question) {
        throw new IllegalStateException("La pregunta ya se encuentra en borrador.");
    }

    @Override
    public String getNombre() {
        return "Borrador";
    }
}
