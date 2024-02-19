package ru.melnikov.springBoot.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.melnikov.springBoot.models.PersonUser;
import ru.melnikov.springBoot.repositories.PersonRepository;
import ru.melnikov.springBoot.security.PersonUserDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PersonUser> userListName = personRepository.findByUserName(username);
        if(userListName.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new PersonUserDetails(userListName.get());
    }
}
