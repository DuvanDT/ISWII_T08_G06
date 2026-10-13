package co.unicauca.taller08.decorator;

public class BasicQuestion implements QuestionComponent {
    private String description;
    private double baseScore;

    public BasicQuestion(String description) {
        this(description, 1.0);
    }

    public BasicQuestion(String description, double baseScore) {
        this.description = description;
        this.baseScore = baseScore;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getScore() {
        return baseScore;
    }
}
