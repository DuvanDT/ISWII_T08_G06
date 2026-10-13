package co.unicauca.taller08.state;

import co.unicauca.taller08.domain.Question;

public class RejectedState implements QuestionState {

    @Override
    public void submitForReview(Question question) {
        question.setState(new DraftState());
        System.out.println("La pregunta " + question.getId() + " vuelve a estado Borrador para correcciones.");
    }

    @Override
    public void approve(Question question) {
        throw new IllegalStateException("Transición no permitida: Una pregunta rechazada debe ser corregida en Borrador antes de ser aprobada.");
    }

    @Override
    public void reject(Question question) {
        throw new IllegalStateException("La pregunta ya ha sido rechazada.");
    }

    @Override
    public void publish(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede publicar una pregunta rechazada.");
    }

    @Override
    public void archive(Question question) {
        question.setState(new ArchivedState());
        System.out.println("La pregunta rechazada " + question.getId() + " ha sido archivada.");
    }

    @Override
    public String getName() {
        return "Rechazada";
    }
}
