package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Question;

public class CaseStudyEvaluator extends MultipleChoiceEvaluator {

    @Override
    protected String generarRetroalimentacion(Question question, String respuesta, double puntaje) {
        if (puntaje == 1.0) {
            return "Analisis de caso correcto.";
        }
        return "El caso de estudio requiere analizar el contexto completo antes de responder.";
    }

    @Override
    protected double calcularPuntaje(Question question, String respuesta) {
        return super.calcularPuntaje(question, respuesta);
    }
}
