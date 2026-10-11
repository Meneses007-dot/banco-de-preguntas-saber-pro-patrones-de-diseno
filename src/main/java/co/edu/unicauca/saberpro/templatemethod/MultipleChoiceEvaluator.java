package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Question;

public class MultipleChoiceEvaluator extends QuestionEvaluator {

    @Override
    protected void validarFormato(String respuesta) {
        if (respuesta == null || !respuesta.trim().toUpperCase().matches("[A-D]")) {
            throw new IllegalArgumentException("Respuesta invalida: se espera una letra entre A y D.");
        }
    }

    @Override
    protected double calcularPuntaje(Question question, String respuesta) {
        int indice = respuesta.trim().toUpperCase().charAt(0) - 'A';
        if (indice < 0 || indice >= question.getOpciones().size()) {
            return 0.0;
        }
        return question.getOpciones().get(indice).isEsCorrecta() ? 1.0 : 0.0;
    }

    @Override
    protected String generarRetroalimentacion(Question question, String respuesta, double puntaje) {
        if (puntaje == 1.0) {
            return "Respuesta correcta. Bien hecho.";
        }
        return "Respuesta incorrecta. Revisa el contexto de la pregunta.";
    }
}
