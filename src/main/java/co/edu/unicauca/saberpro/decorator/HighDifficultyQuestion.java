package co.edu.unicauca.saberpro.decorator;

public class HighDifficultyQuestion extends QuestionDecorator {

    private static final double MULTIPLICADOR = 1.5;

    public HighDifficultyQuestion(QuestionComponent component) {
        super(component);
    }

    @Override
    public double getPuntaje() {
        return component.getPuntaje() * MULTIPLICADOR;
    }

    @Override
    public String getDescripcion() {
        return component.getDescripcion() + " [Alta dificultad x" + MULTIPLICADOR + "]";
    }
}
