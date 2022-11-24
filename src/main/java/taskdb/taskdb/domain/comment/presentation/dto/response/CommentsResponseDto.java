package taskdb.taskdb.domain.comment.presentation.dto.response;

import lombok.Getter;
import taskdb.taskdb.domain.comment.domain.Comment;

@Getter
public class CommentsResponseDto {
    private final String content;
    private final String nickname;
    //등록한 날짜

    public CommentsResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.nickname = comment.getUser().getNickname();
    }
}