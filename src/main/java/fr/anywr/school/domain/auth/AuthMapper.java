package fr.anywr.school.domain.auth;

import fr.anywr.school.core.exception.SchoolException;
import fr.anywr.school.core.rest.Codes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthMapper {

    public Auth toEntity(AuthDto authDto) {
        if (authDto == null) {
            throw new SchoolException(Codes.ERR_AUTHENTICATE_NOT_FOUND);
        }
        Auth auth = new Auth();
        auth.setId(authDto.getId());
        auth.setName(auth.getName());
        auth.setEmail(authDto.getEmail());
        auth.setPassword(authDto.getPassword());
        auth.setRole(authDto.getRole());
        return auth;
    }

    public AuthDto toDto(Auth auth) {
        if (auth == null) {
            throw new SchoolException(Codes.ERR_AUTHENTICATE_NOT_FOUND);
        }
        return AuthDto.builder()
                .id(auth.getId())
                .name(auth.getName())
                .email(auth.getEmail())
                .password(auth.getPassword())
                .role(auth.getRole())
                .build();
    }

    public List<AuthDto> authDtoList(List<Auth> auths) {
        if (auths == null) {
            throw new SchoolException(Codes.ERR_AUTHENTICATES_NOT_FOUND);
        }
        return auths.stream().map(this::toDto).collect(Collectors.toList());
    }
}
