package com.tp_anual_dds.domain.persona_en_situacion_vulnerable;

import java.time.LocalDateTime;

import com.tp_anual_dds.domain.documento.Documento;
import com.tp_anual_dds.domain.persona.PersonaFisica;
import com.tp_anual_dds.domain.tarjeta.TarjetaPersonaEnSituacionVulnerable;
import com.tp_anual_dds.domain.ubicacion.Ubicacion;
import com.tp_anual_dds.sistema.Sistema;

public class PersonaEnSituacionVulnerable {
    private PersonaFisica persona;
    private Ubicacion domicilio;
    private LocalDateTime fechaRegistro;
    private Integer menoresACargo;
    private TarjetaPersonaEnSituacionVulnerable tarjeta;    // tarjetaNula (ningun metodo hace algo pero los conoce, para tener polimorfismo)

    public PersonaEnSituacionVulnerable(String vNombre, String vApellido, Documento vDocumento, LocalDateTime vFechaNacimiento, Ubicacion vDomicilio, LocalDateTime vFechaRegistro, Integer vMenoresACargo) {
        persona = new PersonaFisica(vNombre, vApellido, vDocumento, vFechaNacimiento);
        domicilio = vDomicilio;
        fechaRegistro = vFechaRegistro;
        menoresACargo = vMenoresACargo;
    }

    public Integer getMenoresACargo() {
        return menoresACargo;
    }

    public TarjetaPersonaEnSituacionVulnerable getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaPersonaEnSituacionVulnerable tarjetaAsignada) {
        tarjeta = tarjetaAsignada;
    }

    public Boolean poseeMenoresACargo() {
        return menoresACargo > 0;
    }

    public void darDeAlta() {
        Sistema.agregarPersonaEnSituacionVulnerable(this);
    }

    public void darDeBaja() {
        Sistema.eliminarPersonaEnSituacionVulnerable(this);
    }
}
