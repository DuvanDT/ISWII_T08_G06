package co.unicauca.taller08.templatemethod;

import co.unicauca.taller08.domain.Question;

import java.util.List;

public class TrueFalseEvaluator extends QuestionEvaluator {

    @Override
    protected void validate(Question question, List<String> answers) {
        if (answers == null || answers.size() != 1) {
            throw new IllegalArgumentException("Debe seleccionar exactamente una respuesta (Verdadero o Falso).");
        }
        String answer = answers.get(0);
        if (!("Verdadero".equalsIgnoreCase(answer) || "Falso".equalsIgnoreCase(answer) ||
              "V".equalsIgnoreCase(answer) || "F".equalsIgnoreCase(answer))) {
            throw new IllegalArgumentException("La respuesta debe ser 'Verdadero' o 'Falso'.");
        }
    }

    @Override
    protected boolean compareAnswers(Question question, List<String> answers) {
        String studentAnswer = answers.get(0);
        List<String> correctAnswers = question.getCorrectAnswers();

        if (correctAnswers == null || correctAnswers.isEmpty()) {
            return false;
        }

        String correctAnswer = correctAnswers.get(0);
        return studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
    }
}
