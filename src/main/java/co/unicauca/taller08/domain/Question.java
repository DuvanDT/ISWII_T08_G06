package co.unicauca.taller08.domain;

import co.unicauca.taller08.state.DraftState;
import co.unicauca.taller08.state.QuestionState;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String id;
    private String statement;
    private QuestionType type;
    private QuestionState state;
    private List<AnswerOption> options;
    private List<String> correctAnswers;

    public Question() {
        this.options = new ArrayList<>();
        this.correctAnswers = new ArrayList<>();
        this.state = new DraftState();
    }

    public Question(String id, String statement, QuestionType type) {
        this();
        this.id = id;
        this.statement = statement;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public QuestionState getState() {
        return state;
    }

    public void setState(QuestionState state) {
        this.state = state;
    }

    public List<AnswerOption> getOptions() {
        return options;
    }

    public void addOption(AnswerOption option) {
        this.options.add(option);
        if (option.isCorrect()) {
            this.correctAnswers.add(option.getId());
        }
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    // State transition delegation methods
    public void submitForReview() {
        state.submitForReview(this);
    }

    public void approve() {
        state.approve(this);
    }

    public void reject() {
        state.reject(this);
    }

    public void publish() {
        state.publish(this);
    }

    public void archive() {
        state.archive(this);
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", type=" + type + ", state=" + state.getName() + ", statement=" + statement + "]";
    }
}
