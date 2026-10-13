package co.unicauca.taller08.state;

import co.unicauca.taller08.domain.Question;

public class PublishedState implements QuestionState {

    @Override
    public void submitForReview(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede enviar a revisión una pregunta que ya fue publicada.");
    }

    @Override
    public void approve(Question question) {
        throw new IllegalStateException("La pregunta ya está publicada.");
    }

    @Override
    public void reject(Question question) {
        throw new IllegalStateException("Transición no permitida: No se puede rechazar una pregunta publicada.");
    }

    @Override
    public void publish(Question question) {
        throw new IllegalStateException("La pregunta ya está publicada.");
    }

    @Override
    public void archive(Question question) {
        question.setState(new ArchivedState());
        System.out.println("La pregunta " + question.getId() + " ha sido archivada: Publicada -> Archivada.");
    }

    @Override
    public String getName() {
        return "Publicada";
    }
}
