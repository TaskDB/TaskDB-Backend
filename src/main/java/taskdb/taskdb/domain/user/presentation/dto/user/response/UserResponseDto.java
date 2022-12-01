package taskdb.taskdb.domain.user.presentation.dto.user.response;

import lombok.Getter;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionsResponseDto;
import taskdb.taskdb.domain.store.presentation.dto.response.StoreQuestionsResponseDto;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;

@Getter
public class UserResponseDto {
    private final String nickname;
    private final int contributionLevel;
    private final List<QuestionsResponseDto> getMyQuestions;
    private final List<StoreQuestionsResponseDto> getSavedQuestions;

    public UserResponseDto(User user) {
        this.nickname = user.getNickname();
        this.contributionLevel = user.getContributionLevel();
        this.getMyQuestions = user.toQuestionsResponseDto();
        this.getSavedQuestions = user.toStoreQuestionsResponseDto();
    }
}
