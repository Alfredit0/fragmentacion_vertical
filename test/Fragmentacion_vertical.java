
import fragmentacion_vertical.Proceso;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 706
 */
public class Fragmentacion_vertical {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Proceso proc = new Proceso();
        proc.cargarDatosPrueba();        
//        System.out.println("La afinidad entre A1 y A3 es: "+proc.aff(1, 2));        
        proc.calcularAA();        
//        System.out.println("Es bond de 1 y 4 es: " +proc.bond(0,3));
//        System.out.println("Es bond de 4 y 2 es: " +proc.bond(3,1));
//       System.out.println("Es bond de 3 y 4 es: " +proc.bond(2,3));
        
//        System.out.println("cont(A1;A4;A2) es: "+proc.cont(0, 3, 1));
        
//        System.out.println("cont(A0;A3;A1) es: "+proc.cont(-1, 2, 0));
//        System.out.println("cont(A1;A3;A2) es: "+proc.cont(0, 2, 1));
//       System.out.println("cont(A2;A3;A4) es: "+proc.cont(1, 2, 3));
        
        proc.calcularCA();
        
//        System.out.println("El valor AM de la matriz original es: "+proc.AM());
        
    }
    
}
