package taskdb.taskdb.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.facade.QuestionFacade;
import taskdb.taskdb.domain.store.domain.QuestionStore;
import taskdb.taskdb.domain.store.domain.QuestionStoreRepository;
import taskdb.taskdb.domain.store.presentation.dto.response.StoreQuestionsResponseDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionStoreService {
    private final QuestionStoreRepository questionStoreRepository;
    private final UserFacade userFacade;
    private final QuestionFacade questionFacade;

    public void create(Long id) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        QuestionStore questionStore = QuestionStore.builder()
                .questionId(question.getId())
                .questionTitle(question.getTitle())
                .build();
        questionStore.confirmUser(user);
        questionStoreRepository.save(questionStore);
    }

    @Transactional(readOnly = true)
    public List<StoreQuestionsResponseDto> getQuestions() {
        return questionStoreRepository.findAll().stream()
                .map(StoreQuestionsResponseDto::new)
                .collect(Collectors.toList());
    }
}