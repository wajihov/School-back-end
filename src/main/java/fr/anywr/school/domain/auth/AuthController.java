package fr.anywr.school.domain.auth;

import fr.anywr.school.domain.auth.api.AuthenticateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticateRequest request) {
        try {
            return ResponseEntity.ok().body(authService.singIn(request));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthDto> register(@RequestBody @Valid AuthDto authDto) {
        AuthDto authDtoCreated = authService.register(authDto);
        return new ResponseEntity<>(authDtoCreated, HttpStatus.CREATED);
    }

}
