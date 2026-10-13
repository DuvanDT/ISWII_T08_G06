package co.unicauca.taller08;

import co.unicauca.taller08.domain.AnswerOption;
import co.unicauca.taller08.domain.Question;
import co.unicauca.taller08.domain.QuestionType;
import co.unicauca.taller08.templatemethod.MultipleChoiceEvaluator;
import co.unicauca.taller08.templatemethod.QuestionEvaluator;
import co.unicauca.taller08.templatemethod.SingleChoiceEvaluator;
import co.unicauca.taller08.templatemethod.TrueFalseEvaluator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StateAndTemplateMethodTest {

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DE LA ETAPA 1: PATRÓN STATE ===");
        testStatePatternTransitions();
        testInvalidStateTransitions();

        System.out.println("\n=== PRUEBAS DE LA ETAPA 2: PATRÓN TEMPLATE METHOD ===");
        testSingleChoiceEvaluator();
        testMultipleChoiceEvaluator();
        testTrueFalseEvaluator();

        System.out.println("\n✅ ¡TODAS LAS PRUEBAS DE LA ETAPA 1 Y 2 EJECUTADAS EXITOSAMENTE!");
    }

    private static void testStatePatternTransitions() {
        System.out.println("\n--- Probando Flujo Válido de Transición de Estados ---");
        Question q = new Question("Q1", "¿Cuál es el patrón que define un ciclo de vida?", QuestionType.SINGLE_CHOICE);
        assertState(q, "Borrador");

        // Transición 1: Borrador -> Pendiente de revisión
        q.submitForReview();
        assertState(q, "Pendiente de revisión");

        // Transición 2: Pendiente de revisión -> En revisión
        q.submitForReview();
        assertState(q, "En revisión");

        // Transición 3: En revisión -> Aprobada
        q.approve();
        assertState(q, "Aprobada");

        // Transición 4: Aprobada -> Publicada
        q.publish();
        assertState(q, "Publicada");

        // Transición 5: Publicada -> Archivada
        q.archive();
        assertState(q, "Archivada");
    }

    private static void testInvalidStateTransitions() {
        System.out.println("\n--- Probando Transiciones Inválidas (Bloqueos) ---");

        // Caso 1: Borrador -> Publicada (No permitido)
        Question q1 = new Question("Q2", "Pregunta de prueba inválida", QuestionType.SINGLE_CHOICE);
        try {
            q1.publish();
            throw new RuntimeException("ERROR: No debió permitir publicar en Borrador");
        } catch (IllegalStateException e) {
            System.out.println("  ✓ Bloqueo correcto (Borrador -> Publicada): " + e.getMessage());
        }

        // Caso 2: Publicada -> Borrador (No permitido)
        Question q2 = new Question("Q3", "Pregunta publicada", QuestionType.SINGLE_CHOICE);
        q2.submitForReview(); // a Pendiente
        q2.submitForReview(); // a En revisión
        q2.approve();         // a Aprobada
        q2.publish();         // a Publicada

        try {
            q2.submitForReview();
            throw new RuntimeException("ERROR: No debió permitir volver a revisión estando Publicada");
        } catch (IllegalStateException e) {
            System.out.println("  ✓ Bloqueo correcto (Publicada -> Borrador/Revisión): " + e.getMessage());
        }

        // Caso 3: Rechazo de pregunta
        Question q3 = new Question("Q4", "Pregunta para rechazar", QuestionType.SINGLE_CHOICE);
        q3.submitForReview();
        q3.submitForReview(); // En revisión
        q3.reject();          // Rechazada
        assertState(q3, "Rechazada");
        q3.submitForReview(); // Regresa a Borrador
        assertState(q3, "Borrador");
    }

    private static void testSingleChoiceEvaluator() {
        System.out.println("\n--- Probando SingleChoiceEvaluator ---");
        Question q = new Question("Q10", "¿Cuál es la capital de Colombia?", QuestionType.SINGLE_CHOICE);
        q.addOption(new AnswerOption("A", "Medellín", false));
        q.addOption(new AnswerOption("B", "Bogotá", true));
        q.addOption(new AnswerOption("C", "Cali", false));

        QuestionEvaluator evaluator = new SingleChoiceEvaluator();

        boolean correctResult = evaluator.evaluate(q, List.of("B"));
        System.out.println("  ✓ Evaluación opción correcta ('B'): " + correctResult);
        if (!correctResult) throw new RuntimeException("Evaluación fallida para la opción correcta.");

        boolean incorrectResult = evaluator.evaluate(q, List.of("A"));
        System.out.println("  ✓ Evaluación opción incorrecta ('A'): " + incorrectResult);
        if (incorrectResult) throw new RuntimeException("Evaluación fallida para la opción incorrecta.");

        // Probar validación: más de una opción en selección única
        try {
            evaluator.evaluate(q, Arrays.asList("A", "B"));
            throw new RuntimeException("ERROR: Debió rechazar múltiples respuestas en selección única.");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✓ Validación correcta para selección única con >1 respuestas: " + e.getMessage());
        }
    }

    private static void testMultipleChoiceEvaluator() {
        System.out.println("\n--- Probando MultipleChoiceEvaluator ---");
        Question q = new Question("Q20", "¿Cuáles son lenguajes con tipado estático?", QuestionType.MULTIPLE_CHOICE);
        q.addOption(new AnswerOption("A", "Java", true));
        q.addOption(new AnswerOption("B", "C++", true));
        q.addOption(new AnswerOption("C", "Python", false));

        QuestionEvaluator evaluator = new MultipleChoiceEvaluator();

        // Respuestas correctas exactas: A y B
        boolean correctResult = evaluator.evaluate(q, Arrays.asList("A", "B"));
        System.out.println("  ✓ Respuesta correcta ('A', 'B'): " + correctResult);
        if (!correctResult) throw new RuntimeException("Evaluación de selección múltiple falló.");

        // Incompleta: solo A
        boolean incompleteResult = evaluator.evaluate(q, List.of("A"));
        System.out.println("  ✓ Respuesta incompleta ('A'): " + incompleteResult);
        if (incompleteResult) throw new RuntimeException("Respuesta incompleta no debe ser aprobada.");

        // Con opción incorrecta extra: A, B, C
        boolean extraResult = evaluator.evaluate(q, Arrays.asList("A", "B", "C"));
        System.out.println("  ✓ Respuesta con opción incorrecta extra: " + extraResult);
        if (extraResult) throw new RuntimeException("Respuesta con opción incorrecta no debe ser aprobada.");
    }

    private static void testTrueFalseEvaluator() {
        System.out.println("\n--- Probando TrueFalseEvaluator ---");
        Question q = new Question("Q30", "Java apoya herencia múltiple de clases.", QuestionType.TRUE_FALSE);
        q.setCorrectAnswers(List.of("Falso"));

        QuestionEvaluator evaluator = new TrueFalseEvaluator();

        boolean resultCorrect = evaluator.evaluate(q, List.of("Falso"));
        System.out.println("  ✓ Respuesta correcta ('Falso'): " + resultCorrect);
        if (!resultCorrect) throw new RuntimeException("Evaluación Verdadero/Falso falló.");

        boolean resultIncorrect = evaluator.evaluate(q, List.of("Verdadero"));
        System.out.println("  ✓ Respuesta incorrecta ('Verdadero'): " + resultIncorrect);
        if (resultIncorrect) throw new RuntimeException("Respuesta incorrecta fue aceptada.");
    }

    private static void assertState(Question q, String expectedStateName) {
        if (!q.getState().getName().equals(expectedStateName)) {
            throw new RuntimeException("Estado esperado: " + expectedStateName + ", pero fue: " + q.getState().getName());
        }
        System.out.println("  ✓ Estado verificado correctamente: " + q.getState().getName());
    }
}
