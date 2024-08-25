package com.tp_anual.proyecto_heladeras_solidarias.domain.contribucion;

import java.time.LocalDateTime;

import com.tp_anual.proyecto_heladeras_solidarias.domain.colaborador.Colaborador;
import com.tp_anual.proyecto_heladeras_solidarias.domain.heladera.HeladeraActiva;

public class DistribucionViandas extends Contribucion {
    private final HeladeraActiva origen;
    private final HeladeraActiva destino;
    private final Integer cantidadViandasAMover;
    private final MotivoDistribucion motivo;

    public enum MotivoDistribucion {
        DESPERFECTO_EN_LA_HELADERA,
        FALTA_DE_VIANDAS_EN_DESTINO
    }
    
    public DistribucionViandas(Colaborador vColaborador, LocalDateTime vFechaContribucion, HeladeraActiva vOrigen, HeladeraActiva vDestino, Integer vCantidadViandasAMover, MotivoDistribucion vMotivo) {
        colaborador = vColaborador;
        fechaContribucion = vFechaContribucion;
        origen = vOrigen;
        destino = vDestino;
        cantidadViandasAMover = vCantidadViandasAMover;
        motivo = vMotivo;
        completada = false;
    }

    public HeladeraActiva getOrigen() {
        return origen;
    }

    public HeladeraActiva getDestino() {
        return destino;
    }

    public Integer getCantidadViandasAMover() {
        return cantidadViandasAMover;
    }

    public MotivoDistribucion getMotivo() {
        return motivo;
    }

    @Override
    public void obtenerDetalles() {
        super.obtenerDetalles();
        System.out.println("Heladera Origen: " + origen.getNombre());
        System.out.println("Heladera Destino: " + destino.getNombre());
        System.out.println("Cantidad: " + cantidadViandasAMover);
        System.out.println("Motivo: " + motivo + "\n");
    }
    
    @Override
    public void validarIdentidad() {
        if(colaborador.getDomicilio() == null)
            throw new IllegalArgumentException("El colaborador aspirante no posee domicilio. Para recibir la tarjeta solidaria debe actualizar su información");
    }

    @Override
    protected void calcularPuntos() {
        colaborador.sumarPuntos(Double.valueOf(cantidadViandasAMover));
    }
}
