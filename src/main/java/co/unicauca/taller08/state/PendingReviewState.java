package co.unicauca.taller08.state;

import co.unicauca.taller08.domain.Question;

public class PendingReviewState implements QuestionState {

    @Override
    public void submitForReview(Question question) {
        question.setState(new ReviewState());
        System.out.println("Se ha asignado un revisor a la pregunta " + question.getId() + ". Estado: Pendiente de revisión -> En revisión.");
    }

    @Override
    public void approve(Question question) {
        throw new IllegalStateException("Transición no permitida: Debe pasar por el estado En revisión antes de ser aprobada.");
    }

    @Override
    public void reject(Question question) {
        throw new IllegalStateException("Transición no permitida: Debe asignarse un revisor y evaluarse en estado En revisión.");
    }

    @Override
    public void publish(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede publicar una pregunta pendiente de revisión.");
    }

    @Override
    public void archive(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede archivar una pregunta pendiente de revisión.");
    }

    @Override
    public String getName() {
        return "Pendiente de revisión";
    }
}
