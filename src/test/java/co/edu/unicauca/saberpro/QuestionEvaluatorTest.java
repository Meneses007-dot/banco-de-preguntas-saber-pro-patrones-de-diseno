package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.builder.IQuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.templatemethod.EvaluationResult;
import co.edu.unicauca.saberpro.templatemethod.MultipleChoiceEvaluator;
import co.edu.unicauca.saberpro.templatemethod.TrueFalseEvaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionEvaluatorTest {

    private Question preguntaMultiple() {
        return new QuestionBuilder()
                .withEnunciado("¿Cual es la capital de Colombia?")
                .addOption("Bogota", true)
                .addOption("Medellin", false)
                .addOption("Cali", false)
                .addOption("Popayan", false)
                .build();
    }

    private Question preguntaVerdaderoFalso() {
        IQuestionBuilder builder = new QuestionBuilder()
                .withEnunciado("La reproducibilidad es un pilar del metodo cientifico.")
                .addOption("Verdadero", true)
                .addOption("Falso", false)
                .addOption("No aplica", false)
                .addOption("Depende", false);
        return builder.build();
    }

    @Test
    void seleccionMultipleCorrecta() {
        EvaluationResult resultado = new MultipleChoiceEvaluator().evaluar(preguntaMultiple(), "A");
        assertEquals(1.0, resultado.puntaje());
    }

    @Test
    void seleccionMultipleIncorrecta() {
        EvaluationResult resultado = new MultipleChoiceEvaluator().evaluar(preguntaMultiple(), "C");
        assertEquals(0.0, resultado.puntaje());
        assertFalse(resultado.retroalimentacion().isBlank());
    }

    @Test
    void seleccionMultipleConFormatoInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new MultipleChoiceEvaluator().evaluar(preguntaMultiple(), "Z"));
    }

    @Test
    void verdaderoFalsoCorrecta() {
        EvaluationResult resultado = new TrueFalseEvaluator().evaluar(preguntaVerdaderoFalso(), "V");
        assertEquals(1.0, resultado.puntaje());
    }

    @Test
    void verdaderoFalsoIncorrecta() {
        EvaluationResult resultado = new TrueFalseEvaluator().evaluar(preguntaVerdaderoFalso(), "F");
        assertEquals(0.0, resultado.puntaje());
    }
}
