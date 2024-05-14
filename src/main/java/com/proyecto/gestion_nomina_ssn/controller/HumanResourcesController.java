package com.proyecto.gestion_nomina_ssn.controller;

import com.proyecto.gestion_nomina_ssn.dto.ApiResponse;
import com.proyecto.gestion_nomina_ssn.model.Nomina;
import com.proyecto.gestion_nomina_ssn.model.User;
import com.proyecto.gestion_nomina_ssn.repository.INominaRepository;
import com.proyecto.gestion_nomina_ssn.service.INominaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HumanResources/v1")
@RequiredArgsConstructor
public class HumanResourcesController {
    @Autowired
    private INominaService NominaService;

    @PostMapping(value = "Nomina/create")
    public ApiResponse createEmployee(@RequestBody Nomina nomina) {
        Nomina nomina2 = this.NominaService.createNomina(nomina);
        return new ApiResponse(
                HttpStatus.CREATED.value(),
                "La nomina ha sido creada",
                nomina2);
    }

    @PutMapping("/{id}")
    public ApiResponse updateNomina(@PathVariable("id") Long id, @RequestBody Nomina nomina) {
        NominaService.updateNomina(id, nomina);
        return new ApiResponse(
                HttpStatus.OK.value(),
                "La nomina se ha actualizado correctamente",
                null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteNomina(@PathVariable("id") Long id) {
        NominaService.deleteNomina(id);
        return new ApiResponse(
                HttpStatus.OK.value(),
                "La nomina se ha eliminado correctamente",
                null);
    }

    @GetMapping(value = "Nomina/list")
    public List<Nomina> getAllNomina() {
        return NominaService.getAllNomina();
    }

}