package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

public interface IQuestionState {

    void editar(Question question, String nuevoEnunciado);

    void enviarARevision(Question question);

    void revisar(Question question);

    void aprobar(Question question);

    void solicitarCorrecciones(Question question);

    String getNombre();
}
