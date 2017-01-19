
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
        //System.out.println("La afiinidad es: "+proc.aff(1, 2));
        proc.calcularAA();
    }
    
}
