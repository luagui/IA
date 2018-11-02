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
     * Además usamos una matriz 6x6 de booleanos donde iremos guardando casillas
     * ocupadas con 1, o libres con 0*/
    
    //***************************************************************************
	
    private boolean ocupadas [][];
    private int numFilas;
    private int numColumnas;
    private Pair <Integer,Integer> puerta;
    private Vehiculo vehiculos[]; 
    
    /*
     * Constructora por defecto. Problema del enunciado.
     */
    public AtascoEstado()
    {
    	//Los Arrays por defecto se inicializan a false (según stack overflow)
    	this.ocupadas  = new boolean [6][6];
    	this.puerta = new Pair<Integer, Integer>(2,5);
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
     * Mover el vehiculo 0 del array con el movimiento A
     * Las comprobaciones de si se puede hacer este moviemiento se hacen en otra funcion
     */
    public void move0A() {
    	if (vehiculos[0].getOrientacion().equals("V")) { //Mover arriba
    		if (vehiculos[0].getTipo() == 2) { //Camion
    			ocupadas[vehiculos[0].getFila() + 2 ][vehiculos[0].getColumna()] =  false;	
    		} else { //Coche
    			ocupadas[vehiculos[0].getFila() + 1 ][vehiculos[0].getColumna()] =  false;
    		}
    		ocupadas[vehiculos[0].getFila() - 1 ][vehiculos[0].getColumna()] =  true;
			vehiculos[0].setFila(vehiculos[0].getFila() - 1);
			
    	} else { //Mover a la izquierda
    		if (vehiculos[0].getTipo() == 2) { //Camion
    			ocupadas[vehiculos[0].getFila()][vehiculos[0].getColumna() + 2] =  false;
    		} else { //Coche
    			ocupadas[vehiculos[0].getFila()][vehiculos[0].getColumna() + 1] =  false;
    		}
    		ocupadas[vehiculos[0].getFila()][vehiculos[0].getColumna() - 1] =  true;
			vehiculos[0].setColumna(vehiculos[0].getColumna() - 1);
    	}
    }
}
