package com.secure.jwtimplementation.repository;

import com.secure.jwtimplementation.entity.ConsumeMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<ConsumeMessage, Long> {
}