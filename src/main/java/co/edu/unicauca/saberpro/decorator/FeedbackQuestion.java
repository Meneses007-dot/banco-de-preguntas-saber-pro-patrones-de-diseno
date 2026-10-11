package co.edu.unicauca.saberpro.decorator;

public class FeedbackQuestion extends QuestionDecorator {

    private final String retroalimentacion;

    public FeedbackQuestion(QuestionComponent component, String retroalimentacion) {
        super(component);
        this.retroalimentacion = retroalimentacion;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    @Override
    public String getDescripcion() {
        return component.getDescripcion() + " | Retroalimentacion: " + retroalimentacion;
    }
}
