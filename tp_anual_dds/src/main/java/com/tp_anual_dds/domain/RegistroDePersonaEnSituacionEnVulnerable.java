package com.tp_anual_dds.domain;

import java.time.LocalDateTime;

public class RegistroDePersonaEnSituacionEnVulnerable extends Contribucion {
    private Tarjeta tarjetaAsignada;
    
    public RegistroDePersonaEnSituacionEnVulnerable(Colaborador vColaborador, LocalDateTime vFechaContribucion, Tarjeta vTarjetaAsignada) {
        colaborador = vColaborador;
        fechaContribucion = vFechaContribucion;
        tarjetaAsignada = vTarjetaAsignada;
    }

    public Tarjeta getTarjetaAsignada() {
        return tarjetaAsignada;
    }

    // obtenerDetalles()
    
    @Override
    public void validarIdentidad() {
        if(!esColaboradorHumano(colaborador)) {
            throw new IllegalArgumentException("El colaborador aspirante no es un Colaborador Humano");
        }
        
        if(colaborador.getDomicilio() == null) {
            throw new IllegalArgumentException("El colaborador aspirante no posee domicilio. Para Registrar Personas Vulnerables debe actualizar su información.");
        }
    }

    @Override
    public void accionar() {
        tarjetaAsignada.getTitular().setTarjeta(tarjetaAsignada);
        System.out.println(tarjetaAsignada);    // Esto es temporal, para que no tire errores. La idea es *agregar la tarjeta al sistema*
    }

    @Override
    public void calcularPuntos() {
        colaborador.sumarPuntos(2d);;
    }
}