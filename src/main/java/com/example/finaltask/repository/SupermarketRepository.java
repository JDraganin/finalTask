package com.example.finaltask.repository;

import com.example.finaltask.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket, UUID> {
    Optional<Supermarket> findSupermarketByName(String name);
}