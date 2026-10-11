package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Classification;
import co.edu.unicauca.saberpro.domain.model.Context;
import co.edu.unicauca.saberpro.domain.model.Justification;
import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;


public class QuestionBuilder implements IQuestionBuilder {

    private Question question = new Question();

    @Override
    public IQuestionBuilder withContext(String texto, String fuente) {
        this.question.setContext(new Context(texto, fuente));
        return this;
    }

    @Override
    public IQuestionBuilder withEnunciado(String enunciado) {
        if (enunciado != null && enunciado.contains("Todas las anteriores")) {
            throw new IllegalArgumentException("Enunciado no permitido por HJ03.");
        }
        this.question.setEnunciado(enunciado);
        return this;
    }

    @Override
    public IQuestionBuilder addOption(String texto, boolean esCorrecta) {
        this.question.getOpciones().add(new Option(texto, esCorrecta));
        return this;
    }

    @Override
    public IQuestionBuilder withJustificacion(String texto) {
        this.question.setJustificacion(new Justification(texto));
        return this;
    }

    @Override
    public IQuestionBuilder withClasificacion(String competencia, String nivel) {
        this.question.setClasificacion(new Classification(competencia, nivel));
        return this;
    }

    @Override
    public Question build() {
        if (question.getOpciones().size() != 4) {
            throw new IllegalStateException("La pregunta debe tener exactamente 4 opciones.");
        }
        long correctas = question.getOpciones().stream().filter(Option::isEsCorrecta).count();
        if (correctas != 1) {
            throw new IllegalStateException("La pregunta debe tener exactamente 1 respuesta correcta.");
        }
        Question resultado = this.question;
        this.question = new Question();
        return resultado;
    }
}
