package co.unicauca.taller08.facade;

import co.unicauca.taller08.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionRepository {
    private final List<Question> database = new ArrayList<>();

    public void save(Question question) {
        database.removeIf(q -> q.getId().equals(question.getId()));
        database.add(question);
    }

    public Optional<Question> findById(String id) {
        return database.stream().filter(q -> q.getId().equals(id)).findFirst();
    }

    public List<Question> findAll() {
        return new ArrayList<>(database);
    }
}
