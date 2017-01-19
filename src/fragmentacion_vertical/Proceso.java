/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fragmentacion_vertical;

/**
 *
 * @author Alfredo Reyes
 */
public class Proceso {
    Integer n = 4;
    Integer k = 4;
    Integer m = 3;
    Integer matUse[][] = new Integer[k][n];
    Integer matAcc[][] = new Integer[k][m];
    
    Integer matAA[][] = new Integer[4][4];
    
    public void proceso(){
        
    }
    public void proceso(Integer matUse[][], Integer matAcc[][]){
        this.matUse = matUse;
        this.matAcc = matAcc;
    }
    public int aff(int ai, int aj){
        int sum=0;                
        for(int i=0; i<k; i++){
            if(matUse[i][ai]==1 && matUse[i][aj]==1){                    
                for(int j=0; j<m;j++){
                    sum=sum+matAcc[i][j];                    
                }
            }
        }
        return sum;
    }

    public int bond(int ax, int ay){
        int sum=0;                
        for(int z=0; z<n; z++){
               sum=sum+aff(z,ax)*aff(z, ay);                    
        }
        return sum;
    }  
    
    public int AM(){
        int sum=0;
        for(int j=1; j<=n; j++){
               sum=sum+bond(j, j-1)+bond(j,j+1);                    
        }
        return sum;
    }
    
    public int cont(int Ai, int Ak, int Aj){
        int sum=0;
        sum= 2*bond(Ai, Ak)+2*bond(Ak, Aj)-2*bond(Ai,Aj);
        return sum;
    }
    
    public void calcularAA(){
        for(int i=0;i<n;i++){
            for(int j=0; j<n; j++){
                matAA[i][j]=aff(i, j);
                System.out.print(matAA[i][j]+" -  ");
            }
            System.out.print("\n");
        }
    }
    public void cargarDatosPrueba(){
        matUse[0][0] = 1;
        matUse[0][1] = 0;
        matUse[0][2] = 1;
        matUse[0][3] = 0;
        
        matUse[1][0] = 0;
        matUse[1][1] = 1;
        matUse[1][2] = 1;
        matUse[1][3] = 0;   
        
        matUse[2][0] = 0;
        matUse[2][1] = 1;
        matUse[2][2] = 0;
        matUse[2][3] = 1;

        matUse[3][0] = 0;
        matUse[3][1] = 0;
        matUse[3][2] = 1;
        matUse[3][3] = 1;  
        
        matAcc[0][0] = 15;
        matAcc[0][1] = 20;
        matAcc[0][2] = 10;
        
        matAcc[1][0] = 5;
        matAcc[1][1] = 0;
        matAcc[1][2] = 0;

        matAcc[2][0] = 25;
        matAcc[2][1] = 25;
        matAcc[2][2] = 25;

        matAcc[3][0] = 3;
        matAcc[3][1] = 0;
        matAcc[3][2] = 0;   
    }
}