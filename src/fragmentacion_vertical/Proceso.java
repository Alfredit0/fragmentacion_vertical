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
    Integer matCA[][] = new Integer[4][4];
    
    String arrAtr[]= {"A1","A2","A3","A4"};
    String arrOrd[]= new String[4];        
        
    Integer bandBEA = 0;
    
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
        //System.out.println("********* LOS VALORES RECIBIDOS EN EL METODO BOND SON: "+ ax + " y "+ay);
        int sum=0;                
        if((ax<0||ax>3)||(ay<0||ay>3)){
            sum=0;
        }else{
            for(int z=0; z<n; z++){
                //System.out.println("Afinadad de: A"+z+" y A"+ax+" es: = "+aff(z,ax));
                   sum=sum+aff(z,ax)*aff(z, ay);                    
            }
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
        //System.out.println(" "+bond(Ai, Ak)+" "+bond(Ak, Aj)+" "+ bond(Ai,Aj));
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
    public void calcularCA(){        
        Integer[][]matOrd = {{-1, 2, 0},{0,2,1},{1,2,3},{-1,3,0},{0,3,1},{1,3,2},{1,2,3}};                
                                
        bandBEA=1;
        System.out.println("cont(A0;A3;A1) es: "+cont(-1, 2, 0));
        System.out.println("cont(A1;A3;A2) es: "+cont(0, 2, 1));
        System.out.println("cont(A2;A3;A4) es: "+cont(1, 2, 3));
        for(int i=0;i<2;i++){
            for(int j=0; j<n; j++){
              matCA[i][j]=matAA[i][j];
            }
            arrOrd[i]=arrAtr[i];
        }
        
        int ord=0; //orden aceptado
        if(cont(-1,2,0)>cont(0,2,1)){
            if(cont(-1,2,0)>cont(1,2,3)){
                ord=1;
            }else{
                ord=3;
            }
        }else{
            if(cont(0,2,1)>cont(1,2,3)){
                ord=2;
            }else{
                ord=3;
            }
        }
        
        System.out.println(ord);
        for(int i=0; i<n; i++){
            for(int j=0; j<3; j++){
                matCA[i][j]=matAA[i][matOrd[ord-1][j]];
                System.out.print(matCA[i][j]+" -");
            }
            System.out.println();
        }

        for(int i=0; i<3; i++){
            arrOrd[i]=arrAtr[matOrd[ord-1][i]];
            System.out.print(arrOrd[i]+" ");
        }
        
        ord=0;
        int contMayor=0;
        for(int i=3; i<7; i++){
            if(cont(matOrd[i][0],matOrd[i][1], matOrd[i][2])>contMayor){
                contMayor=cont(matOrd[i][0],matOrd[i][1], matOrd[i][2]);
                ord=i;
            }
        }
        
        /*for(int i=0; i<n; i++){
            for(int j=0; j<4; j++){
                matCA[i][j]=matAA[i][matOrd[ord-1][j]];
                System.out.print(matCA[i][j]+" -");
            }
            System.out.println();
        }

        for(int i=0; i<4; i++){
            arrOrd[i]=arrAtr[matOrd[ord-1][i]];
            System.out.print(arrOrd[i]+" ");
        }*/
        
    }
    
    public Integer[][] getMatUse() {
        return matUse;
    }

    public void setMatUse(Integer[][] matUse) {
        this.matUse = matUse;
    }

    public Integer[][] getMatAcc() {
        return matAcc;
    }

    public void setMatAcc(Integer[][] matAcc) {
        this.matAcc = matAcc;
    }
    
    public Integer[][] getMatAA() {
        return matAA;
    }

    public void setMatAA(Integer[][] matAA) {
        this.matAA = matAA;
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