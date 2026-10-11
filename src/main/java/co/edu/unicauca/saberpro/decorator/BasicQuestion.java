package co.edu.unicauca.saberpro.decorator;

import co.edu.unicauca.saberpro.domain.model.Question;

public class BasicQuestion implements QuestionComponent {

    private static final double PUNTAJE_BASE = 1.0;

    private final Question question;

    public BasicQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String getDescripcion() {
        return question.getEnunciado();
    }

    @Override
    public double getPuntaje() {
        return PUNTAJE_BASE;
    }

    public Question getQuestion() {
        return question;
    }
}
