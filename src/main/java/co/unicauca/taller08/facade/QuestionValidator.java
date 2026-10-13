package co.unicauca.taller08.facade;

import co.unicauca.taller08.domain.Question;

public class QuestionValidator {

    public void validate(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("La pregunta no puede ser nula.");
        }
        if (question.getId() == null || question.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("La pregunta debe tener un identificador válido.");
        }
        if (question.getStatement() == null || question.getStatement().trim().isEmpty()) {
            throw new IllegalArgumentException("El enunciado de la pregunta no puede estar vacío.");
        }
    }
}
