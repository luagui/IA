package atasco;

//import aima.core.search.framework.GoalTest;

public class AtascoGoalTest {

	
	    //private AtascoEstado goal = new AtascoEstado();

	 //   @Override
	    public boolean isGoalState(Object o) {
	        AtascoEstado estado = (AtascoEstado) o;
	        if (estado.getCocheRojo().getOrientacion().equals("H")) {
	        	//el coche rojo va a estar en la misma fila que la puerta
	        	//comprobamos si está en la misma columna
	        	if(estado.getPuerta().daSegundo()==estado.getCocheRojo().getColumna()) {
	        		return true;
	        	}
	        	else
	        		return false;
	        }
	        else {
	        	//el coche rojo va a estar en la misma columna que la puerta
	        	//comprobamos si está en la misma fila
	        	if(estado.getPuerta().daPrimero()==estado.getCocheRojo().getFila()) {
	        		return true;
	        	}
	        	else 
	        		return false;
	        }
	    }
	

	
}
