package co.unicauca.taller08.facade;

import co.unicauca.taller08.adapter.QuestionProvider;
import co.unicauca.taller08.decorator.BasicQuestion;
import co.unicauca.taller08.decorator.QuestionComponent;
import co.unicauca.taller08.domain.Question;
import co.unicauca.taller08.domain.QuestionType;
import co.unicauca.taller08.templatemethod.MultipleChoiceEvaluator;
import co.unicauca.taller08.templatemethod.QuestionEvaluator;
import co.unicauca.taller08.templatemethod.SingleChoiceEvaluator;
import co.unicauca.taller08.templatemethod.TrueFalseEvaluator;

import java.util.List;
import java.util.Optional;

public class QuestionBankFacade {
    private final QuestionService questionService;
    private final QuestionValidator validator;
    private final QuestionPublisher publisher;
    private final QuestionImporter importer;

    public QuestionBankFacade() {
        QuestionRepository repository = new QuestionRepository();
        this.questionService = new QuestionService(repository);
        this.validator = new QuestionValidator();
        this.publisher = new QuestionPublisher();
        this.importer = new QuestionImporter();
    }

    /**
     * 1. Crear / Guardar una pregunta en el Banco de Preguntas.
     */
    public void createQuestion(Question question) {
        validator.validate(question);
        questionService.save(question);
        System.out.println("Pregunta guardada en el Banco: " + question.getId());
    }

    /**
     * 2. Consultar pregunta por ID.
     */
    public Optional<Question> findQuestionById(String id) {
        return questionService.findById(id);
    }

    /**
     * 3. Consultar todas las preguntas.
     */
    public List<Question> listAllQuestions() {
        return questionService.findAll();
    }

    /**
     * 4. Transiciones de estado simplificadas.
     */
    public void submitForReview(String questionId) {
        Question q = getQuestionOrThrow(questionId);
        q.submitForReview(); // Transición de estado mediante Patrón State
    }

    public void approveQuestion(String questionId) {
        Question q = getQuestionOrThrow(questionId);
        q.approve();
    }

    public void rejectQuestion(String questionId) {
        Question q = getQuestionOrThrow(questionId);
        q.reject();
    }

    public void publishQuestion(String questionId) {
        Question q = getQuestionOrThrow(questionId);
        validator.validate(q);
        publisher.publish(q);
    }

    public void archiveQuestion(String questionId) {
        Question q = getQuestionOrThrow(questionId);
        publisher.archive(q);
    }

    /**
     * 5. Evaluar respuestas de una pregunta utilizando Patrón Template Method.
     */
    public boolean evaluateQuestion(String questionId, List<String> studentAnswers) {
        Question q = getQuestionOrThrow(questionId);

        QuestionEvaluator evaluator;
        if (q.getType() == QuestionType.SINGLE_CHOICE) {
            evaluator = new SingleChoiceEvaluator();
        } else if (q.getType() == QuestionType.MULTIPLE_CHOICE) {
            evaluator = new MultipleChoiceEvaluator();
        } else if (q.getType() == QuestionType.TRUE_FALSE) {
            evaluator = new TrueFalseEvaluator();
        } else {
            throw new UnsupportedOperationException("Tipo de pregunta no soportado para evaluación.");
        }

        return evaluator.evaluate(q, studentAnswers);
    }

    /**
     * 6. Importar preguntas externas utilizando Patrón Adapter.
     */
    public Question importExternalQuestion(QuestionProvider provider) {
        Question q = importer.importQuestion(provider);
        createQuestion(q);
        return q;
    }

    /**
     * 7. Crear un componente decorado mediante el Patrón Decorator.
     */
    public QuestionComponent createBasicDecoratorComponent(String questionId) {
        Question q = getQuestionOrThrow(questionId);
        return new BasicQuestion(q.getStatement());
    }

    private Question getQuestionOrThrow(String id) {
        return questionService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la pregunta con ID: " + id));
    }
}
