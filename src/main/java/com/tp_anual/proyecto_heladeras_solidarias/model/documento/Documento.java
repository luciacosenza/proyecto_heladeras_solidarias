package com.tp_anual.proyecto_heladeras_solidarias.model.documento;

import jakarta.persistence.*;
import lombok.Getter;
import jakarta.validation.constraints.Pattern;

@Entity
@Getter
public class Documento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipo; // final

    @Pattern(regexp = "^\\d+$")
    private String numero;  // final

    @Enumerated(EnumType.STRING)
    private Sexo sexo;  // final
    
    public enum TipoDocumento {
        DNI,
        PASAPORTE,
        LICENCIA_CONDUCIR,
        LIBRETA_CIVICA,
        LIBRETA_ENROLAMIENTO;
    }
    
    public enum Sexo {
        MASCULINO,
        FEMENINO,
        OTRO
    }

    public Documento() {}

    public Documento(TipoDocumento vTipo, String vNumero, Sexo vSexo) {
        tipo = vTipo;
        numero = vNumero;
        sexo = vSexo;
    }
}