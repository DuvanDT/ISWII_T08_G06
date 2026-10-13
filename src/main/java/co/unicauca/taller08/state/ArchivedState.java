package co.unicauca.taller08.state;

import co.unicauca.taller08.domain.Question;

public class ArchivedState implements QuestionState {

    @Override
    public void submitForReview(Question question) {
        throw new IllegalStateException("Transición no permitida: Una pregunta archivada no se puede enviar a revisión.");
    }

    @Override
    public void approve(Question question) {
        throw new IllegalStateException("Transición no permitida: Una pregunta archivada no se puede aprobar.");
    }

    @Override
    public void reject(Question question) {
        throw new IllegalStateException("Transición no permitida: Una pregunta archivada no se puede rechazar.");
    }

    @Override
    public void publish(Question question) {
        throw new IllegalStateException("Transición no permitida: Una pregunta archivada no se puede publicar.");
    }

    @Override
    public void archive(Question question) {
        throw new IllegalStateException("La pregunta ya está archivada.");
    }

    @Override
    public String getName() {
        return "Archivada";
    }
}
