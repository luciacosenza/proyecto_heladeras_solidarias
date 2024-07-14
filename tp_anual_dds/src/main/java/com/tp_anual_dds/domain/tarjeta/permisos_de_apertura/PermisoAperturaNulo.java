package com.tp_anual_dds.domain.tarjeta.permisos_de_apertura;

import java.time.LocalDateTime;

import com.tp_anual_dds.domain.heladera.Heladera;

public class PermisoAperturaNulo extends PermisoApertura {
    public PermisoAperturaNulo() {
        heladeraPermitida = null;   // No usamos Null Object porque creimos que no lo ameritaba
        fechaOtorgamiento = null;
    }

    @Override
    public Heladera getHeladeraPermitida() {
        throw new UnsupportedOperationException("Permiso Nulo no tiene una heladera permitida.");
    }

    @Override
    public LocalDateTime getFechaOtorgamiento() {
        throw new UnsupportedOperationException("Permiso Nulo no tiene una fecha de otorgamiento.");
    }

    @Override
    public void actualizarFechaOtorgamiento() {}

    @Override
    public Boolean esHeladeraPermitida(Heladera heladera) {
        return false;
    }

    @Override
    public void setHeladeraPermitida(Heladera heladera) {}

    @Override
    public void resetHeladeraPermitida() {}
}