package david.simumemoria2;
/**
 *
 * @author david
 */
public class Particion2 {
    int id, tamano;
    boolean ocupada;
    String nombreProceso;
    
    
    public Particion2(int id, int tamano){
        this.id = id;
        this.tamano = tamano;
        this.ocupada = false;
        this.nombreProceso = " ";
    }
}
