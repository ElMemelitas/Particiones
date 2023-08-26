package david.mejorajuste;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author david
 */
public class MejorAjuste {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int memoriaTotal=2200;
        List<ParticionMA> particiones = new ArrayList<>();
        List<ProcesoMA> procesos = new ArrayList<>();
        
        System.out.println("Cuantas particiones vas a querer? (memoria máxima de 2000) ");
        int numPart = scanner.nextInt();
        int total=0;
        for (int i = 1; i <= numPart+1; i++) {
            if(i==1){
                particiones.add(new ParticionMA(1,200));
                total=total+200;
            }else{
             System.out.print("Ingrese el tamaño de la partición " + i + " : ");
              int tamParticion = scanner.nextInt();
              total=total+tamParticion;
              if(total>memoriaTotal){
                  System.out.println("Espacio insuficiente en la memoria para asignar la particion ");
                   break;
            }
                particiones.add(new ParticionMA(i, tamParticion));                  
            }
         
        }

        System.out.println("Cuantos procesos vas a querer?");
        int numPro = scanner.nextInt();
        int tamPart=200;
        int tamanoPro = 0;
        for(int i = 1; i <=numPro ;i ++){
             System.out.print("Ingrese el nombre del proceso " + i + " : ");
            String nombrePro = scanner.next();
            System.out.print("Ingrese el tamaño del proceso " + i + ": ");
            tamanoPro = scanner.nextInt();
            
            procesos.add(new ProcesoMA(nombrePro, tamanoPro));
        }
        
        int ultimo=1;
        for(int i = 0; i < procesos.size(); i++ ){
            ProcesoMA proceso= procesos.get(i);
            boolean asignado = false;
            ParticionMA mejorParticion = null;
           
            for(int j = ultimo; j < particiones.size(); j++){
                ParticionMA particion = particiones.get(j);
                if(!particion.ocupada && particion.tamano >= proceso.tamano){
                    if (mejorParticion == null || particion.tamano < mejorParticion.tamano) {
                        mejorParticion = particion;
                    }
                }      
            }
            
            if (mejorParticion != null) {
                mejorParticion.ocupada = true;
                mejorParticion.nombreProceso = proceso.nombre;
                mejorParticion.tamano -= proceso.tamano;
                asignado = true;
                 tamPart=tamPart+proceso.tamano;
                 
                System.out.println(proceso.nombre + " asignado a la partición " + mejorParticion.id);
            }
            
            if (!asignado) {
                System.out.println("El proceso " + proceso.nombre + " no pudo ser asignado a ninguna partición.");
            }
        }
         int libre=memoriaTotal-tamPart;
         System.out.println("Hay " + libre+ " espacios de tamaño libre");

         
    }
}
