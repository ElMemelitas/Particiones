package david.siguienteajuste;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author david
 */
public class SiguienteAjuste {

    public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
        int memoriaTotal=2200;
        List<ParticionSA> particiones = new ArrayList<>();
        List<ProcesoSA> procesos = new ArrayList<>();
        
        System.out.println("Cuantas particiones vas a querer? (memoria máxima de 2000) ");
        int numPart = scanner.nextInt();
        int total=0;
        for (int i = 1; i <= numPart+1; i++) {
            if(i==1){
                particiones.add(new ParticionSA(1,200));
                total=total+200;
            }else{
             System.out.print("Ingrese el tamaño de la partición " + i + " : ");
              int tamParticion = scanner.nextInt();
              total=total+tamParticion;
              if(total>memoriaTotal){
                  System.out.println("Espacio insuficiente en la memoria para asignar la particion ");
                   break;
            }
                particiones.add(new ParticionSA(i, tamParticion));                  
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
            
            procesos.add(new ProcesoSA(nombrePro, tamanoPro));
        }
        
        int ultimo=1;
        for(int i = 0; i < procesos.size(); i++ ){
            ProcesoSA proceso= procesos.get(i);
            boolean asignado = false;
            
           
            for(int j = ultimo; j < particiones.size(); j++){
                ParticionSA particion = particiones.get(j);
                if(!particion.ocupada && particion.tamano >= proceso.tamano){
                    int sobraPart=0;
                    particion.ocupada = true;
                    particion.nombreProceso = proceso.nombre;
                    particion.tamano = particion.tamano - proceso.tamano;
                    asignado = true;
                    
                    tamPart=tamPart+proceso.tamano;
                    
                    sobraPart=particion.tamano-proceso.tamano;
                    particion.tamano=particion.tamano-sobraPart;
                    
                    System.out.println(proceso.nombre +" asignado a la partición "+ particion.id);
                    ultimo=j;
                    break; 
                }
                
            }
            if (!asignado) {
                for (int j = 1; j < ultimo; j++) {
                    ParticionSA particion = particiones.get(j);
                    if (!particion.ocupada && particion.tamano >= proceso.tamano) {
                        int sobraPart=0;
                        particion.ocupada = true;
                        particion.nombreProceso = proceso.nombre;
                        particion.tamano = particion.tamano - proceso.tamano;
                        asignado = true;
                        
                        tamPart = tamPart + proceso.tamano;
                        
                        sobraPart=particion.tamano-proceso.tamano;
                        particion.tamano=particion.tamano-sobraPart;
                        
                        System.out.println(proceso.nombre + " asignado a la partición " + particion.id);
                        ultimo = j;
                        break;
                    }
                }
            }
            
            
            if (!asignado) {
                System.out.println("El proceso " + proceso.nombre + " no pudo ser asignado a ninguna partición.");
            }
        }
         int libre=memoriaTotal-tamPart;
         System.out.println("Hay " + libre+ " espacios de tamaño libre");
         particiones.add(new ParticionSA(numPart+2, libre));
         System.out.println("Particion de  " +libre + " espacios creada en base a la memoria libre");
         
         for(int i=1;i<particiones.size();i++){
             ParticionSA particion = particiones.get(i);
                System.out.println("Particion  " +particion.id + " actualizada a "+particion.tamano+" espacios de tamaño");
         }
    }
}
