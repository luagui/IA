package atasco;


import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.Pair;

public class AtascoEstado {
	
	//movimiento en horizontal
	public static Action H = new DynamicAction("H");
	//movimiento en vertical
    public static Action V = new DynamicAction("V");
	
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
    private int numCoches;
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
    	this.numCoches = 8;
    	
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
    	
    	
    	for(int i = 0; i < numCoches; i++) {
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
    
}
