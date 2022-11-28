package taskdb.taskdb.domain.like.questionLike.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;

public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Long> {
    boolean existsByQuestionAndUser(Question question, User user);
    void deleteByQuestionAndUser(Question question, User user);
    List<QuestionLike> findByQuestion(Question question);
}
