package com.proyecto.gestion_nomina_ssn.service;

import com.proyecto.gestion_nomina_ssn.model.User;

import java.util.List;

public interface IEmployeeService {

    User createEmployee(User user);

    User updateEmployee(Long id, User user);

    User getById(Long id);

    List<User> getAllEmployees();

    void deleteEmployee(Long id);
}
