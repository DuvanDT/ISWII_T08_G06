package co.unicauca.taller08.state;

import co.unicauca.taller08.domain.Question;

public class DraftState implements QuestionState {

    @Override
    public void submitForReview(Question question) {
        question.setState(new PendingReviewState());
        System.out.println("La pregunta " + question.getId() + " cambió de estado: Borrador -> Pendiente de revisión.");
    }

    @Override
    public void approve(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede aprobar una pregunta desde el estado Borrador.");
    }

    @Override
    public void reject(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede rechazar una pregunta en Borrador.");
    }

    @Override
    public void publish(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede publicar una pregunta en Borrador.");
    }

    @Override
    public void archive(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede archivar una pregunta directamente desde Borrador.");
    }

    @Override
    public String getName() {
        return "Borrador";
    }
}
