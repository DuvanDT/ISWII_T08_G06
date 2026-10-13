package co.unicauca.taller08;

import co.unicauca.taller08.adapter.ExternalQuestionAdapter;
import co.unicauca.taller08.adapter.ExternalQuestionService;
import co.unicauca.taller08.adapter.QuestionProvider;
import co.unicauca.taller08.decorator.*;
import co.unicauca.taller08.domain.Question;

public class DecoratorAndAdapterTest {

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DE LA ETAPA 3: PATRÓN DECORATOR ===");
        testDecoratorPattern();

        System.out.println("\n=== PRUEBAS DE LA ETAPA 3: PATRÓN ADAPTER ===");
        testAdapterPattern();

        System.out.println("\n✅ ¡TODAS LAS PRUEBAS DE LA ETAPA 3 EJECUTADAS EXITOSAMENTE!");
    }

    private static void testDecoratorPattern() {
        System.out.println("\n--- Probando Decoración Básica y Anidada ---");

        // 1. Pregunta básica
        QuestionComponent q = new BasicQuestion("¿Qué es un patrón de diseño?", 1.0);
        System.out.println("Básica: " + q.getDescription() + " | Puntos: " + q.getScore());
        if (q.getScore() != 1.0) throw new RuntimeException("Error en puntaje básico");

        // 2. Decorada con Contexto
        q = new ContextualizedQuestion(q, "En el marco de la Arquitectura de Software");
        System.out.println("Decorada (+Contexto): " + q.getDescription() + " | Puntos: " + q.getScore());

        // 3. Decorada con Alta Dificultad (+0.5 puntos)
        q = new HighDifficultyQuestion(q);
        System.out.println("Decorada (+Dificultad): " + q.getDescription() + " | Puntos: " + q.getScore());
        if (q.getScore() != 1.5) throw new RuntimeException("Error en puntaje decorado por Alta Dificultad.");

        // 4. Decorada con Retroalimentación
        q = new FeedbackQuestion(q, "Revisar la definición estándar del GoF.");
        System.out.println("Decorada (+Retroalimentación): " + q.getDescription() + " | Puntos: " + q.getScore());

        if (!q.getDescription().contains("[CONTEXTO:") || !q.getDescription().contains("[ALTA DIFICULTAD]") || !q.getDescription().contains("[RETROALIMENTACIÓN:")) {
            throw new RuntimeException("Error en la acumulación de descripciones decoradas.");
        }
        System.out.println("  ✓ Todos los decoradores combinados correctamente.");
    }

    private static void testAdapterPattern() {
        System.out.println("\n--- Probando Adaptación de Preguntas Externas ---");

        ExternalQuestionService externalService = new ExternalQuestionService();
        QuestionProvider adapter = new ExternalQuestionAdapter(externalService);

        Question adaptedQuestion = adapter.getQuestion();
        System.out.println("Pregunta adaptada: " + adaptedQuestion);
        System.out.println("  - Enunciado: " + adaptedQuestion.getStatement());
        System.out.println("  - Opciones de respuesta: " + adaptedQuestion.getOptions());
        System.out.println("  - Respuestas correctas: " + adaptedQuestion.getCorrectAnswers());

        if (!"EXT-101".equals(adaptedQuestion.getId())) throw new RuntimeException("ID de pregunta adaptada incorrecto.");
        if (!adaptedQuestion.getCorrectAnswers().contains("B")) throw new RuntimeException("Respuesta correcta adaptada incorrecta.");

        System.out.println("  ✓ Adaptador convirtió correctamente el formato externo al dominio interno Question.");
    }
}
