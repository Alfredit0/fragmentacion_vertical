package fragmentacion_vertical;

import java.util.Scanner;

public class Fragmentacion {
        int numAt = 4;
        int numQue = 4;
        int numSit = 3;
        int numAtClave=0;
        int [][] matUse = new int [4][4];
        int [][] matAcc = new int [4][4];
        int [][] matAA = new int [4][4];
        int [][] matCA = new int [4][4]; //Matriz de afinidad closterizada
        int [] ordenAtri = new int[4];
        String [] atriClave = new String[4];
        String [] nombresAtri = new String[4];
        
    public void realizarProceso() {

        nombresAtri[0]="A1";
        nombresAtri[1]="A2";
        nombresAtri[2]="A3";
        nombresAtri[3]="A4";
        //Llenar matriz de uso de atributo
        //System.out.println("\n\nProporciona valores para la matriz de uso de atributo");
        //LlenarMatriz(matUse,numQue,numAt,"Atributo",entrada);
        //Llenar matriz de frecuencia de acceso
        //System.out.println("\n\nProporciona valores para la matriz de frecuencia de acceso");
        //LlenarMatriz(matAcc,numAt,numSit,"Sitio",entrada);
        CalcularMatAffAtri(matAA,matUse,matAcc,numQue,numAt,numSit);
        CalcularMatAff_CA(matCA,matAA,ordenAtri,numAt);
        CalcularParticiones(matCA,matUse,matAcc,ordenAtri,nombresAtri,
                atriClave,numAt,numQue,numSit,numAtClave);
    }

    private static void CalcularMatAffAtri(int[][] matAA, int[][] matUse, 
            int[][] matAcc,int numQue,int numAt, int numSit) {
        for (int fila = 0; fila < numAt; fila++) {
            for (int columna = fila; columna < numAt; columna++) {
                matAA[fila][columna] = Afinidad(fila,columna,matUse,
                        matAcc,numQue,numSit);
                matAA[columna][fila] = matAA[fila][columna];
            }
        }
        System.out.println("Matriz de Afinidad AA");
        ImprimirMatriz(matAA, numAt, numAt);
        //Continuar();
    }

    private static int Afinidad(int Atri1, int Atri2, int[][] matUse, 
            int[][] matAcc, int numQue, int numSit) {
        int aff_A1_A2 = 0;
        //Recorrer las querys de la matriz de uso de atributo para calcular aff(Atri1,Atri2)
        for (int fila = 0; fila < numQue; fila++) {
            if(matUse[fila][Atri1] == 1 && matUse[fila][Atri2] == 1){
                for (int columna = 0; columna < numSit; columna++) {
                    aff_A1_A2 = aff_A1_A2 + matAcc[fila][columna];
                }
            }
        }
        return aff_A1_A2;
    }

    private static void CalcularMatAff_CA(int[][] matCA, int[][] matAA, 
            int[] ordenColumnas, int numAt) {
        int index;
        int[] cont = new int[20];
        //int[] ordenColumnas = new int[numAt];
        int[] ordenFilas = new int[20];
        int loc;
        //Pasar las primeras dos columnas
        for (int fila = 0; fila < numAt; fila++) {
            for (int columna = 0; columna < 2; columna++) {
                matCA[fila][columna] = matAA[fila][columna];
            }
        }
        System.out.println("Matriz de Afinidad Closterizada Primeras dos columnas");
        ImprimirMatriz(matCA, numAt, 2);
        //Continuar();
        index = 2;
        ordenColumnas[0] = 0;
        ordenColumnas[1] = 1;
        //Guardando posición original de las filas
        for (int pos = 0; pos < numAt; pos++) {
            ordenFilas[pos] = pos;
        }
        while(index < numAt){
            for (int columna = 0; columna < index; columna++) {
                cont[columna]=CalcularCont(matCA,matAA,columna-1,index,
                        columna,numAt,index);
            }
            cont[index]=CalcularCont(matCA,matAA,index-1,index,index+1,
                    numAt,index);
            loc = ObtenerPosNumMay(cont,index);//Posición número mayor
            for (int fila = 0; fila < numAt; fila++) {
                for (int columna = index; columna >loc; columna--) {
                    matCA[fila][columna] = matCA[fila][columna-1];
                }
                matCA[fila][loc] = matAA[fila][index];
            }
            System.out.println("Matriz de Afinidad Closterizada con "+(index+1)+
                    " columnas");
            ImprimirMatriz(matCA, numAt, index+1);
            //Continuar();
            //guardando el orden de columnas
            for (int pos = index; pos >loc; pos--) {
                ordenColumnas[pos] = ordenColumnas[pos-1];
            }
            ordenColumnas[loc]= index;
            index = index + 1;
        }
        //Ordenando las filas de acuerdo a las columnas
        int aux;
        for (int pos = 0; pos < numAt; pos++) {
            if(ordenFilas[pos] != ordenColumnas[pos]){
                for (int columna = 0; columna < numAt; columna++) {
                    aux = matCA[pos][columna];
                    matCA[pos][columna] = matCA[ordenColumnas[pos]][columna];
                    matCA[ordenColumnas[pos]][columna] = aux;
                }
                aux = ordenFilas[pos];
                ordenFilas[pos] = ordenColumnas[pos];
                ordenFilas[ordenColumnas[pos]] = aux;
            }
        }
        System.out.println("Matriz de Afinidad Closterizada ordenada");
        ImprimirMatriz(matCA,numAt,numAt);
        //Continuar();
    }

    private static int ObtenerPosNumMay(int[] cont, int index) {
        int posNumMay = 0;
        int numMayor = cont[0];
        for (int i = 0; i <= index; i++) {
            if (cont[i]>numMayor){
                numMayor=cont[i];
                posNumMay = i;
             } 
        }
        return posNumMay;
    }

    private static int CalcularCont(int[][] matCA, int[][] matAA, 
            int atri1, int atri2, int atri3, int numAt, int columnasCA) {
        int cont;
        cont = 2 * CalcularBond(matCA,matAA,atri1,atri2,numAt,
                columnasCA)+2 * CalcularBond(matCA,matAA,atri2,atri3,
                numAt,columnasCA)-2 * CalcularBond(matCA,matAA,atri1,
                atri3,numAt,columnasCA);
        return cont;
    }

    private static int CalcularBond(int[][] matCA, int[][] matAA, 
            int atributo1, int atributo2, int numAt,int columnasCA) {
        int bond =0;
        if(atributo1<0 || atributo2>columnasCA){
            bond=0;
        }else{
            if(atributo2==columnasCA){
                for (int columna = 0; columna < numAt; columna++) {
                    bond = bond + matCA[columna][atributo1] * matAA[columna][atributo2];
                }
            }else if(atributo1==columnasCA){
                for (int columna = 0; columna < numAt; columna++) {
                    bond = bond + matCA[columna][atributo2] * matAA[columna][atributo1];
                }
            }else{
                for (int columna = 0; columna < numAt; columna++) {
                    bond = bond + matCA[columna][atributo1] * matCA[columna][atributo2];
                }
            }
        }
        return bond;
    }

    private static void ImprimirMatriz(int[][] matriz, int totFilas, int totColumnas) {
        for (int fila = 0; fila < totFilas; fila++) {
            for (int columna = 0; columna < totColumnas; columna++) {
                System.out.print("["+ matriz[fila][columna]+ "]\t");
            }
            System.out.println("");
        }
    }


    private static void CalcularParticiones(int[][] matCA, int[][] matUse,
            int[][] matAcc, int[] ordenAtri, String[] nombresAtri, String[] atriClave, int numAt, int numQue, int numSit, int numAtClave) {
        int CTQ, CBQ, COQ, best,z,puntoDivision=0;
        int [] nuevoOrdenCol = new int[20];
        int [] ordenColumnasPuntoMáximo = new int[20];
        int auxiliar = ordenAtri[0];
        nuevoOrdenCol = ordenAtri;
        int [] queryTQ = new int[20];
        int [] queryBQ = new int[20];
        int [] querysT = new int[1];
        int [] querysB = new int[1];
        CTQ = CalcularCTQ(matCA,matUse,matAcc,queryTQ,numAt-1,ordenAtri,numAt,numQue,numSit,querysT);
        CBQ = CalcularCBQ(matCA,matUse,matAcc,queryBQ,numAt-1,ordenAtri,numAt,numQue,numSit,querysB);
        COQ = CalcularCOQ(matCA,matUse,matAcc,numAt-1,ordenAtri,numAt,numQue,numSit,queryTQ,queryBQ,querysT,querysB);
        best = CTQ * CBQ - (COQ*COQ);
        do{
            for (int columna = numAt-2; columna >= 1; columna--) {
                for (int query = 0; query < numQue; query++) {
                    queryTQ[query] = 0;
                    queryBQ[query] = 0;
                }
                
                CTQ = CalcularCTQ(matCA,matUse,matAcc,queryTQ,columna,nuevoOrdenCol,numAt,numQue,numSit,querysT);
                CBQ = CalcularCBQ(matCA,matUse,matAcc,queryBQ,columna,nuevoOrdenCol,numAt,numQue,numSit,querysB);
                COQ = CalcularCOQ(matCA,matUse,matAcc,columna,ordenAtri,numAt,numQue,numSit,queryTQ,queryBQ,querysT,querysB);
                z = CTQ * CBQ - (COQ*COQ);
                if(z>best){
                    puntoDivision = columna;
                    ordenColumnasPuntoMáximo = nuevoOrdenCol;
                }
            }
            SHIFT(nuevoOrdenCol,numAt);
            
        }while(auxiliar!= nuevoOrdenCol[numAt-1]);
        
        //Fragmentos de TQ
        System.out.println("\n\nFRAGMENTOS 1");
        boolean esClave = false;
        for (int columna = 0; columna < puntoDivision; columna++) {
            for (int clave = 0; clave < numAtClave; clave++) {
                if(atriClave[clave].equals(nombresAtri[(ordenAtri[ordenColumnasPuntoMáximo[columna]]+1)-1])){
                    esClave = true;
                }
            }
            if(!esClave){
                System.out.println("Atributo: "+nombresAtri[(ordenAtri[ordenColumnasPuntoMáximo[columna]]+1)-1]);
            }
            esClave = false;
        }
        for (int clave = 0; clave < numAtClave; clave++) {
            System.out.println("Atributo: "+atriClave[clave]);
        }
        //Continuar();
        //Fragmntos de BQ
        System.out.println("\nFRAGMENTOS 2");
        for (int columna = puntoDivision; columna < numAt; columna++) {
            esClave = false;
            for (int clave = 0; clave < numAtClave; clave++) {
                if(atriClave[clave].equals(nombresAtri[(ordenAtri[ordenColumnasPuntoMáximo[columna]]+1)-1])){
                    esClave = true;
                }
            }
            if(!esClave){
                System.out.println("Atributo: "+nombresAtri[(ordenAtri[ordenColumnasPuntoMáximo[columna]]+1)-1]);
            }
        }
        for (int clave = 0; clave < numAtClave; clave++) {
            System.out.println("Atributo: "+atriClave[clave]);
        }
    }

    private static int CalcularCTQ(int[][] matCA, int[][] matUse, int[][] matAcc, int[] queryTQ, int puntoDivision, int[] nuevoOrdenCol, int numAt, int numQue, int numSit,int[] totquerysT) {
        int CTQ = 0;
        int querys = BuscarQueryTQ(matUse,nuevoOrdenCol,queryTQ,numAt,numQue,puntoDivision);
        CTQ = SumarFrecuencias(matAcc,queryTQ,querys,numSit);
        totquerysT[0] = querys;
        return CTQ;
    }

    private static int CalcularCBQ(int[][] matCA, int[][] matUse, int[][] matAcc, int[] queryBQ, int puntoDivision, int[] nuevoOrdenCol, int numAt, int numQue, int numSit,int[] totquerysB) {
        int CBQ = 0;
        int querys = BuscarQueryBQ(matUse,nuevoOrdenCol,queryBQ,numAt,numQue,puntoDivision);
        CBQ = SumarFrecuencias(matAcc,queryBQ,querys,numSit);
        totquerysB[0] = querys;
        return CBQ;
    }

    private static int BuscarQueryTQ(int[][] matUse,int[] nuevoOrdenCol, int[] queryTQ, int numAt,int numQue, int puntoDivision) {
        int querys = 0;
        for (int aplicaciones = 0; aplicaciones < numQue; aplicaciones++) {
            for (int atributo = 0; atributo < puntoDivision; atributo++) {
                if(matUse[aplicaciones][nuevoOrdenCol[atributo]] == 1){
                    queryTQ[querys] = aplicaciones;
                    querys++;
                }
            }
        }
        return querys;
    }

    private static int BuscarQueryBQ(int[][] matUse, int[] nuevoOrdenCol, int[] queryBQ, int numAt, int numQue, int puntoDivision) {
        int querys = 0;
        for (int aplicaciones = 0; aplicaciones < numQue; aplicaciones++) {
            for (int atributo = puntoDivision; atributo < numAt; atributo++) {
                if(matUse[aplicaciones][nuevoOrdenCol[atributo]] == 1){
                    queryBQ[querys] = aplicaciones;
                    querys++;
                }
            }
        }
        return querys;
    }

    private static int SumarFrecuencias(int[][] matAcc, int[] queryTQ, int querys, int numSit) {
        int frecuencias = 0;
        for (int query = 0; query < querys; query++) {
            for (int querysFrec = 0; querysFrec < numSit; querysFrec++) {
                frecuencias = frecuencias + matAcc[queryTQ[query]][querysFrec];
            }
        }
        return frecuencias;
    }

    private static int CalcularCOQ(int[][] matCA, int[][] matUse, int[][] matAcc, int columna, int[] ordenAtri, int numAt, int numQue, int numSit, int[] queryTQ, int[] queryBQ, int[] querysT, int[] querysB) {
        int querysCOQ = 0;
        int [] querysOQ = new int[20];
        int querysOQ_TQ=0;
        int [] querysOQ_TQ_BQ = new int[20];
        boolean encontrado=false;
        //Eliminamos querys de R que aparecen en el conjunto TQ
        for (int query = 0; query < numAt; query++) {
            for (int tq = 0; tq < querysT[0]; tq++) {
                if(ordenAtri[query]==queryTQ[tq]){
                    encontrado = true;
                }
            }
            if(encontrado == false){
                querysOQ[querysOQ_TQ] = ordenAtri[query];
                querysOQ_TQ++;
            }
            encontrado = false;
        }
        //querysCOQ=0;
        //Eliminamos querys de R que aparecen en el conjunto BQ
        for (int query = 0; query < querysOQ_TQ; query++) {
            for (int bq = 0; bq < querysB[0]; bq++) {
                if(querysOQ[query]==queryBQ[bq]){
                    encontrado = true;
                }
            }
            if(encontrado == false){
                querysOQ_TQ_BQ[querysCOQ]=querysOQ[query];
                querysCOQ++;
            }
            encontrado = false;
        }
        //Calculamos frecuencias de querysCOQ
        int COQ = SumarFrecuencias(matAcc,querysOQ_TQ_BQ,querysCOQ,numSit);
        return COQ;
    }

    private static void SHIFT(int[] nuevoOrdenCol, int numAt) {
        int aux;
        aux=nuevoOrdenCol[0];
        for (int columna = 0; columna < numAt-1; columna++) {
            nuevoOrdenCol[columna] = nuevoOrdenCol[columna+1];
        }
        nuevoOrdenCol[numAt-1]=aux;
    }

    private static void Continuar() {
        int c;
        Scanner entrada = new Scanner(System.in);
        System.out.print("Presiona 1 para continuar: ");
        do{
            c = entrada.nextInt();
        }while(c!=1);
    }
    
    
}