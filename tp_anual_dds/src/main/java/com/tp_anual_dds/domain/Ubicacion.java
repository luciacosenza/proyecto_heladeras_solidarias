package com.tp_anual_dds.domain;

public class Ubicacion {
    Double longitud;
    Double latitud;
    String direccion;
    String ciudad;
    String pais;

    public Ubicacion(Double vLatitud, Double vLongitud, String vDireccion, String vCiudad, String vPais) {
        vLongitud = vLatitud;
        longitud = vLongitud;
        direccion = vDireccion;
        ciudad = vCiudad;
        pais = vPais;
    }
}
