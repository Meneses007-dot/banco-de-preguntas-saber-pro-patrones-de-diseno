package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.decorator.QuestionComponent;
import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.facade.QuestionBankFacade;
import co.edu.unicauca.saberpro.templatemethod.EvaluationResult;

public class ApplicationMain {

    public static void main(String[] args) {
        QuestionBankFacade facade = new QuestionBankFacade();

        Question lecturaCritica = facade.crearPreguntaLecturaCritica();
        Question importada = facade.importarPreguntaExterna();

        System.out.println("=== Preguntas creadas ===");
        facade.getPreguntas().forEach(q -> {
            System.out.println(q);
            System.out.println("-------------------------");
        });

        System.out.println("=== Ciclo de vida (State) ===");
        System.out.println("Estado inicial: " + lecturaCritica.getNombreEstado());
        facade.enviarARevision(lecturaCritica);
        facade.revisar(lecturaCritica);
        facade.aprobar(lecturaCritica);
        System.out.println("Estado final: " + lecturaCritica.getNombreEstado());

        System.out.println("=== Evaluacion (Template Method) ===");
        EvaluationResult resultado = facade.evaluar(lecturaCritica, QuestionBankFacade.TIPO_MULTIPLE, "A");
        System.out.println("Puntaje: " + resultado.puntaje());
        System.out.println("Retroalimentacion: " + resultado.retroalimentacion());

        System.out.println("=== Decoracion (Decorator) ===");
        QuestionComponent dificil = facade.conAltaDificultad(importada);
        System.out.println("Puntaje con alta dificultad: " + dificil.getPuntaje());
        QuestionComponent conFeedback = facade.conRetroalimentacion(dificil, "Recuerda revisar el contexto.");
        System.out.println("Descripcion: " + conFeedback.getDescripcion());
    }
}
