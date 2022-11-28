package taskdb.taskdb.domain.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import taskdb.taskdb.global.exception.application.BaseExceptionType;

@Getter
@AllArgsConstructor
public enum UserExceptionType implements BaseExceptionType {

    ALREADY_EXIST_EMAIL(600, HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    NOT_SIGNUP_EMAIL(601, HttpStatus.BAD_REQUEST, "가입되지 않은 이메일입니다."),
    WRONG_PASSWORD(602, HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    WRONG_EMAIL_CHECK_CODE(603, HttpStatus.BAD_REQUEST, "이메일 인증 코드가 일치하지 않습니다."),
    REQUIRED_DO_LOGIN(604, HttpStatus.BAD_REQUEST, "로그인이 필요합니다."),
    FORBIDDEN(605, HttpStatus.BAD_REQUEST, "권한이 부족합니다."),
    NOT_FOUND_USER(606, HttpStatus.BAD_REQUEST, "회원이 존재하지 않습니다."),
    DIFFERENT_USER(607, HttpStatus.BAD_REQUEST, "다른 유저의 질문글입니다."),
    INVALID_EMAIL(608, HttpStatus.BAD_REQUEST, "잘못된 양식의 이메일입니다.");

    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
