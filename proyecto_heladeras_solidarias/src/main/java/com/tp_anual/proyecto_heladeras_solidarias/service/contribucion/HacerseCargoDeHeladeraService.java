package com.tp_anual.proyecto_heladeras_solidarias.service.contribucion;

import com.tp_anual.proyecto_heladeras_solidarias.i18n.I18n;
import com.tp_anual.proyecto_heladeras_solidarias.model.colaborador.Colaborador;
import com.tp_anual.proyecto_heladeras_solidarias.model.colaborador.ColaboradorJuridico;
import com.tp_anual.proyecto_heladeras_solidarias.model.contribucion.DonacionVianda;
import com.tp_anual.proyecto_heladeras_solidarias.model.contribucion.HacerseCargoDeHeladera;
import com.tp_anual.proyecto_heladeras_solidarias.repository.contribucion.ContribucionRepository;
import com.tp_anual.proyecto_heladeras_solidarias.repository.contribucion.HacerseCargoDeHeladeraRepository;
import com.tp_anual.proyecto_heladeras_solidarias.service.colaborador.ColaboradorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;

@Service
@Log
public class HacerseCargoDeHeladeraService extends ContribucionService {

    private final HacerseCargoDeHeladeraRepository hacerseCargoDeHeladeraRepository;
    private final Double multiplicadorPuntos = 5d;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // TODO: Probablemente no vaya acá el scheduler, porque los Services son Singletons

    public HacerseCargoDeHeladeraService(ContribucionRepository vContribucionRepository, ColaboradorService vColaboradorService, HacerseCargoDeHeladeraRepository vHacerseCargoDeHeladeraRepository) {
        super(vContribucionRepository, vColaboradorService);
        hacerseCargoDeHeladeraRepository = vHacerseCargoDeHeladeraRepository;
    }

    public HacerseCargoDeHeladera obtenerHacerseCargoDeHeladera(Long hacerseCargoId) {
        return hacerseCargoDeHeladeraRepository.findById(hacerseCargoId).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada"));
    }

    public ArrayList<HacerseCargoDeHeladera> obtenerHacerseCargoDeHeladerasQueSumanPuntos() {
        return new ArrayList<>(hacerseCargoDeHeladeraRepository.findHacerseCargoDeHeladeras());
    }

    public HacerseCargoDeHeladera guardarHacerseCargoDeHeladera(HacerseCargoDeHeladera hacerseCargoDeHeladera) {
        return hacerseCargoDeHeladeraRepository.save(hacerseCargoDeHeladera);
    }

    @Override
    public void validarIdentidad(Long contribucionId, Long colaboradorId) {}   // No tiene ningún requisito en cuanto a los datos o identidad del colaborador

    @Override
    protected void confirmarSumaPuntos(Long contribucionId, Long colaboradorId, Double puntosSumados) {
        ColaboradorJuridico colaborador = colaboradorService.obtenerColaboradorJuridico(colaboradorId);
        log.log(Level.INFO, I18n.getMessage("contribucion.HacerseCargoDeHeladera.confirmarSumaPuntos_info", puntosSumados, colaborador.getPersona().getNombre(2)), getClass().getSimpleName());
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    protected void calcularPuntos() {
        ArrayList<HacerseCargoDeHeladera> hacerseCargoDeLaHeladeras = obtenerHacerseCargoDeHeladerasQueSumanPuntos();

        for(HacerseCargoDeHeladera hacerseCargoDeHeladera : hacerseCargoDeLaHeladeras ) {
            ColaboradorJuridico colaborador = (ColaboradorJuridico) hacerseCargoDeHeladera.getColaborador();

            colaborador.sumarPuntos(multiplicadorPuntos);
            colaboradorService.guardarColaborador(colaborador);

            hacerseCargoDeHeladera.setYaSumoPuntos(true);
            guardarHacerseCargoDeHeladera(hacerseCargoDeHeladera);
            confirmarSumaPuntos(hacerseCargoDeHeladera.getId(), colaborador.getId(), multiplicadorPuntos);
        }
    }


}