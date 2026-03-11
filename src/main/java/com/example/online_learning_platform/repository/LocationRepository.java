package com.example.online_learning_platform.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.online_learning_platform.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    Boolean existsByCode(String code);

    List<Location> findByName(String name);

}
