package david.siguienteajuste;
/**
 *
 * @author david
 */
public class ParticionSA {
    int id, tamano;
    boolean ocupada;
    String nombreProceso;
    
    
    public ParticionSA(int id, int tamano){
        this.id = id;
        this.tamano = tamano;
        this.ocupada = false;
        this.nombreProceso = " ";
    }
}
