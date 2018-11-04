package atasco;

import aima.core.search.framework.evalfunc.HeuristicFunction;
import atasco.AtascoEstado;



/**
 *Explicación de la heurística:
 *
 *ESTO ERA LA IDEA INICIAL
 *Contamos el número de casillas entre el coche rojo y la puerta. 
 *A eso le sumamos, por cada casilla bloqueada (está ocupada) el número de casillas que harían
 *falta para que el camino a la salida quedara libre.
 *
 *Es una heurística admisible:
 *Nunca el coste va a ser menor, como mucho igual -> En el caso de que los coches que obstruyen se 
 *	movieran sin tener que mover otro coche previamente y, en el mejor, de los sentidos (es decir, en
 *	el que menos movimientos necesita para desbloquear la puerta).
 *
 *Ver si es consistente!!! *********************************************************
 */


public class AtascoHeuristicFunction implements HeuristicFunction{

	
	    @Override
	    public double h(Object o) {

	    	double valorHeur = 0;
	        AtascoEstado estado = (AtascoEstado) o;

	        if(estado.getCocheRojo().getOrientacion().equals("H")) {//Coche rojo en vertical
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
	        System.out.print("Valar heuristico: " + valorHeur + "\n ");
	        return valorHeur;
	    }
	
	
}
