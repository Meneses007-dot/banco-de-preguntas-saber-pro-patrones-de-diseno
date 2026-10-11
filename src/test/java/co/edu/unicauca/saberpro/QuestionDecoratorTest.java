package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.decorator.BasicQuestion;
import co.edu.unicauca.saberpro.decorator.FeedbackQuestion;
import co.edu.unicauca.saberpro.decorator.HighDifficultyQuestion;
import co.edu.unicauca.saberpro.decorator.QuestionComponent;
import co.edu.unicauca.saberpro.domain.model.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionDecoratorTest {

    private Question pregunta() {
        return new QuestionBuilder()
                .withEnunciado("¿Cual es la capital de Colombia?")
                .addOption("Bogota", true)
                .addOption("Medellin", false)
                .addOption("Cali", false)
                .addOption("Popayan", false)
                .build();
    }

    @Test
    void preguntaBasicaTienePuntajeBase() {
        QuestionComponent basic = new BasicQuestion(pregunta());
        assertEquals(1.0, basic.getPuntaje());
        assertEquals("¿Cual es la capital de Colombia?", basic.getDescripcion());
    }

    @Test
    void altaDificultadMultiplicaElPuntaje() {
        QuestionComponent decorada = new HighDifficultyQuestion(new BasicQuestion(pregunta()));
        assertEquals(1.5, decorada.getPuntaje());
        assertTrue(decorada.getDescripcion().contains("Alta dificultad"));
    }

    @Test
    void retroalimentacionAmpliaLaDescripcion() {
        QuestionComponent decorada = new FeedbackQuestion(new BasicQuestion(pregunta()), "Revisa el contexto.");
        assertTrue(decorada.getDescripcion().contains("Revisa el contexto."));
        assertEquals(1.0, decorada.getPuntaje());
    }

    @Test
    void decoradoresAnidadosCombinanComportamientos() {
        QuestionComponent component = new FeedbackQuestion(
                new HighDifficultyQuestion(new BasicQuestion(pregunta())),
                "Recuerda el contexto.");
        assertEquals(1.5, component.getPuntaje());
        assertTrue(component.getDescripcion().contains("Alta dificultad"));
        assertTrue(component.getDescripcion().contains("Recuerda el contexto."));
    }
}
