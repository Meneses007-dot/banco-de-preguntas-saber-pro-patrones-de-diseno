package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

public class ReviewState implements IQuestionState {

    @Override
    public void editar(Question question, String nuevoEnunciado) {
        throw new IllegalStateException("Una pregunta en revision no puede editarse directamente.");
    }

    @Override
    public void enviarARevision(Question question) {
        throw new IllegalStateException("La pregunta ya se encuentra en revision.");
    }

    @Override
    public void revisar(Question question) {
        throw new IllegalStateException("La pregunta ya esta en revision.");
    }

    @Override
    public void aprobar(Question question) {
        question.setEstado(new ApprovedState());
    }

    @Override
    public void solicitarCorrecciones(Question question) {
        question.setEstado(new DraftState());
    }

    @Override
    public String getNombre() {
        return "En Revision";
    }
}
