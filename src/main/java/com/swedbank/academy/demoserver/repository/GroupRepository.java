package com.swedbank.academy.demoserver.repository;

import com.swedbank.academy.demoserver.person.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
