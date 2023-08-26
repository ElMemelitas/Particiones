package david.mejorajuste;
/**
 *
 * @author david
 */
public class ParticionMA {
    int id, tamano;
    boolean ocupada;
    String nombreProceso;
    
    
    public ParticionMA(int id, int tamano){
        this.id = id;
        this.tamano = tamano;
        this.ocupada = false;
        this.nombreProceso = " ";
    }
}
