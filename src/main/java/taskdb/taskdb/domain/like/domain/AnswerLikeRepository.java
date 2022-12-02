package taskdb.taskdb.domain.like.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;

public interface AnswerLikeRepository extends JpaRepository<AnswerLike, Long> {
    boolean existsByAnswerAndUser(Answer answer, User user);
    void deleteByAnswerAndUser(Answer answer, User user);
    List<AnswerLike> findByAnswer(Answer answer);

}