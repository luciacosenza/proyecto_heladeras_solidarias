package com.tp_anual_dds.domain.heladera;

import java.time.LocalDateTime;

import com.tp_anual_dds.domain.colaborador.Colaborador;

public class Vianda {
    private String comida;
    private Heladera heladera;
    private Colaborador colaborador;
    private LocalDateTime fechaCaducidad;
    private LocalDateTime fechaDonacion;
    private Integer calorias;
    private Integer peso;
    private Boolean entregada;

    public Vianda(String vComida, Colaborador vColaborador, LocalDateTime vFechaCaducidad, LocalDateTime vFechaDonacion, Integer vCalorias, Integer vPeso, Boolean vEntregada) {
        comida = vComida;
        heladera = new HeladeraNula();
        colaborador = vColaborador;
        fechaCaducidad = vFechaCaducidad;
        fechaDonacion = vFechaDonacion;
        calorias = vCalorias;
        peso = vPeso;
        entregada = vEntregada;
    }

    public Heladera getHeladera() {
        return heladera;
    }

    public void setHeladera(Heladera vHeladera) {
        heladera = vHeladera;
    }
}
