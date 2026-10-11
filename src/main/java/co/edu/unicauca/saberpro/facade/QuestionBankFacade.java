package co.edu.unicauca.saberpro.facade;

import co.edu.unicauca.saberpro.adapter.ExternalQuestionAdapter;
import co.edu.unicauca.saberpro.adapter.ExternalQuestionService;
import co.edu.unicauca.saberpro.adapter.QuestionProvider;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionDirector;
import co.edu.unicauca.saberpro.decorator.BasicQuestion;
import co.edu.unicauca.saberpro.decorator.FeedbackQuestion;
import co.edu.unicauca.saberpro.decorator.HighDifficultyQuestion;
import co.edu.unicauca.saberpro.decorator.QuestionComponent;
import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.templatemethod.CaseStudyEvaluator;
import co.edu.unicauca.saberpro.templatemethod.EvaluationResult;
import co.edu.unicauca.saberpro.templatemethod.MultipleChoiceEvaluator;
import co.edu.unicauca.saberpro.templatemethod.QuestionEvaluator;
import co.edu.unicauca.saberpro.templatemethod.TrueFalseEvaluator;

import java.util.ArrayList;
import java.util.List;


public class QuestionBankFacade {

    public static final String TIPO_MULTIPLE = "MULTIPLE";
    public static final String TIPO_VERDADERO_FALSO = "VF";
    public static final String TIPO_CASO = "CASO";

    private final List<Question> preguntas = new ArrayList<>();
    private final QuestionDirector director = new QuestionDirector();
    private final ExternalQuestionService servicioExterno = new ExternalQuestionService();

    public Question crearPreguntaLecturaCritica() {
        return registrar(director.buildPreguntaLecturaCritica(new QuestionBuilder()));
    }

    public Question crearPreguntaVerdaderoFalso() {
        return registrar(director.buildPreguntaVerdaderoFalso(new QuestionBuilder()));
    }

    public Question importarPreguntaExterna() {
        QuestionProvider provider = new ExternalQuestionAdapter(servicioExterno);
        return registrar(provider.getQuestion());
    }

    public List<Question> getPreguntas() {
        return preguntas;
    }

    public void enviarARevision(Question question) {
        question.getEstado().enviarARevision(question);
    }

    public void revisar(Question question) {
        question.getEstado().revisar(question);
    }

    public void aprobar(Question question) {
        question.getEstado().aprobar(question);
    }

    public void solicitarCorrecciones(Question question) {
        question.getEstado().solicitarCorrecciones(question);
    }

    public EvaluationResult evaluar(Question question, String tipo, String respuesta) {
        QuestionEvaluator evaluator = switch (tipo == null ? "" : tipo.toUpperCase()) {
            case TIPO_VERDADERO_FALSO -> new TrueFalseEvaluator();
            case TIPO_CASO -> new CaseStudyEvaluator();
            default -> new MultipleChoiceEvaluator();
        };
        return evaluator.evaluar(question, respuesta);
    }

    public QuestionComponent conAltaDificultad(Question question) {
        return new HighDifficultyQuestion(new BasicQuestion(question));
    }

    public QuestionComponent conRetroalimentacion(QuestionComponent component, String retroalimentacion) {
        return new FeedbackQuestion(component, retroalimentacion);
    }

    private Question registrar(Question question) {
        preguntas.add(question);
        return question;
    }
}
