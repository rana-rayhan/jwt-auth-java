package jwt.auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jwt.auth.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    // custom method to find user by email
    Optional<User> findByEmail(String email);
}
