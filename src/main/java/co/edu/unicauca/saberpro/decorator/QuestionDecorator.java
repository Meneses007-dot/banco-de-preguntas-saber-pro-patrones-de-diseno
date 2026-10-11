package co.edu.unicauca.saberpro.decorator;

public abstract class QuestionDecorator implements QuestionComponent {

    protected final QuestionComponent component;

    protected QuestionDecorator(QuestionComponent component) {
        this.component = component;
    }

    @Override
    public String getDescripcion() {
        return component.getDescripcion();
    }

    @Override
    public double getPuntaje() {
        return component.getPuntaje();
    }
}
