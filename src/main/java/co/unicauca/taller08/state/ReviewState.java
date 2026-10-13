package co.unicauca.taller08.state;

import co.unicauca.taller08.domain.Question;

public class ReviewState implements QuestionState {

    @Override
    public void submitForReview(Question question) {
        throw new IllegalStateException("La pregunta ya se encuentra en proceso de revisión.");
    }

    @Override
    public void approve(Question question) {
        question.setState(new ApprovedState());
        System.out.println("La pregunta " + question.getId() + " ha sido aprobada por el revisor: En revisión -> Aprobada.");
    }

    @Override
    public void reject(Question question) {
        question.setState(new RejectedState());
        System.out.println("La pregunta " + question.getId() + " ha sido rechazada por el revisor: En revisión -> Rechazada.");
    }

    @Override
    public void publish(Question question) {
        throw new IllegalStateException("Transición no permitida: Una pregunta en revisión debe ser aprobada antes de ser publicada.");
    }

    @Override
    public void archive(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede archivar una pregunta durante la revisión.");
    }

    @Override
    public String getName() {
        return "En revisión";
    }
}
