package com.proyecto.gestion_nomina_ssn.repository;

import com.proyecto.gestion_nomina_ssn.model.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INominaRepository extends JpaRepository<Nomina, Long> {
}
