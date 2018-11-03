package atasco;

import aima.core.search.framework.problem.GoalTest;

//import aima.core.search.framework.GoalTest;

public class AtascoGoalTest implements GoalTest {

	    public boolean isGoalState(Object o) {
	        AtascoEstado estado = (AtascoEstado) o;
	        if (estado.getCocheRojo().getOrientacion().equals("H")) {
	        	//el coche rojo va a estar en la misma fila que la puerta
	        	//comprobamos si está en la misma columna
	        	int columnaPuerta = estado.getPuerta().daSegundo();
	        	
	        	if(columnaPuerta == (estado.getCocheRojo().getColumna() + 1) || //Puerta Izquierda 
	        			columnaPuerta == (estado.getCocheRojo().getColumna())) { //Puerta Derecha
	        		return true;
	        	}
	        	else
	        		return false;
	        }
	        else {
	        	//el coche rojo va a estar en la misma columna que la puerta
	        	//comprobamos si está en la misma fila
	        	int filaPuerta = estado.getPuerta().daPrimero();
	        	if(filaPuerta == (estado.getCocheRojo().getFila() + 1) || 
	        			filaPuerta == (estado.getCocheRojo().getFila())) {
	        		return true;
	        	}
	        	else 
	        		return false;
	        }
	    }
	

	
}
