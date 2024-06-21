package com.tp_anual_dds.domain;

import java.time.LocalDateTime;

public interface ContribucionCreator {
    public Contribucion crearContribucion(Colaborador colaborador, LocalDateTime fechaContribucion, Object... args);
}
