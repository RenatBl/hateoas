package ru.itis.hateoas.services.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.dto.TokenDto;
import ru.itis.hateoas.forms.SignInForm;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.repositories.UsersRepository;
import ru.itis.hateoas.services.SignInService;
import ru.itis.hateoas.services.UsersService;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public TokenDto signIn(SignInForm form) {
        User user = (User) usersService.loadUserByUsername(form.getUsername());
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }
        return new TokenDto(Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("name", user.getUsername())
                .claim("role", user.getRole().name())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact());
    }
}
