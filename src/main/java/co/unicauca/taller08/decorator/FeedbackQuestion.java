package co.unicauca.taller08.decorator;

public class FeedbackQuestion extends QuestionDecorator {
    private String feedback;

    public FeedbackQuestion(QuestionComponent question, String feedback) {
        super(question);
        this.feedback = feedback;
    }

    @Override
    public String getDescription() {
        return question.getDescription() + " [RETROALIMENTACIÓN: " + feedback + "]";
    }
}
