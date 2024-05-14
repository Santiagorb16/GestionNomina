package com.proyecto.gestion_nomina_ssn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Nomina")
public class Nomina {
    @Id
    @GeneratedValue
    private Long id;
    private Double salary;
    private LocalDate paymentDate;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User id_employee;
}
