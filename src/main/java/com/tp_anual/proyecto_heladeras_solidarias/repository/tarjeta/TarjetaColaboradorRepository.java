package com.tp_anual.proyecto_heladeras_solidarias.repository.tarjeta;

import com.tp_anual.proyecto_heladeras_solidarias.model.tarjeta.TarjetaColaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaColaboradorRepository extends JpaRepository<TarjetaColaborador, String> {
    TarjetaColaborador findByPermisosId(Long permisoId);
}