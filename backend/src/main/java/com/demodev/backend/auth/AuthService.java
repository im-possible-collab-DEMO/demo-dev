package com.demodev.backend.auth;

import com.demodev.backend.auth.dto.LoginRequest;
import com.demodev.backend.auth.dto.SignupRequest;
import com.demodev.backend.auth.dto.TokenResponse;
import com.demodev.backend.auth.dto.UserResponse;
import com.demodev.backend.common.exception.DuplicateEmailException;
import com.demodev.backend.common.exception.InvalidCredentialsException;
import com.demodev.backend.security.JwtTokenProvider;
import com.demodev.backend.user.User;
import com.demodev.backend.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public UserResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(request.email());
        }

        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.name()
        );
        User saved = userRepository.save(user);

        return new UserResponse(saved.getId(), saved.getEmail(), saved.getName());
    }

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());
        return new TokenResponse(token, user.getEmail(), user.getName());
    }
}
