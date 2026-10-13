package co.unicauca.taller08.decorator;

public class ContextualizedQuestion extends QuestionDecorator {
    private String contextText;

    public ContextualizedQuestion(QuestionComponent question, String contextText) {
        super(question);
        this.contextText = contextText;
    }

    @Override
    public String getDescription() {
        return "[CONTEXTO: " + contextText + "] " + question.getDescription();
    }
}
