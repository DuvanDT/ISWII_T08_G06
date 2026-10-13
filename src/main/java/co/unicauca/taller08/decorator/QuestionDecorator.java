package co.unicauca.taller08.decorator;

public abstract class QuestionDecorator implements QuestionComponent {
    protected QuestionComponent question;

    public QuestionDecorator(QuestionComponent question) {
        this.question = question;
    }

    @Override
    public String getDescription() {
        return question.getDescription();
    }

    @Override
    public double getScore() {
        return question.getScore();
    }
}
