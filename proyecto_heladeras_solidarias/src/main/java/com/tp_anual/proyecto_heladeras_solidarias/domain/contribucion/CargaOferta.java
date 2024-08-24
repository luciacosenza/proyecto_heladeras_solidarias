package com.tp_anual.proyecto_heladeras_solidarias.domain.contribucion;

import java.time.LocalDateTime;

import com.tp_anual.proyecto_heladeras_solidarias.domain.colaborador.Colaborador;
import com.tp_anual.proyecto_heladeras_solidarias.domain.oferta.Oferta;

public class CargaOferta extends Contribucion {
    private final Oferta oferta;

    public CargaOferta(Colaborador vColaborador, LocalDateTime vFechaContribucion, Oferta vOferta) {
        colaborador = vColaborador;
        fechaContribucion = vFechaContribucion;
        oferta = vOferta;
        completada = false;
    }

    @Override
    public void obtenerDetalles() {
        super.obtenerDetalles();
        System.out.println("Oferta: " + oferta.getNombre());
    }
    
    public Oferta getOferta() {
        return oferta;
    }

    @Override
    public void validarIdentidad() {}   // No tiene ningún requisito en cuanto a los datos o identidad del colaborador

    @Override
    protected void calcularPuntos() {}  // Esta Contribución no entra entre las que suman puntos

}