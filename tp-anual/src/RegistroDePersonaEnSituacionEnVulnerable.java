package src;
import java.util.ArrayList;
import java.time.LocalDate;

public class RegistroDePersonaEnSituacionEnVulnerable extends Contribucion {
    private ArrayList<Tarjeta> tarjetasAsignadas;
    
    public RegistroDePersonaEnSituacionEnVulnerable(Colaborador vColaborador, LocalDate vFechaContribucion, ArrayList<Tarjeta> vTarjetasAsignadas) {
        colaborador = vColaborador;
        fechaContribucion = vFechaContribucion;
        tarjetasAsignadas = vTarjetasAsignadas;
    }

    public ArrayList<Tarjeta> tarjetasAsignadas(){
        return tarjetasAsignadas;
    }

    // obtenerDetalles()
    // validarIdentidad()
}