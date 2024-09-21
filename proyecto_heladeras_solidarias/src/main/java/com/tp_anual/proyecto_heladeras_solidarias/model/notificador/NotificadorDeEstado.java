package com.tp_anual.proyecto_heladeras_solidarias.model.notificador;

import java.util.ArrayList;

import com.tp_anual.proyecto_heladeras_solidarias.model.contacto.MedioDeContacto;
import com.tp_anual.proyecto_heladeras_solidarias.model.heladera.HeladeraActiva;
import com.tp_anual.proyecto_heladeras_solidarias.model.suscripcion.Suscripcion.CondicionSuscripcion;
import com.tp_anual.proyecto_heladeras_solidarias.model.ubicador.UbicadorHeladera;
import com.tp_anual.proyecto_heladeras_solidarias.i18n.I18n;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificadorDeEstado extends Notificador {
    private final UbicadorHeladera ubicador;
    private Integer cantidadHeladeras;
    
    public NotificadorDeEstado() {
        ubicador = new UbicadorHeladera();
        cantidadHeladeras = 10; // Esta cantidad es arbitraria, pero puede ser modificada
    }

    public void notificarEstado(HeladeraActiva heladera, CondicionSuscripcion condicion, MedioDeContacto contacto) {    // Usa el Medio de Contacto previamente elegido por el colaborador
        ArrayList<HeladeraActiva> heladerasCercanas = ubicador.obtenerHeladerasCercanasA(heladera, cantidadHeladeras);
        
        switch(condicion) {
        
            case VIANDAS_MIN -> {
                // Obtengo la Heladera más llena
                HeladeraActiva heladeraMasLlena = ubicador.obtenerHeladeraMasLlena(heladerasCercanas);

                enviarNotificacion(
                contacto,
                I18n.getMessage("notificador.NotificadorDeEstado.notificarEstado_outer_message_vmn_title",
                heladera.getNombre()),
                I18n.getMessage("notificador.NotificadorDeEstado.notificarEstado_outer_message_vmn_body",
                heladera.getNombre(), heladera.viandasActuales(), heladeraMasLlena.getNombre(), heladeraMasLlena.getUbicacion().getDireccion()));
            }

            case VIANDAS_MAX -> {
                // Obtengo la Heladera menos llena
                HeladeraActiva heladeraMenosLlena = ubicador.obtenerHeladeraMenosLlena(heladerasCercanas);

                enviarNotificacion(
                contacto,
                I18n.getMessage("notificador.NotificadorDeEstado.notificarEstado_outer_message_vmx_title",
                heladera.getNombre()),
                I18n.getMessage("notificador.NotificadorDeEstado.notificarEstado_outer_message_vmn_body",
                (heladera.getCapacidad() - heladera.viandasActuales()), heladera.getNombre(), heladeraMenosLlena.getNombre(), heladeraMenosLlena.getUbicacion().getDireccion()));
            }

            case DESPERFECTO -> {
                enviarNotificacion(
                contacto,
                I18n.getMessage("notificador.NotificadorDeEstado.notificarEstado_outer_message_d_title",
                heladera.getNombre()),
                I18n.getMessage("notificador.NotificadorDeEstado.notificarEstado_outer_message_d_body",
                obtenerNombresYDireccionesDe(heladerasCercanas)));
            }

            default -> {}
        }
    }

    public String obtenerNombresYDireccionesDe(ArrayList<HeladeraActiva> heladeras) {
        HeladeraActiva heladera1 = heladeras.removeFirst();
        String nombresYDirecciones = I18n.getMessage("notificador.NotificadorDeEstado.notificarEstado_outer_message_d_body", heladera1.getNombre(), heladera1.getUbicacion().getDireccion());
        
        for (HeladeraActiva heladera : heladeras) {
            nombresYDirecciones += I18n.getMessage("notificador.NotificadorDeEstado.notificarEstado_outer_message_d_body", heladera.getNombre(), heladera.getUbicacion().getDireccion());
        }

        return nombresYDirecciones;
    }
}