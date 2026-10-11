package co.edu.unicauca.saberpro.adapter;

import co.edu.unicauca.saberpro.builder.IQuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.domain.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExternalQuestionAdapter implements QuestionProvider {

    private static final Pattern ENUNCIADO = Pattern.compile("\"enunciado\"\\s*:\\s*\"([^\"]*)\"");
    private static final Pattern OPCIONES = Pattern.compile("\"opciones\"\\s*:\\s*\\[(.*?)\\]");
    private static final Pattern TEXTO_OPCION = Pattern.compile("\"([^\"]*)\"");
    private static final Pattern CORRECTA = Pattern.compile("\"correcta\"\\s*:\\s*(\\d+)");

    private final ExternalQuestionService service;

    public ExternalQuestionAdapter(ExternalQuestionService service) {
        this.service = service;
    }

    @Override
    public Question getQuestion() {
        String rawJson = service.getQuestionData();

        String enunciado = extraer(ENUNCIADO, rawJson, 1);
        List<String> opciones = extraerOpciones(rawJson);
        int correcta = Integer.parseInt(extraer(CORRECTA, rawJson, 1));

        IQuestionBuilder builder = new QuestionBuilder()
                .withContext("Importada desde servicio externo", "Externo")
                .withEnunciado(enunciado);

        for (int i = 0; i < opciones.size(); i++) {
            builder.addOption(opciones.get(i), i == correcta);
        }

        return builder.build();
    }

    private List<String> extraerOpciones(String rawJson) {
        List<String> opciones = new ArrayList<>();
        Matcher matcherLista = OPCIONES.matcher(rawJson);
        if (matcherLista.find()) {
            Matcher matcherTexto = TEXTO_OPCION.matcher(matcherLista.group(1));
            while (matcherTexto.find()) {
                opciones.add(matcherTexto.group(1));
            }
        }
        return opciones;
    }

    private String extraer(Pattern patron, String texto, int grupo) {
        Matcher matcher = patron.matcher(texto);
        if (matcher.find()) {
            return matcher.group(grupo);
        }
        throw new IllegalArgumentException("No se pudo adaptar el formato externo: " + texto);
    }
}
