package com.management.practicespringjpa.repository;

import com.management.practicespringjpa.domain.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer> {
}
