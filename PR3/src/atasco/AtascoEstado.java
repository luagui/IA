package atasco;


import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.Pair;

public class AtascoEstado {
	
	// Suponemos un numero constate de vehiculos: 8
	// V0A = Movimiento A del vehiculo 0:
 	// Si está en horizontal  ===> A = Izq | B = Drch
	// Si está en vertical ===> A = Arriba | B = Abajo
	
	public static Action V0A = new DynamicAction("V0A");
    public static Action V0B = new DynamicAction("V0B");
    public static Action V1A = new DynamicAction("V1A");
    public static Action V1B = new DynamicAction("V1B");
    public static Action V2A = new DynamicAction("V2A");
    public static Action V2B = new DynamicAction("V2B");
    public static Action V3A = new DynamicAction("V3A");
    public static Action V3B = new DynamicAction("V3B");
    public static Action V4A = new DynamicAction("V4A");
    public static Action V4B = new DynamicAction("V4B");
    public static Action V5A = new DynamicAction("V5A");
    public static Action V5B = new DynamicAction("V5B");
    public static Action V6A = new DynamicAction("V6A");
    public static Action V6B = new DynamicAction("V6B");
    public static Action V7A = new DynamicAction("V7A");
    public static Action V7B = new DynamicAction("V7B");
    
	
    //Representamos el estado con un vector de Vehiculos
    /*
     * La orientacion será H o V e indicará si el coche está en horizontal o vertical
     * 
     * Además usamos una matriz 6x6 de booleanos "ocupadas" donde iremos guardando casillas
     * ocupadas con true, o libres con false*/
    
    //***************************************************************************
	
    private boolean ocupadas [][];
    private int numFilas;
    private int numColumnas;
    private Par <Integer,Integer> puerta;
    private Vehiculo vehiculos[]; 
    

    /*
     * Constructora por defecto. Problema del enunciado.
     */
    public AtascoEstado()
    {
    	//Los Arrays por defecto se inicializan a false (según stack overflow)
    	this.ocupadas  = new boolean [6][6];
    	this.puerta = new Par<Integer, Integer>(2,5);
    	this.vehiculos = new Vehiculo[8];
    	this.numFilas = 6;
    	this.numColumnas = 6;
    	
    	//Creacion de los vehiculos del enunciado:
    	
    	vehiculos[0] = new Vehiculo(2, 2, "H", 0); //Coche Rojo
    	vehiculos[1] = new Vehiculo(0,4,"V", 1); //Coche Verde
    	vehiculos[2] = new Vehiculo(0,5,"V", 2); //Camion Amarillo
    	vehiculos[3] = new Vehiculo(1,2,"H", 1); //Coche Naranja
    	vehiculos[4] = new Vehiculo(2,4,"V", 1); //Coche Azul 
    	vehiculos[5] = new Vehiculo(3,2,"V", 2); //Camion Morado
    	vehiculos[6] = new Vehiculo(4,3,"V", 1); //Coche Rosa
    	vehiculos[7] = new Vehiculo(4,4,"H", 1); //Coche Morado

    	actualizarOcupadas();
    	
    }
	
    /*
     * Constructor con paso de estado por parámetro*/
    public AtascoEstado (AtascoEstado copia) {
    	this.numFilas = copia.getNumFilas();
    	this.numColumnas = copia.getNumColumnas();
    	this.puerta = new Par <Integer, Integer> (copia.getFilaPuerta(), copia.getColumnaPuerta());
    	this.ocupadas = copia.copiarOcupadas();
    	this.vehiculos = copia.copiarVehiculos();
    	
    }
    
    
    /*
     * Actualiza la matriz de booleanos, para saber qué casillas están ocupadas
     */
    public void actualizarOcupadas() {
    	
    	//Ponemos todas a false
    	for (int i = 0; i < numFilas; i++) {
    		for (int j = 0; j < numColumnas; j++){
    			ocupadas[i][j] = false;
    		}
    	}
    	
    	for(int i = 0; i < 8; i++) {
    		int fila = vehiculos[i].getFila();
    		int columna = vehiculos[i].getColumna();
    		
    		if(vehiculos[i].getOrientacion().equals("V")) {
    			ocupadas[fila][columna] = true;
				ocupadas[fila + 1][columna] = true;			
    			if (vehiculos[i].getTipo() == 2){ //camion en vertical, longitud 3
    				ocupadas[fila + 2][columna] = true;
    			}
    		} else { //Horizontal
    			ocupadas[fila][columna] = true;
				ocupadas[fila][columna + 1] = true;
				if (vehiculos[i].getTipo() == 2){ //camion en horizontal, longitud 3
    				ocupadas[fila][columna + 2] = true;
    			}
			}
    	}
    }
    /*
     *Función que nos devuelve true si el movimiento que queremos hacer es válido y false en 
     *otro caso */
    
    public boolean movimientoValido(Action movimiento){
    	
        boolean valido=false;
        
        if(movimiento.equals(V0A)){ 
        	//comprobamos movimiento A con el vehículo 0
        	valido = movimientoAValido(0);
        }
        else if(movimiento.equals(V1A)) {
        	//comprobamos movimiento A con el vehículo 1
        	valido = movimientoAValido(1);
        }
        else if(movimiento.equals(V2A)) {
        	//comprobamos movimiento A con el vehículo 2
        	valido = movimientoAValido(2);
        }
        else if(movimiento.equals(V3A)) {
        	//comprobamos movimiento A con el vehículo 3
        	valido = movimientoAValido(3);
        }
        else if(movimiento.equals(V4A)) {
        	//comprobamos movimiento A con el vehículo 4
        	valido = movimientoAValido(4);
        }
        else if(movimiento.equals(V5A)) {
        	//comprobamos movimiento A con el vehículo 5
        	valido = movimientoAValido(5);
        }
        else if(movimiento.equals(V6A)) {
        	//comprobamos movimiento A con el vehículo 6
        	valido = movimientoAValido(6);
        }
        else if(movimiento.equals(V7A)) {
        	//comprobamos movimiento A con el vehículo 7
        	valido = movimientoAValido(7);
        }
        
        else if(movimiento.equals(V0B)) {
        	//comprobamos movimiento B con el vehículo 0
        	valido = movimientoBValido(0);
        }
        
        else if(movimiento.equals(V1B)) {
        	//comprobamos movimiento B con el vehículo 1
        	valido = movimientoBValido(1);
        }
        
        else if(movimiento.equals(V2B)) {
        	//comprobamos movimiento B con el vehículo 2
        	valido = movimientoBValido(2);
        }
        else if(movimiento.equals(V3B)) {
        	//comprobamos movimiento B con el vehículo 3
        	valido = movimientoBValido(3);
        }
        else if(movimiento.equals(V4B)) {
        	//comprobamos movimiento B con el vehículo 4
        	valido = movimientoBValido(4);
        }
        else if(movimiento.equals(V5B)) {
        	//comprobamos movimiento B con el vehículo 5
        	valido = movimientoBValido(5);
        }
        else if(movimiento.equals(V6B)) {
        	//comprobamos movimiento B con el vehículo 6
        	valido = movimientoBValido(6);
        }
        else if(movimiento.equals(V7B)) {
        	//comprobamos movimiento B con el vehículo 7
        	valido = movimientoBValido(7);
        }
        return valido;
    }
    /*
     *Dado un coche, nos dice si un movimiento A sobre él sería válido*/
    
    public boolean movimientoAValido(int numV) {
    	
    	boolean valido = false;
    	if (vehiculos[numV].getOrientacion().equals("V")) {//está en vertical, movemos hacia arriba
    		//hay espacio en la fila de arriba y está libre
    		if(vehiculos[numV].getFila() > 0 && 
    				!ocupadas[vehiculos[numV].getFila()-1][vehiculos[numV].getColumna()]) {
    			valido = true;
    		}
    	}
    	else {//está en horizontal, movemos hacia la izquierda
    		//hay sitio a la izquierda y está libre
    		if(vehiculos[numV].getColumna() > 0 && 
    				!ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna()-1]) {
    			valido = true;
    		}
    	}
    	return valido;
    }
    
    /*
     *Dado un coche, nos dice si un movimiento B sobre él sería válido*/
    
    public boolean movimientoBValido(int numV) {
    	
    	boolean valido = false;
    	
    	if (vehiculos[numV].getOrientacion().equals("V")) { //Mover abajo
    		if (vehiculos[numV].getTipo() == 2) { //Camion
    			//hay hueco hacia abajo y está libre
    			if ((vehiculos[numV].getFila() + 2) < (numFilas - 1)&& 
    					!ocupadas[vehiculos[numV].getFila()+3][vehiculos[numV].getColumna()]) {
    				valido = true;
    			}
    		}
    		else { //coche
    			//hay hueco hacia abajo y está libre
    			if ((vehiculos[numV].getFila() + 1) < (numFilas - 1)&& 
    					!ocupadas[vehiculos[numV].getFila()+2][vehiculos[numV].getColumna()]) {
    				valido = true;
    			}
    		}
    	}
    	else {//Mover derecha
    		if (vehiculos[numV].getTipo() == 2) { //Camion
    			//hay hueco hacia la derecha y está libre
    			if ((vehiculos[numV].getColumna() + 2) < (numColumnas - 1)&& 
    					!ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna()+3]) {
    				valido = true;
    			}
    		}
    		else { //coche
    			//hay hueco hacia la derecha y está libre
    			if ((vehiculos[numV].getColumna() + 1) < (numColumnas - 1)&& 
    					!ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna()+2]) {
    				valido = true;
    			}
    		}
    	}
    	
    	return valido;
    }
    
    
    
    /*
     * Mover el vehiculo numV del array con el movimiento A
     * Las comprobaciones de si se puede hacer este moviemiento se hacen en otra funcion
     */
    public void moveA(int numV) {
    	if (vehiculos[numV].getOrientacion().equals("V")) { //Mover arriba
    		if (vehiculos[numV].getTipo() == 2) { //Camion
    			ocupadas[vehiculos[numV].getFila() + 2 ][vehiculos[numV].getColumna()] =  false;	
    		} else { //Coche
    			ocupadas[vehiculos[numV].getFila() + 1 ][vehiculos[numV].getColumna()] =  false;
    		}
    		ocupadas[vehiculos[numV].getFila() - 1 ][vehiculos[numV].getColumna()] =  true;
			vehiculos[numV].setFila(vehiculos[numV].getFila() - 1);
			
    	} else { //Mover a la izquierda
    		if (vehiculos[numV].getTipo() == 2) { //Camion
    			ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna() + 2] =  false;
    		} else { //Coche
    			ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna() + 1] =  false;
    		}
    		ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna() - 1] =  true;
			vehiculos[numV].setColumna(vehiculos[numV].getColumna() - 1);
    	}
    }
    
    /*
     * Mover el vehiculo numV del array con el movimiento B
     * Las comprobaciones de si se puede hacer este moviemiento se hacen en otra funcion
     */
    public void moveB(int numV) {
    	if (vehiculos[numV].getOrientacion().equals("V")) { //Mover abajo
    		if (vehiculos[numV].getTipo() == 2) { //Camion
    			
    			ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna()] =  false;
    			ocupadas[vehiculos[numV].getFila() + 3 ][vehiculos[numV].getColumna()] =  true;
    			
    		} else { //Coche
    			
    			ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna()] =  false;
    			ocupadas[vehiculos[numV].getFila() + 2 ][vehiculos[numV].getColumna()] =  true;
    		}
    		
			vehiculos[numV].setFila(vehiculos[numV].getFila() + 1);
			
    	} else { //Mover a la derecha
    		if (vehiculos[numV].getTipo() == 2) { //Camion
    			
    			ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna()] =  false;
    			ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna() + 3] =  true;
    			
    		} else { //Coche
    			ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna()] =  false;
    			ocupadas[vehiculos[numV].getFila()][vehiculos[numV].getColumna() + 2] =  true;
    		}
    		
			vehiculos[numV].setColumna(vehiculos[numV].getColumna() + 1);
    	}
    }
    
    public boolean equals(Object o) {
    	boolean eq = true;
    	
        if(this == o) return true;

        if((o == null || (this.getClass() != o.getClass()))) return false;

        AtascoEstado otroEstado = (AtascoEstado) o;
        
        for(int i=0; i < 8 && eq; i++) {
        
        	if (!vehiculos[i].equals(otroEstado.vehiculos[i])) {
        		eq = false;
        	}
        }        
        return eq;
    }
    
    public int hashCode() {
    	int hash = 0;
    	
    	for (int i = 0; i < 8; i++) {
    			hash += vehiculos[i].hashCode();
    		}
    	 return hash;	
    }

    public int getNumFilas() {
    	return this.numFilas;
    }
    
    public int getNumColumnas() {
    	return this.numColumnas;
    }
    
    public Vehiculo[] copiarVehiculos() {
    	return this.vehiculos.clone();
    }
    public boolean [][] copiarOcupadas() {
    	return this.ocupadas.clone();
    }
    
    public int getFilaPuerta() {
    	return this.puerta.daPrimero();
    }
    
    public int getColumnaPuerta() {
    	return this.puerta.daSegundo();
    }
    /*
     * Devuelve la posición de la puerta*/
    public Par<Integer,Integer> getPuerta() {
    	return this.puerta;
    }
    
    /*
     * Devuelve la posición del coche rojo*/
    public Vehiculo getCocheRojo() {
    	return this.vehiculos[0];
    }
    public boolean [][] getOcupadas(){
    	return this.ocupadas;
    }
}