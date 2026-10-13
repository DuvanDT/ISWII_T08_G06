package co.unicauca.taller08;

import co.unicauca.taller08.adapter.ExternalQuestionAdapter;
import co.unicauca.taller08.adapter.ExternalQuestionService;
import co.unicauca.taller08.decorator.HighDifficultyQuestion;
import co.unicauca.taller08.decorator.QuestionComponent;
import co.unicauca.taller08.domain.AnswerOption;
import co.unicauca.taller08.domain.Question;
import co.unicauca.taller08.domain.QuestionType;
import co.unicauca.taller08.facade.QuestionBankFacade;

import java.util.List;

public class FacadeAndIntegrationTest {

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DE INTEGRACIÓN FACADE Y FLUJO COMPLETO DE ETAPA 4 ===");

        QuestionBankFacade facade = new QuestionBankFacade();

        // 1. Probar Facade creación y consulta
        Question q = new Question("INTEG-1", "Pregunta de integración", QuestionType.SINGLE_CHOICE);
        q.addOption(new AnswerOption("A", "Opción A", true));
        q.addOption(new AnswerOption("B", "Opción B", false));
        facade.createQuestion(q);

        Question retrieved = facade.findQuestionById("INTEG-1")
                .orElseThrow(() -> new RuntimeException("No se pudo recuperar la pregunta creada."));
        if (!"INTEG-1".equals(retrieved.getId())) throw new RuntimeException("ID no coincide.");

        // 2. Probar evaluación vía Facade
        boolean evalResult = facade.evaluateQuestion("INTEG-1", List.of("A"));
        if (!evalResult) throw new RuntimeException("Error en evaluación mediante Facade.");

        // 3. Probar transiciones de estado vía Facade
        facade.submitForReview("INTEG-1"); // a Pendiente
        facade.submitForReview("INTEG-1"); // a En revisión
        facade.approveQuestion("INTEG-1"); // a Aprobada
        facade.publishQuestion("INTEG-1"); // a Publicada

        if (!"Publicada".equals(retrieved.getState().getName())) {
            throw new RuntimeException("Estado publicado no se reflejó correctamente.");
        }

        // 4. Probar integración con Decorator vía Facade
        QuestionComponent comp = facade.createBasicDecoratorComponent("INTEG-1");
        comp = new HighDifficultyQuestion(comp);
        if (!comp.getDescription().contains("[ALTA DIFICULTAD]") || comp.getScore() != 1.5) {
            throw new RuntimeException("Error al aplicar decorador mediante Facade.");
        }

        // 5. Probar importación vía Facade
        ExternalQuestionService externalService = new ExternalQuestionService();
        ExternalQuestionAdapter adapter = new ExternalQuestionAdapter(externalService);
        Question imported = facade.importExternalQuestion(adapter);
        if (imported == null || facade.listAllQuestions().size() < 2) {
            throw new RuntimeException("Error al importar pregunta externa mediante Facade.");
        }

        System.out.println("\n✅ ¡TODAS LAS PRUEBAS DE INTEGRACIÓN DE LA ETAPA 4 FINALIZARON CON ÉXITO!");
    }
}
