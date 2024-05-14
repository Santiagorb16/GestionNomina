package com.proyecto.gestion_nomina_ssn.service;

import com.proyecto.gestion_nomina_ssn.model.Nomina;
import com.proyecto.gestion_nomina_ssn.repository.INominaRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class NominaServiceImplementation implements INominaService {

    private INominaRepository nominaRepository;

    NominaServiceImplementation(INominaRepository nominaRepository) {
        this.nominaRepository = nominaRepository;
    }

    @Override
    public Nomina createNomina(Nomina nomina) {
        return this.nominaRepository.save(nomina);
    }

    @Override
    public Nomina updateNomina(Long id, Nomina nomina) {
        if (this.nominaRepository.existsById(id)) {
            nomina.setId(id);
            return this.nominaRepository.save(nomina);
        }
        return null;
    }

    @Override
    public Nomina getByIdNomina(Long id) {
        return this.nominaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Nomina> getAllNomina() {
        return this.nominaRepository.findAll();
    }

    @Override
    public void deleteNomina(Long id) {
        this.nominaRepository.deleteById(id);
    }

    // Método programado para ejecutarse todos los días a las 6 pm hora colombiana
    @Scheduled(cron = "0 53 13 * * ?", zone = "America/Bogota")
    public void pagarNominasDiario() {
        System.out.println("SE HA PAGADO");
    }

}
