package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;

public abstract class QuestionEvaluator {

    public final EvaluationResult evaluar(Question question, String respuesta) {
        validarFormato(respuesta);
        double puntaje = calcularPuntaje(question, respuesta);
        String retroalimentacion = generarRetroalimentacion(question, respuesta, puntaje);
        return new EvaluationResult(puntaje, retroalimentacion);
    }

    protected abstract void validarFormato(String respuesta);

    protected abstract double calcularPuntaje(Question question, String respuesta);

    protected abstract String generarRetroalimentacion(Question question, String respuesta, double puntaje);

    protected Option buscarOpcionCorrecta(Question question) {
        return question.getOpciones().stream()
                .filter(Option::isEsCorrecta)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("La pregunta no tiene respuesta correcta."));
    }
}
