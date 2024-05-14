package com.proyecto.gestion_nomina_ssn.service;

import com.proyecto.gestion_nomina_ssn.model.Nomina;

import java.util.List;

public interface INominaService {

    Nomina createNomina(Nomina nomina);

    Nomina updateNomina(Long id, Nomina nomina);

    Nomina getByIdNomina(Long id);

    List<Nomina> getAllNomina();

    void deleteNomina(Long id);

    void pagarNominasDiario();
}
