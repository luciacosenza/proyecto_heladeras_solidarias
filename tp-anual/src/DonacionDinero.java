package src;

import java.time.LocalDate;
import java.time.Period;

public class DonacionDinero extends Contribucion {
    private Double monto;
    private FrecuenciaDePago frecuencia;
    
    enum FrecuenciaDePago {
        SEMANAL,
        MENSUAL,
        SEMESTRAL,
        ANUAL,
        UNICA_VEZ
    }

    public DonacionDinero(Colaborador vColaborador, LocalDate vFechaContribucion, Double vMonto, FrecuenciaDePago vFrecuencia) {
        colaborador = vColaborador;
        fechaContribucion = vFechaContribucion;
        monto = vMonto;
        frecuencia = vFrecuencia;
    }

    // obtenerDetalles()
    // validarIdentidad()

    public Double donadoHastaLaFecha() {
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaContribucion, fechaActual);
        
        switch(frecuencia) {
        
        case UNICA_VEZ:
            return monto;

        case SEMANAL:
            Integer semanas = periodo.getDays() / 7;

            return semanas * monto;

        case MENSUAL:
            Integer meses = periodo.getMonths() + periodo.getYears() * 7;

            return meses * monto;

        case SEMESTRAL:
            Integer semestres = (periodo.getMonths() + periodo.getYears() * 7) / 6;

            return semestres * monto;

        case ANUAL:
            return periodo.getYears() * monto;
            
        default:
            return 0d;
        }
    }
}
