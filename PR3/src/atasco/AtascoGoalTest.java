package atasco;

import aima.core.search.framework.problem.GoalTest;


public class AtascoGoalTest implements GoalTest {

	    public boolean isGoalState(Object o) {
	        AtascoEstado estado = (AtascoEstado) o;
	        if (estado.getCocheRojo().getOrientacion().equals("H")) {
	        	//el coche rojo va a estar en la misma fila que la puerta
	        	//comprobamos si está en la misma columna
	        	int columnaPuerta = estado.getColumnaPuerta();
	        	
	        	if(columnaPuerta == (estado.getCocheRojo().getColumna() + 1) || //Puerta Derecha 
	        			columnaPuerta == (estado.getCocheRojo().getColumna())) { //PuertaIzquierda
	        		return true;
	        	}
	        	else
	        		return false;
	        }
	        else {
	        	//el coche rojo va a estar en la misma columna que la puerta
	        	//comprobamos si está en la misma fila
	        	int filaPuerta = estado.getFilaPuerta();
	        	if(filaPuerta == (estado.getCocheRojo().getFila() + 1) || 
	        			filaPuerta == (estado.getCocheRojo().getFila())) {
	        		return true;
	        	}
	        	else 
	        		return false;
	        }
	    }
	

	
}
