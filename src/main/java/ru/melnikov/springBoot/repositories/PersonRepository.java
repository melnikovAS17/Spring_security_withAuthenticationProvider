package ru.melnikov.springBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.melnikov.springBoot.models.PersonUser;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonUser,Integer> {
    Optional<PersonUser> findByUserName(String username);
}
