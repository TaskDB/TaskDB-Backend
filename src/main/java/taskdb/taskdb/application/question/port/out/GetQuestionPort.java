package taskdb.taskdb.application.question.port.out;

import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;

public interface GetQuestionPort {
    List<Question> getQuestions();
    Question getQuestion(Long id);
    List<Question> getQuestionsByStatus(String status);
    List<Question> getQuestionsByGrade(int grade);
    List<Question> getQuestionsByCategory(String category);
    List<Question> getQuestionsByKeyword(String keyword);
}
