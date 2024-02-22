package ru.melnikov.springBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.melnikov.springBoot.services.PersonDetailsService;

import java.util.Collections;
//В соновном AuthenticationProvider нужен для настройки какой-то кастомной аутентификации
//если мы не добавляем ничего "особенного" в процесс аутентификации, его можно не использовать
@Component
public class AuthProvider implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;
    @Autowired
    public AuthProvider(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String name = authentication.getName();
       String password = authentication.getCredentials().toString();
       UserDetails userDetails = personDetailsService.loadUserByUsername(name);

       if(!password.equals(userDetails.getPassword()))
           throw new BadCredentialsException("Incorrect password!");


       return new UsernamePasswordAuthenticationToken(userDetails,password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
