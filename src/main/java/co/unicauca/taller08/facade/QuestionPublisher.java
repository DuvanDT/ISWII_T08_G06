package co.unicauca.taller08.facade;

import co.unicauca.taller08.domain.Question;

public class QuestionPublisher {

    public void publish(Question question) {
        question.publish();
        System.out.println("Pregunta " + question.getId() + " fue publicada exitosamente en el Banco de Preguntas.");
    }

    public void archive(Question question) {
        question.archive();
        System.out.println("Pregunta " + question.getId() + " fue archivada exitosamente.");
    }
}
