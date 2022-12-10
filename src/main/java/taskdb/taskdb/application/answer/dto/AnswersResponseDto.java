package taskdb.taskdb.application.answer.dto;

import lombok.Getter;
import taskdb.taskdb.domain.answer.domain.Answer;

import java.time.LocalDateTime;

@Getter
public class AnswersResponseDto {
    private String content;
    private String userImage;
    private String nickname;
    private int likeCount;
    private LocalDateTime createdDate;

    public AnswersResponseDto() {
    }

    public AnswersResponseDto(Answer answer) {
        this.content = answer.getContent();
        this.userImage = answer.getUser().getImgUrl();
        this.nickname = answer.getUser().getNickname();
        this.likeCount = answer.getLikeCount();
        this.createdDate = answer.getCreatedDate();
    }
}