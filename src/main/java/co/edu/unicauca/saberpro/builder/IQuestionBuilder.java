package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Question;

public interface IQuestionBuilder {

    IQuestionBuilder withContext(String texto, String fuente);

    IQuestionBuilder withEnunciado(String enunciado);

    IQuestionBuilder addOption(String texto, boolean esCorrecta);

    IQuestionBuilder withJustificacion(String texto);

    IQuestionBuilder withClasificacion(String competencia, String nivel);

    Question build();
}
