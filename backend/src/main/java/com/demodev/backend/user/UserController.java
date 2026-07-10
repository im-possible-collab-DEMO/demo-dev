package com.demodev.backend.user;

import com.demodev.backend.auth.dto.UserResponse;
import com.demodev.backend.common.exception.InvalidCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public UserResponse me(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(InvalidCredentialsException::new);

        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }
}
