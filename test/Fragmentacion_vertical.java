
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
        System.out.println("La afiinidad entre A1 y A3 es: "+proc.aff(1, 2));        
        proc.calcularAA();        
        System.out.println("Es bond de 1 y 4 es: " +proc.bond(0,3));
        System.out.println("Es bond de 4 y 2 es: " +proc.bond(3,1));
        System.out.println("Es bond de 1 y 2 es: " +proc.bond(0,1));
        
        System.out.println("cont(A1;A4;A2) es: "+proc.cont(0, 3, 1));
        
    }
    
}
