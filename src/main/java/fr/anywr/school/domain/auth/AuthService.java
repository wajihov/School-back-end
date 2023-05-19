package fr.anywr.school.domain.auth;

import fr.anywr.school.core.config.JwtTokenUtil;
import fr.anywr.school.core.exception.SchoolException;
import fr.anywr.school.core.rest.Codes;
import fr.anywr.school.domain.auth.api.AuthenticateRequest;
import fr.anywr.school.domain.auth.api.AuthenticateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtUtil;
    private final AuthRepository authRepository;
    private final AuthMapper authMapper;

    public AuthService(AuthenticationManager authManager, JwtTokenUtil jwtUtil, AuthRepository authRepository, AuthMapper authMapper) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.authRepository = authRepository;
        this.authMapper = authMapper;
    }

    public AuthenticateResponse singIn(AuthenticateRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));
        Auth auth = (Auth) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessTokenAuth(auth);
        return new AuthenticateResponse(auth.getEmail(), accessToken);
    }

    private String passwordEncoder(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public AuthDto register(AuthDto authDto) {
        if (authRepository.findByEmail(authDto.getEmail()).isPresent()) {
            throw new SchoolException(Codes.ERR_AUTHENTICATE_EMAIL_EXIST);
        } else {
            Auth auth = new Auth(authDto.getEmail(), passwordEncoder(authDto.getPassword()), authDto.getName());
            auth = authRepository.save(auth);
            return authMapper.toDto(auth);
        }
    }

}
