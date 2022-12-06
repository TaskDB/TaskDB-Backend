package taskdb.taskdb.domain.comment.port;

import taskdb.taskdb.domain.comment.domain.Comment;

public interface CommentStore {
    Comment store(Comment comment);
    void delete(Comment comment);
}