package taskdb.taskdb.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.domain.auth.presentation.dto.request.UserLoginRequestDto;
import taskdb.taskdb.domain.auth.presentation.dto.response.TokenResponseDto;
import taskdb.taskdb.domain.auth.service.AuthService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authService.login(requestDto);
    }
}