package com.tp_anual_dds.domain.contribuciones;

import java.time.LocalDateTime;

import com.tp_anual_dds.domain.colaborador.Colaborador;
import com.tp_anual_dds.domain.colaborador.ColaboradorHumano;
import com.tp_anual_dds.domain.colaborador.ColaboradorJuridico;

public abstract class Contribucion {
    protected Colaborador colaborador;
    protected LocalDateTime fechaContribucion;
    protected Boolean completada;

    public Colaborador getColaborador() {
        return colaborador;
    }

    public LocalDateTime getFechaContribucion() {
        return fechaContribucion;
    }

    public Boolean estaCompletada() {
        return completada;
    }

    // obtenerDetalles()

    protected Boolean esColaboradorHumano(Colaborador colaboradorAspirante) {
        return colaboradorAspirante.getClass() == ColaboradorHumano.class;
    }

    protected Boolean esColaboradorJuridico(Colaborador colaboradorAspirante) {
        return colaboradorAspirante.getClass() == ColaboradorJuridico.class;
    }

    protected abstract void validarIdentidad();

    protected abstract void accionar();

    protected abstract void calcularPuntos();
    
    public void contribuir() {
        validarIdentidad();
        accionar();
    }

    public void confirmar() {
        // Podemos agregar logica para confirmar que la Contribucion fue efectivamente realizada
        completada = true;
        calcularPuntos();
    }
}
