package taskdb.taskdb.application.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.comment.port.in.CommentDeleteUseCase;
import taskdb.taskdb.application.comment.port.in.CommentSaveUseCase;
import taskdb.taskdb.application.comment.port.in.CommentUpdateUseCase;
import taskdb.taskdb.application.comment.port.in.ReCommentSaveUseCase;
import taskdb.taskdb.application.comment.port.out.DeleteCommentPort;
import taskdb.taskdb.application.comment.port.out.GetCommentPort;
import taskdb.taskdb.application.comment.port.out.SaveCommentPort;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.domain.Content;
import taskdb.taskdb.application.comment.dto.CommentCreateRequestDto;
import taskdb.taskdb.application.comment.dto.CommentUpdateRequestDto;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.application.comment.dto.CommentMapper;
import taskdb.taskdb.domain.user.exception.DifferentUserException;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService implements
        CommentSaveUseCase, ReCommentSaveUseCase, CommentUpdateUseCase, CommentDeleteUseCase {
    private final GetUserPort getUserPort;
    private final GetQuestionPort getQuestionPort;
    private final CommentMapper commentMapper;
    private final SaveCommentPort saveCommentPort;
    private final GetCommentPort getCommentPort;
    private final DeleteCommentPort deleteCommentPort;

    @Override
    public void save(Long id, CommentCreateRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        Comment comment = commentMapper.of(requestDto, user, question);
        saveCommentPort.save(comment);
    }

    @Override
    public void save(Long questionId, Long parentId, CommentCreateRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(questionId);
        Comment parentComment = getCommentPort.getComment(parentId);
        Comment comment = commentMapper.of(requestDto, user, question, parentComment);
        saveCommentPort.save(comment);
    }

    @Override
    public void update(Long id, CommentUpdateRequestDto requestDto) {
        Comment comment = getCommentPort.getComment(id);
        checkDifferentUser(comment.getUser());
        updateContent(requestDto.getContent(), comment);
    }

    private void updateContent(String requestContent, Comment comment) {
        Content content = Content.of(requestContent);
        comment.update(content);
    }

    @Override
    public void delete(Long id) {
        Comment comment = getCommentPort.getComment(id);
        checkDifferentUser(comment.getUser());
        deleteCommentPort.delete(comment);
    }

    private void checkDifferentUser(User writer) {
        User user = getUserPort.getCurrentUser();
        if(user.isNotCorrectEmail(writer.getEmail())) {
            throw new DifferentUserException();
        }
    }
}
