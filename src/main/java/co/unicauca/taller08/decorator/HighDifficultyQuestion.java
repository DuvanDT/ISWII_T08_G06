package co.unicauca.taller08.decorator;

public class HighDifficultyQuestion extends QuestionDecorator {

    public HighDifficultyQuestion(QuestionComponent question) {
        super(question);
    }

    @Override
    public String getDescription() {
        return question.getDescription() + " [ALTA DIFICULTAD]";
    }

    @Override
    public double getScore() {
        // Incrementa la puntuación base en 0.5 puntos por alta dificultad
        return question.getScore() + 0.5;
    }
}
