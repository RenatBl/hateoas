package ru.itis.hateoas.services.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.dto.UserDto;
import ru.itis.hateoas.forms.UserForm;
import ru.itis.hateoas.mappers.UsersMapper;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.repositories.UsersRepository;
import ru.itis.hateoas.security.authentication.JwtAuthentication;
import ru.itis.hateoas.services.UsersService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder encoder;
    private final UsersMapper usersMapper;

    @Override
    public UserDto getUserById(ObjectId id) {
        return usersMapper.entityToDto(usersRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                ));
    }

    @Override
    public User save(User user) {
        return usersRepository.save(user);
    }

    @Override
    public User updateUserAccount(User user, UserForm form) {
        Optional.ofNullable(form.getUsername())
                .ifPresent(user::setUsername);
        Optional.ofNullable(encoder.encode(form.getPassword()))
                .ifPresent(user::setPassword);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ((JwtAuthentication) authentication).setUserDetails(user);

        return save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return usersRepository.findByUsername(s)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username " + s + " not found")
                );
    }
}