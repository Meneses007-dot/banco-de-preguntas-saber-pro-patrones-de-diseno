package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.state.ApprovedState;
import co.edu.unicauca.saberpro.state.DraftState;
import co.edu.unicauca.saberpro.state.PendingReviewState;
import co.edu.unicauca.saberpro.state.ReviewState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionStateTest {

    @Test
    void iniciaEnEstadoBorrador() {
        Question question = new Question();
        assertInstanceOf(DraftState.class, question.getEstado());
        assertEquals("Borrador", question.getNombreEstado());
    }

    @Test
    void borradorPermiteEditar() {
        Question question = new Question();
        question.getEstado().editar(question, "Enunciado editado");
        assertEquals("Enunciado editado", question.getEnunciado());
    }

    @Test
    void borradorTransicionaAPendienteDeRevision() {
        Question question = new Question();
        question.getEstado().enviarARevision(question);
        assertInstanceOf(PendingReviewState.class, question.getEstado());
    }

    @Test
    void pendienteDeRevisionBloqueaEdicion() {
        Question question = new Question();
        question.getEstado().enviarARevision(question);
        assertThrows(IllegalStateException.class,
                () -> question.getEstado().editar(question, "No permitido"));
    }

    @Test
    void revisionApruebaLaPregunta() {
        Question question = new Question();
        question.getEstado().enviarARevision(question);
        question.getEstado().revisar(question);
        assertInstanceOf(ReviewState.class, question.getEstado());
        question.getEstado().aprobar(question);
        assertInstanceOf(ApprovedState.class, question.getEstado());
        assertEquals("Aprobada", question.getNombreEstado());
    }

    @Test
    void solicitarCorreccionesVuelveABorrador() {
        Question question = new Question();
        question.getEstado().enviarARevision(question);
        question.getEstado().revisar(question);
        question.getEstado().solicitarCorrecciones(question);
        assertInstanceOf(DraftState.class, question.getEstado());
    }
}
