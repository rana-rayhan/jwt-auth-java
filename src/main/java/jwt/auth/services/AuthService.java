package jwt.auth.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jwt.auth.config.JwtService;
import jwt.auth.models.AuthResponse;
import jwt.auth.models.RegisterRequest;
import jwt.auth.models.Role;
import jwt.auth.models.User;
import jwt.auth.repo.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    // dependency injection
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    // register user into databse
    public AuthResponse registerUser(RegisterRequest data) {
        var user = User
                .builder()
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .email(data.getEmail())
                .password(passwordEncoder.encode(data.getPassword()))
                .role(Role.USER)
                .build();

        // save user into database
        userRepo.save(user);
        // generate token
        var userToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(userToken).build();
    }

    // login user
    public AuthResponse loginUser(RegisterRequest data) {

        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                data.getEmail(),
                data.getPassword()));

        // find user by email from database
        var user = userRepo.findByEmail(data.getEmail()).orElseThrow();
        // create login token
        var userToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(userToken).build();
    }

}
