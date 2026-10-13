package co.unicauca.taller08.adapter;

import co.unicauca.taller08.domain.AnswerOption;
import co.unicauca.taller08.domain.Question;
import co.unicauca.taller08.domain.QuestionType;

import java.util.List;

public class ExternalQuestionAdapter implements QuestionProvider {
    private ExternalQuestionService externalService;

    public ExternalQuestionAdapter(ExternalQuestionService externalService) {
        this.externalService = externalService;
    }

    @Override
    public Question getQuestion() {
        // Obtiene los datos del servicio externo
        String rawData = externalService.getQuestionData();

        // Transforma los datos formateados del sistema externo al objeto de dominio Question
        String id = extractField(rawData, "ID:", "|");
        String statement = extractField(rawData, "Enunciado:", "|");
        String correctAnswer = extractField(rawData, "Correcta:", null).trim();

        Question question = new Question(id.trim(), statement.trim(), QuestionType.SINGLE_CHOICE);

        // Parsear y agregar opciones de respuesta
        question.addOption(new AnswerOption("A", "Medellín", "A".equalsIgnoreCase(correctAnswer)));
        question.addOption(new AnswerOption("B", "Bogotá", "B".equalsIgnoreCase(correctAnswer)));
        question.addOption(new AnswerOption("C", "Cali", "C".equalsIgnoreCase(correctAnswer)));

        return question;
    }

    private String extractField(String source, String startKey, String endKey) {
        int startIndex = source.indexOf(startKey);
        if (startIndex == -1) return "";
        startIndex += startKey.length();

        if (endKey == null) {
            return source.substring(startIndex);
        }

        int endIndex = source.indexOf(endKey, startIndex);
        if (endIndex == -1) {
            return source.substring(startIndex);
        }

        return source.substring(startIndex, endIndex);
    }
}
