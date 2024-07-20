package com.tp_anual_dds.domain.heladera.acciones_en_heladera;

import java.time.LocalDateTime;

import com.tp_anual_dds.domain.colaborador.ColaboradorHumano;
import com.tp_anual_dds.domain.heladera.HeladeraActiva;

public class SolicitudAperturaColaborador extends AccionHeladera {
    private MotivoSolicitud motivo;

    public enum MotivoSolicitud {
        INGRESAR_DONACION,
        INGRESAR_LOTE_DE_DISTRIBUCION,
        RETIRAR_LOTE_DE_DISTRIBUCION
    }

    public SolicitudAperturaColaborador(LocalDateTime vFecha, HeladeraActiva vHeladera, ColaboradorHumano vResponsable, MotivoSolicitud vMotivo) {
        fecha = vFecha;
        heladera = vHeladera;
        responsable = vResponsable;
        motivo = vMotivo;
    }
}
