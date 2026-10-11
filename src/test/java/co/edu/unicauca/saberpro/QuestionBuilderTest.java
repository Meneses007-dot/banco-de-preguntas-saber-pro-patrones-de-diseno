package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.builder.IQuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.domain.model.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionBuilderTest {

    private IQuestionBuilder builderValido() {
        return new QuestionBuilder()
                .withContext("Contexto de prueba", "Fuente")
                .withEnunciado("¿Cual es la respuesta correcta?")
                .addOption("A. Opcion uno", true)
                .addOption("B. Opcion dos", false)
                .addOption("C. Opcion tres", false)
                .addOption("D. Opcion cuatro", false)
                .withJustificacion("Porque la opcion A es la correcta.")
                .withClasificacion("Lectura Critica", "Media");
    }

    @Test
    void construyePreguntaValida() {
        Question question = builderValido().build();
        assertNotNull(question);
        assertEquals(4, question.getOpciones().size());
        assertEquals("¿Cual es la respuesta correcta?", question.getEnunciado());
        assertEquals("Borrador", question.getNombreEstado());
    }

    @Test
    void fallaConMenosDeCuatroOpciones() {
        IQuestionBuilder builder = new QuestionBuilder()
                .withEnunciado("Pregunta incompleta")
                .addOption("A. Opcion uno", true);
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void fallaSinRespuestaCorrecta() {
        IQuestionBuilder builder = new QuestionBuilder()
                .withEnunciado("Pregunta sin correcta")
                .addOption("A", false)
                .addOption("B", false)
                .addOption("C", false)
                .addOption("D", false);
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void fallaConMultiplesRespuestasCorrectas() {
        IQuestionBuilder builder = new QuestionBuilder()
                .withEnunciado("Pregunta con dos correctas")
                .addOption("A", true)
                .addOption("B", true)
                .addOption("C", false)
                .addOption("D", false);
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void rechazaEnunciadoProhibido() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuestionBuilder().withEnunciado("Marque la opcion: Todas las anteriores"));
    }
}
