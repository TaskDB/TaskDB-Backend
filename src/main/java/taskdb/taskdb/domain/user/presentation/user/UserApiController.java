package taskdb.taskdb.domain.user.presentation.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionsResponseDto;
import taskdb.taskdb.domain.store.presentation.dto.response.StoreQuestionsResponseDto;
import taskdb.taskdb.domain.user.presentation.dto.user.request.UserJoinRequestDto;
import taskdb.taskdb.domain.user.presentation.dto.user.response.UserResponseDto;
import taskdb.taskdb.domain.user.presentation.dto.user.response.UsersRankResponseDto;
import taskdb.taskdb.domain.user.service.user.UserService;
import taskdb.taskdb.global.cover.Result;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    @PostMapping("/join")
    public boolean join(@RequestBody @Valid UserJoinRequestDto requestDto) {
        return userService.join(requestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/rank")
    public Result rank() {
        List<UsersRankResponseDto> usersRank = userService.rank();
        return Result.builder()
                .data(usersRank)
                .build();
    }
}
