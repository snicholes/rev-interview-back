package dev.nicholes.revinterview.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.nicholes.revinterview.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
