package fr.anywr.school.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthDto {

    private Long id;
    @NotNull
    @Email
    @Length(min = 5, max = 50)
    private String email;

    @Length(min = 5, max = 50)
    private String name;

    @NotNull
    @Length(min = 5, max = 10)
    private String password;

}