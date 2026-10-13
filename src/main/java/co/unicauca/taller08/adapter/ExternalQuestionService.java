package co.unicauca.taller08.adapter;

public class ExternalQuestionService {

    /**
     * Simula la respuesta de un servicio externo devolviendo datos en un formato plano / JSON.
     */
    public String getQuestionData() {
        return "ID: EXT-101 | Enunciado: ¿Cuál es la capital de Colombia? | Opciones: A) Medellín, B) Bogotá, C) Cali | Correcta: B";
    }

    public String getJsonQuestionData() {
        return "{\n" +
               "  \"id\": \"EXT-102\",\n" +
               "  \"statement\": \"¿Qué patrón oculta la complejidad de subsistemas?\",\n" +
               "  \"type\": \"SINGLE_CHOICE\",\n" +
               "  \"answer\": \"Facade\"\n" +
               "}";
    }
}
