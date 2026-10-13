package co.unicauca.taller08.domain;

public class AnswerOption {
    private String id;
    private String text;
    private boolean isCorrect;

    public AnswerOption(String id, String text, boolean isCorrect) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public String toString() {
        return id + ") " + text + (isCorrect ? " [Correcta]" : "");
    }
}
