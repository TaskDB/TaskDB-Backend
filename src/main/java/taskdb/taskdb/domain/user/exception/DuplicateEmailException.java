package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class DuplicateEmailException extends BadRequestException {
    private static final String MESSAGE = "이미 존재하는 이메일입니다.";

    public DuplicateEmailException() {
        super(MESSAGE);
    }
}