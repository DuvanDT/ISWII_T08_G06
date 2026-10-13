package co.unicauca.taller08;

import co.unicauca.taller08.adapter.ExternalQuestionAdapter;
import co.unicauca.taller08.adapter.ExternalQuestionService;
import co.unicauca.taller08.decorator.*;
import co.unicauca.taller08.domain.AnswerOption;
import co.unicauca.taller08.domain.Question;
import co.unicauca.taller08.domain.QuestionType;
import co.unicauca.taller08.facade.QuestionBankFacade;

import java.util.Arrays;
import java.util.List;

public class ApplicationMain {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("  BANCO DE PREGUNTAS SABER PRO - TALLER 8: PATRONES DE DISEÑO");
        System.out.println("===============================================================\n");

        QuestionBankFacade facade = new QuestionBankFacade();

        // 1. REQUERIMIENTO: Crear preguntas de Selección Única, Selección Múltiple y Verdadero/Falso
        System.out.println("--- 1. CREACIÓN DE PREGUNTAS ---");
        Question q1 = new Question("Q-101", "¿Cuál es el patrón que permite definir un algoritmo plantilla?", QuestionType.SINGLE_CHOICE);
        q1.addOption(new AnswerOption("A", "State", false));
        q1.addOption(new AnswerOption("B", "Template Method", true));
        q1.addOption(new AnswerOption("C", "Facade", false));
        facade.createQuestion(q1);

        Question q2 = new Question("Q-102", "Seleccione los patrones de diseño estructurales:", QuestionType.MULTIPLE_CHOICE);
        q2.addOption(new AnswerOption("A", "Decorator", true));
        q2.addOption(new AnswerOption("B", "Adapter", true));
        q2.addOption(new AnswerOption("C", "State", false));
        facade.createQuestion(q2);

        Question q3 = new Question("Q-103", "El patrón Facade permite agregar responsabilidades dinámicamente.", QuestionType.TRUE_FALSE);
        q3.setCorrectAnswers(List.of("Falso"));
        facade.createQuestion(q3);

        // 2. REQUERIMIENTO: Demostración del Patrón State (Ciclo de Vida)
        System.out.println("\n--- 2. DEMOSTRACIÓN PATRÓN STATE (CICLO DE VIDA) ---");
        System.out.println("Estado inicial de Q-101: " + q1.getState().getName());

        facade.submitForReview("Q-101"); // Draft -> PendingReview
        facade.submitForReview("Q-101"); // PendingReview -> Review
        facade.approveQuestion("Q-101"); // Review -> Approved
        facade.publishQuestion("Q-101"); // Approved -> Published

        System.out.println("Estado final de Q-101: " + q1.getState().getName());

        // Intento de transición no permitida (Publicada -> Borrador)
        try {
            facade.submitForReview("Q-101");
        } catch (IllegalStateException e) {
            System.out.println("  ✓ Transición bloqueada correctamente por Patrón State: " + e.getMessage());
        }

        // 3. REQUERIMIENTO: Demostración del Patrón Template Method (Evaluación)
        System.out.println("\n--- 3. DEMOSTRACIÓN PATRÓN TEMPLATE METHOD (EVALUACIÓN) ---");

        // Evaluando Selección Única
        boolean resQ1_Correct = facade.evaluateQuestion("Q-101", List.of("B"));
        System.out.println("Evaluación Q-101 con opción 'B' (Correcta): " + resQ1_Correct);

        boolean resQ1_Incorrect = facade.evaluateQuestion("Q-101", List.of("A"));
        System.out.println("Evaluación Q-101 con opción 'A' (Incorrecta): " + resQ1_Incorrect);

        // Evaluando Selección Múltiple
        boolean resQ2_Correct = facade.evaluateQuestion("Q-102", Arrays.asList("A", "B"));
        System.out.println("Evaluación Q-102 con opciones ['A', 'B'] (Correcta): " + resQ2_Correct);

        // Evaluando Verdadero/Falso
        boolean resQ3_Correct = facade.evaluateQuestion("Q-103", List.of("Falso"));
        System.out.println("Evaluación Q-103 con opción 'Falso' (Correcta): " + resQ3_Correct);

        // 4. REQUERIMIENTO: Demostración del Patrón Decorator (Propiedades dinámicas)
        System.out.println("\n--- 4. DEMOSTRACIÓN PATRÓN DECORATOR ---");
        QuestionComponent questionComp = facade.createBasicDecoratorComponent("Q-101");
        System.out.println("1. Pregunta Básica: " + questionComp.getDescription() + " | Puntos: " + questionComp.getScore());

        // Aplicando decoradores en cadena
        questionComp = new ContextualizedQuestion(questionComp, "Evaluación Final ISW II");
        questionComp = new HighDifficultyQuestion(questionComp);
        questionComp = new FeedbackQuestion(questionComp, "Revisar capítulo 4 del libro GoF.");

        System.out.println("2. Pregunta Decorada Anidada:");
        System.out.println("   Descripción: " + questionComp.getDescription());
        System.out.println("   Puntuación calculada: " + questionComp.getScore());

        // 5. REQUERIMIENTO: Demostración del Patrón Adapter (Importar preguntas externas)
        System.out.println("\n--- 5. DEMOSTRACIÓN PATRÓN ADAPTER ---");
        ExternalQuestionService externalService = new ExternalQuestionService();
        ExternalQuestionAdapter adapter = new ExternalQuestionAdapter(externalService);

        Question importedQ = facade.importExternalQuestion(adapter);
        System.out.println("Pregunta importada desde servicio externo: " + importedQ);

        // Evaluando la pregunta importada
        boolean resImported = facade.evaluateQuestion(importedQ.getId(), List.of("B"));
        System.out.println("Evaluación de pregunta importada con respuesta 'B': " + resImported);

        // 6. CONSULTAR TODAS LAS PREGUNTAS EN EL SISTEMA
        System.out.println("\n--- 6. CONSULTA GENERAL EN EL BANCO DE PREGUNTAS (FACADE) ---");
        List<Question> allQuestions = facade.listAllQuestions();
        System.out.println("Total de preguntas registradas: " + allQuestions.size());
        for (Question q : allQuestions) {
            System.out.println("  - " + q);
        }

        System.out.println("\n===============================================================");
        System.out.println("  ✅ EJECUCIÓN DEL BANCO DE PREGUNTAS FINALIZADA CON ÉXITO");
        System.out.println("===============================================================");
    }
}
