package com.proyecto.gestion_nomina_ssn.controller;

import com.proyecto.gestion_nomina_ssn.Auth.AuthResponse;
import com.proyecto.gestion_nomina_ssn.Auth.RegisterRequest;
import com.proyecto.gestion_nomina_ssn.dto.ApiResponse;
import com.proyecto.gestion_nomina_ssn.model.User;
import com.proyecto.gestion_nomina_ssn.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @PostMapping(value = "Employee/create")
    public ApiResponse createEmployee(@RequestBody User user) {
        User employee = this.employeeService.createEmployee(user);
        return new ApiResponse(
                HttpStatus.CREATED.value(),
                "EL usuario ha sido creado",
                employee);
    }

    @PutMapping("/{id}")
    public ApiResponse updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        employeeService.updateEmployee(id, user);
        return new ApiResponse(
                HttpStatus.OK.value(),
                "El usuario se ha actualizado correctamente",
                null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ApiResponse(
                HttpStatus.OK.value(),
                "El usuario se ha eliminado correctamente",
                null);
    }

    @GetMapping(value = "Employee/list")
    public List<User> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}
