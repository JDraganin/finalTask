package com.example.finaltask.repository;

import com.example.finaltask.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ItemsRepository extends JpaRepository<Item, UUID> {
}