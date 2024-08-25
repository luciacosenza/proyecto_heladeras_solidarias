package com.tp_anual.proyecto_heladeras_solidarias.domain.contacto;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.tp_anual.proyecto_heladeras_solidarias.domain.colaborador.Colaborador;
import com.tp_anual.proyecto_heladeras_solidarias.message_loader.I18n;

public class EMail extends MedioDeContacto {
    private static final Logger logger = Logger.getLogger(Colaborador.class.getName());
    private final String direccionCorreo;

    public EMail(String vDireccionCorreo) {
        direccionCorreo = vDireccionCorreo;
    }

    @Override
    public void contactar(String asunto, String cuerpo) {
        EMailSenderService.enviarEMail(direccionCorreo, asunto, cuerpo);
        logger.log(Level.INFO, I18n.getMessage("contacto.EMail.contactar_info", direccionCorreo));
    }
}
