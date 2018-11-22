package atasco;

import aima.core.search.framework.evalfunc.HeuristicFunction;
import atasco.AtascoEstado;



/**
 *Explicación de la heurística:
 *
 * Consiste en contar las casillas que quedan le quedan al coche rojo
 *  hasta llegar a la puerta y para cada una de esas casillas sumarle 1 a la heurística,
 *  con la intución de que hay que hacer un movimiento más para desplazar ese coche.
 *  Más info en la memoria. 
 */


public class AtascoHeuristicFunction implements HeuristicFunction{

	
	    @Override
	    public double h(Object o) {

	    	double valorHeur = 0;
	        AtascoEstado estado = (AtascoEstado) o;

	        if(estado.getCocheRojo().getOrientacion().equals("H")) {//Coche rojo en horizontal
	        	if(estado.getColumnaPuerta()==0) {//Tenemos la puerta a la izquierda
	        		int numPasos = estado.getCocheRojo().getColumna(); //casillas desde el coche a la puerta
	        		valorHeur = numPasos;
	        		for(int i=0; i < numPasos; i++ ) {//Para cada una vemos si están ocupadas
	        			if(estado.getOcupadas()[estado.getCocheRojo().getFila()][i] != -1) {//casilla ocupada
	        				valorHeur +=1;
	        			}
	        		}
	        	}
	        	else {//Tenemos la puerta a la derecha
	        		System.out.print("Tiene que entrar por aqui \n ");
	        		int numPasos = 5 - (estado.getCocheRojo().getColumna() + 1) ; //casillas desde el coche a la puerta
	        		System.out.print("Columna del coche rojo: " + estado.getCocheRojo().getColumna() + "\n");
	        		System.out.print("Numero de casillas a la puerta: " + numPasos + "\n");
	        		//el +1 es porque el coche acaba en estado.getCocheRojo().getColumna()+1
	        		valorHeur = numPasos;
	        		for(int i=estado.getCocheRojo().getColumna() + 2; i <= 5; i++ ) {//Para cada una vemos si están ocupadas
	        			if(estado.getOcupadas()[estado.getCocheRojo().getFila()][i] != -1) {//casilla ocupada
	        				System.out.print("Casilla " + "(" +estado.getCocheRojo().getFila() +  "," + (i) + ")" + " ocupada \n");
	        				valorHeur +=1;
	        			}
	        		}
	        	}
	        }
	        else {//Coche rojo en vertical
	        	if(estado.getFilaPuerta()==0) {//Tenemos la puerta arriba
	        		int numPasos = estado.getCocheRojo().getFila(); //casillas desde el coche a la puerta
	        		valorHeur = numPasos;
	        		for(int i=0; i < numPasos; i++ ) {//Para cada una vemos si están ocupadas
	        			if(estado.getOcupadas()[i][estado.getCocheRojo().getColumna()] != -1) {//casilla ocupada
	        				valorHeur +=1;
	        			}
	        		}
	        	}
	        	else {//Tenemos la puerta abajo
	        		int numPasos = 5 - estado.getCocheRojo().getFila()+1 ; //casillas desde el coche a la puerta
	        		//el +1 es porque el coche acaba en estado.getCocheRojo().getFila()+1
	        		valorHeur = numPasos;
	        		for(int i=estado.getCocheRojo().getFila() + 2; i <= 5; i++ ) {//Para cada una vemos si están ocupadas
	        			if(estado.getOcupadas()[i][estado.getCocheRojo().getColumna()] != -1) {//casilla ocupada
	        				valorHeur +=1;
	        			}
	        		}
	        	}
	        	
	        }
	        System.out.print("Valor heuristico: " + valorHeur + "\n ");
	        return valorHeur;
	    }
	
	
}
