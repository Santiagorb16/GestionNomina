package com.proyecto.gestion_nomina_ssn.repository;

import com.proyecto.gestion_nomina_ssn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface IEmployeeRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
