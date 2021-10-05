package com.epam.example.shoppinglist.data.repository;

import com.epam.example.shoppinglist.data.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to get {@link UserEntity} objects.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
