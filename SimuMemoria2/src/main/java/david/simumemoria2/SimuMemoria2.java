package david.simumemoria2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author david
 */
public class SimuMemoria2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int memoriaTotal=1200;
        List<Particion2> particiones = new ArrayList<>();
        List<Proceso2> procesos = new ArrayList<>();
        
        System.out.println("Cuantas particiones vas a querer? (memoria máxima de 1000) ");
        int numPart = scanner.nextInt();
        int total=0;
        for (int i = 1; i <= numPart+1; i++) {
            if(i==1){
                particiones.add(new Particion2(1,200));
                total=total+200;
            }else{
             System.out.print("Ingrese el tamaño de la partición " + i + " : ");
              int tamParticion = scanner.nextInt();
              total=total+tamParticion;
              if(total>memoriaTotal){
                  System.out.println("Espacio insuficiente en la memoria para asignar la particion ");
                   break;
            }
                particiones.add(new Particion2(i, tamParticion));                  
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
            
            procesos.add(new Proceso2(nombrePro, tamanoPro));
        }
        
        for(int i = 0; i < procesos.size(); i++ ){
            Proceso2 proceso= procesos.get(i);
            boolean asignado = false;
           
            for(int j = 1; j < particiones.size(); j++){
                Particion2 particion = particiones.get(j);
                if(!particion.ocupada && particion.tamano >= proceso.tamano){
                    particion.ocupada = true;
                    particion.nombreProceso = proceso.nombre;
                    particion.tamano = particion.tamano - proceso.tamano;
                    asignado = true;
                    tamPart=tamPart+proceso.tamano;
                    System.out.println(proceso.nombre +" asignado a la partición "+ particion.id);
                    break; 
                }
                
            }
            if (!asignado) {
                System.out.println("El proceso " + proceso.nombre + " no pudo ser asignado a ninguna partición.");
            }
        }
        int libre=memoriaTotal-tamPart;
         System.out.println("Hay " + libre+ " espacios de tamaño libre");
        
    }
               
    }

