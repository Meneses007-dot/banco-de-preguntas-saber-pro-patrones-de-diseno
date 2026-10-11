package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

public class PendingReviewState implements IQuestionState {

    @Override
    public void editar(Question question, String nuevoEnunciado) {
        throw new IllegalStateException("El autor no puede editar una pregunta pendiente de revision.");
    }

    @Override
    public void enviarARevision(Question question) {
        throw new IllegalStateException("La pregunta ya fue enviada a revision.");
    }

    @Override
    public void revisar(Question question) {
        question.setEstado(new ReviewState());
    }

    @Override
    public void aprobar(Question question) {
        throw new IllegalStateException("La pregunta debe revisarse antes de aprobarse.");
    }

    @Override
    public void solicitarCorrecciones(Question question) {
        question.setEstado(new DraftState());
    }

    @Override
    public String getNombre() {
        return "Pendiente de Revision";
    }
}
