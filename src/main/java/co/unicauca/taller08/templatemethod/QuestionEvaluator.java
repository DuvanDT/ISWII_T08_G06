package co.unicauca.taller08.templatemethod;

import co.unicauca.taller08.domain.Question;

import java.util.List;

public abstract class QuestionEvaluator {

    /**
     * Algoritmo plantilla (Template Method) para evaluar la respuesta a una pregunta.
     * Define el esqueleto general de ejecución y delega los pasos variables a las subclases.
     */
    public final boolean evaluate(Question question, List<String> answers) {
        validate(question, answers);
        return compareAnswers(question, answers);
    }

    /**
     * Paso del algoritmo: Validar formato y restricciones de la respuesta según el tipo de pregunta.
     */
    protected abstract void validate(Question question, List<String> answers);

    /**
     * Paso del algoritmo: Comparar las respuestas proporcionadas por el estudiante con las respuestas correctas.
     */
    protected abstract boolean compareAnswers(Question question, List<String> answers);
}
