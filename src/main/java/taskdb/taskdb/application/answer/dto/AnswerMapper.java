package taskdb.taskdb.application.answer.dto;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.domain.Content;
import taskdb.taskdb.application.answer.dto.AnswerCreateRequestDto;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;

@Component
public class AnswerMapper {
    public Answer of(AnswerCreateRequestDto requestDto, User user, Question question) {
        Answer answer = Answer.builder()
                .content(Content.of(requestDto.getContent()))
                .build();
        answer.confirmUser(user);
        answer.confirmQuestion(question);
        return answer;
    }
}
