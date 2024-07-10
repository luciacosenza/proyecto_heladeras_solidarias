package com.tp_anual_dds.migrador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import com.tp_anual_dds.conversores.ConversorFormaContribucion;
import com.tp_anual_dds.conversores.ConversorTipoDocumento;
import com.tp_anual_dds.domain.colaborador.ColaboradorHumano;
import com.tp_anual_dds.domain.contacto.EMail;
import com.tp_anual_dds.domain.contacto.MedioDeContacto;
import com.tp_anual_dds.domain.contribuciones.Contribucion;
import com.tp_anual_dds.domain.contribuciones.ContribucionCreator;
import com.tp_anual_dds.domain.documento.Documento;

public class TransformacionDeDatos {
    private String quitarEspacios(String string) {
        string = string.replaceAll("\\s+", "");
        return string;
    }

    private String quitarNumericosYEspeciales(String string) {
        string = string.replaceAll("[^a-zA-Z]", "");
        return string;
    }

    private Contribucion registrarContribucion(String formaContribucionStr, ColaboradorHumano colaborador, LocalDateTime fechaContribucion) {
        ContribucionCreator creator = ConversorFormaContribucion.convertirStrAContribucionCreator(formaContribucionStr);
        return creator.crearContribucion(colaborador, fechaContribucion); // Posible error al querer crear una contribucion a traves de un Creator sin pasarle el resto de argumentos necesarios
    }
    
    // private static ArrayList<Colaborador> sincronizarContribuciones() no hara falta, dado que cuando tengamos una database, esta hara que cada Colaborador sea unico

    private ColaboradorHumano procesarColaborador(String[] data) {
        String tipoDocStr = data[0];
        String numDoc = data[1];
        String nombre = data[2];
        String apellido = data[3];
        String direcMail = data[4];
        String fechaContribucionStr = data[5];
        String formaContribucionStr = data[6];
        Integer cantColabs = Integer.valueOf(data[7]);

        // Transforma a documento
        String tipoDocStrCleaned = quitarEspacios(quitarNumericosYEspeciales(tipoDocStr));
        Documento.TipoDocumento tipoDoc = ConversorTipoDocumento.convertirStrATipoDocumento(tipoDocStrCleaned);
        Documento documento = new Documento(tipoDoc, numDoc, null);
        
        // Transforma a eMail
        EMail mail = new EMail(direcMail);
        ArrayList<MedioDeContacto> contactos = new ArrayList<>();
        contactos.add(mail);

        // Transforma a fechaContribucion
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime fechaContribucion;
        
        try {
            fechaContribucion = LocalDateTime.parse(fechaContribucionStr, dateFormat);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }

        // Transforma a colaborador
        ColaboradorHumano colaborador = null;   // Deberia ir: "obtenerColaborador(documento, nombre, apellido);" pero no tenemos forma de implementarlo, dado que todavia no tenemos una database
        
        if (colaborador == null) {
            colaborador = new ColaboradorHumano(null, contactos, null, null, nombre, apellido, documento, null);
        }
        
        // Agrega contribuciones a colaborador
        for(Integer i = 0; i < cantColabs; i++){
            Contribucion contribucion = registrarContribucion(formaContribucionStr, colaborador, fechaContribucion);
            colaborador.agregarContribucion(contribucion);
        }

        return colaborador;
    }    
    
    public ArrayList<ColaboradorHumano> transform(ArrayList<String[]> data) {
        ArrayList<ColaboradorHumano> colaboradoresProcesados = new ArrayList<>();

        for(String[] dataColaborador : data) {
            ColaboradorHumano colaborador = procesarColaborador(dataColaborador);

            if(colaborador == null) {
                continue;
            }
            
            colaboradoresProcesados.add(colaborador);
        }

        return colaboradoresProcesados;
    }
}