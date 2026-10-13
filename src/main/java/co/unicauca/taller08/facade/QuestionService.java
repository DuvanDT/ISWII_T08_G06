package co.unicauca.taller08.facade;

import co.unicauca.taller08.domain.Question;

import java.util.List;
import java.util.Optional;

public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public void save(Question question) {
        repository.save(question);
    }

    public Optional<Question> findById(String id) {
        return repository.findById(id);
    }

    public List<Question> findAll() {
        return repository.findAll();
    }
}
