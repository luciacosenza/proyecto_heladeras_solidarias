package com.tp_anual.proyecto_heladeras_solidarias.repository.persona;

import com.tp_anual.proyecto_heladeras_solidarias.model.persona.PersonaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaFisicaRepository extends JpaRepository<PersonaFisica, Long> {
    
}