package co.unicauca.taller08.templatemethod;

import co.unicauca.taller08.domain.Question;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultipleChoiceEvaluator extends QuestionEvaluator {

    @Override
    protected void validate(Question question, List<String> answers) {
        if (answers == null || answers.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar al menos una respuesta para preguntas de selección múltiple.");
        }
    }

    @Override
    protected boolean compareAnswers(Question question, List<String> answers) {
        List<String> correct = question.getCorrectAnswers();
        if (correct == null || correct.isEmpty()) {
            return false;
        }

        Set<String> selectedSet = new HashSet<>(answers);
        Set<String> correctSet = new HashSet<>(correct);

        return selectedSet.equals(correctSet);
    }
}
