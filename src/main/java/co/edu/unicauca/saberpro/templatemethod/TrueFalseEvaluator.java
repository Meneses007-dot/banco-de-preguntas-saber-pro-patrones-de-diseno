package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;

import java.util.Locale;

public class TrueFalseEvaluator extends QuestionEvaluator {

    @Override
    protected void validarFormato(String respuesta) {
        if (respuesta == null) {
            throw new IllegalArgumentException("Respuesta invalida: se espera V o F.");
        }
        String normalizada = normalizar(respuesta);
        if (!normalizada.equals("verdadero") && !normalizada.equals("falso")) {
            throw new IllegalArgumentException("Respuesta invalida: se espera V o F.");
        }
    }

    @Override
    protected double calcularPuntaje(Question question, String respuesta) {
        Option correcta = buscarOpcionCorrecta(question);
        String esperada = normalizar(correcta.getTexto());
        return esperada.equals(normalizar(respuesta)) ? 1.0 : 0.0;
    }

    @Override
    protected String generarRetroalimentacion(Question question, String respuesta, double puntaje) {
        return puntaje == 1.0
                ? "Afirmacion evaluada correctamente."
                : "Afirmacion incorrecta. Verifica la justificacion.";
    }

    private String normalizar(String valor) {
        String limpio = valor.trim().toLowerCase(Locale.ROOT);
        if (limpio.equals("v")) {
            return "verdadero";
        }
        if (limpio.equals("f")) {
            return "falso";
        }
        return limpio;
    }
}
