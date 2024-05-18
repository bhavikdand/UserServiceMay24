package com.example.userservicemay24.repositories;

import com.example.userservicemay24.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer > {

    Optional<Token> findTokenByValue(String value);
}
