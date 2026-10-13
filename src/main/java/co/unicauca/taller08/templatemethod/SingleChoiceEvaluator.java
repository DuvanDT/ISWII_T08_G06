package co.unicauca.taller08.templatemethod;

import co.unicauca.taller08.domain.Question;

import java.util.List;

public class SingleChoiceEvaluator extends QuestionEvaluator {

    @Override
    protected void validate(Question question, List<String> answers) {
        if (answers == null || answers.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar una respuesta.");
        }
        if (answers.size() > 1) {
            throw new IllegalArgumentException("Para preguntas de selección única solo se permite seleccionar exactamente una opción.");
        }
    }

    @Override
    protected boolean compareAnswers(Question question, List<String> answers) {
        String selected = answers.get(0);
        List<String> correct = question.getCorrectAnswers();
        return correct != null && correct.contains(selected);
    }
}
