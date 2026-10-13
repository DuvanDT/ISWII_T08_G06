package co.unicauca.taller08.state;

import co.unicauca.taller08.domain.Question;

public class ApprovedState implements QuestionState {

    @Override
    public void submitForReview(Question question) {
        throw new IllegalStateException("La pregunta ya está aprobada.");
    }

    @Override
    public void approve(Question question) {
        throw new IllegalStateException("La pregunta ya se encuentra aprobada.");
    }

    @Override
    public void reject(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede rechazar una pregunta que ya fue aprobada.");
    }

    @Override
    public void publish(Question question) {
        question.setState(new PublishedState());
        System.out.println("La pregunta " + question.getId() + " ha sido publicada: Aprobada -> Publicada.");
    }

    @Override
    public void archive(Question question) {
        question.setState(new ArchivedState());
        System.out.println("La pregunta " + question.getId() + " ha sido archivada desde estado Aprobada.");
    }

    @Override
    public String getName() {
        return "Aprobada";
    }
}
