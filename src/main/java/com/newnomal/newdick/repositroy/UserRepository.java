package com.newnomal.newdick.repositroy;

import com.newnomal.newdick.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
    public Optional<User> findByEmailAndPassword(String email, String password);

    public Optional<User> getUserById(Long id);

}
