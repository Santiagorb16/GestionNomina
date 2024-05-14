package com.proyecto.gestion_nomina_ssn.service;

import com.proyecto.gestion_nomina_ssn.model.User;
import com.proyecto.gestion_nomina_ssn.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements IEmployeeService {

    private IEmployeeRepository employeeRepository;

    EmployeeServiceImplementation(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public User createEmployee(User user) {
        return this.employeeRepository.save(user);
    }

    @Override
    public User updateEmployee(Long id, User user) {
        if (this.employeeRepository.existsById(id)) {
            // user.setId(id);
            return this.employeeRepository.save(user);
        }
        return null;
    }

    @Override
    public User getById(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public void deleteEmployee(Long id) {
        this.employeeRepository.deleteById(id);
    }
}
