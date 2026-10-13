package co.unicauca.taller08.facade;

import co.unicauca.taller08.adapter.QuestionProvider;
import co.unicauca.taller08.domain.Question;

public class QuestionImporter {

    public Question importQuestion(QuestionProvider provider) {
        Question importedQuestion = provider.getQuestion();
        System.out.println("Importación realizada con éxito para la pregunta: " + importedQuestion.getId());
        return importedQuestion;
    }
}
