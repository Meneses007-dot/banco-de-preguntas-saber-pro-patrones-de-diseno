package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Question;

public class QuestionDirector {

    public Question buildPreguntaLecturaCritica(IQuestionBuilder builder) {
        return builder
                .withContext("Texto sobre filosofia de la tecnologia...", "Pantoja, 2024")
                .withEnunciado("¿Cual es la tesis central planteada en el texto?")
                .addOption("A. La tecnologia aliena al individuo.", true)
                .addOption("B. El progreso es automatico.", false)
                .addOption("C. La ciencia reemplaza la etica.", false)
                .addOption("D. Ninguna de las anteriores.", false)
                .withJustificacion("El autor sostiene que la tecnologia aliena al individuo.")
                .withClasificacion("Lectura Critica", "Media")
                .build();
    }

    public Question buildPreguntaVerdaderoFalso(IQuestionBuilder builder) {
        return builder
                .withContext("Afirmacion sobre el metodo cientifico.", "Manual Saber PRO")
                .withEnunciado("La reproducibilidad es un pilar del metodo cientifico.")
                .addOption("Verdadero", true)
                .addOption("Falso", false)
                .addOption("No aplica", false)
                .addOption("Depende del caso", false)
                .withJustificacion("Toda observacion cientifica debe poder reproducirse.")
                .withClasificacion("Razonamiento Cuantitativo", "Baja")
                .build();
    }
}
