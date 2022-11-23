package taskdb.taskdb.domain.questions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.domain.QuestionRepository;
import taskdb.taskdb.domain.questions.exception.QuestionException;
import taskdb.taskdb.domain.questions.exception.QuestionExceptionType;
import taskdb.taskdb.domain.questions.presentation.dto.request.QuestionCreateRequestDto;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionResponseDto;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionsResponseDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserFacade userFacade;

    public void create(QuestionCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = requestDto.toEntity();
        question.confirmUser(user);
        question.openQuestion();
        questionRepository.save(question);
    }

    @Transactional(readOnly = true)
    public List<QuestionsResponseDto> getQuestions() {
        return questionRepository.findAll().stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public QuestionResponseDto getQuestion(Long id) {
        return questionRepository.findById(id)
                .map(question -> {
                    question.addViewCount();
                    return QuestionResponseDto.builder()
                            .question(question)
                            .build();
                })
                .orElseThrow(() -> new QuestionException(QuestionExceptionType.NOT_FOUND_QUESTION));
    }
}
