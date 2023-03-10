package taskdb.taskdb.adapter.comment.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.comment.port.out.DeleteCommentPort;
import taskdb.taskdb.application.comment.port.out.GetCommentPort;
import taskdb.taskdb.application.comment.port.out.SaveCommentPort;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.exception.CommentNotFoundException;

@Component
@RequiredArgsConstructor
public class CommentAdapter implements SaveCommentPort, GetCommentPort, DeleteCommentPort {
    private final CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public Comment delete(Comment comment) {
        commentRepository.delete(comment);
        return comment;
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
    }
}
