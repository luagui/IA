package atasco;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

public class AtascoEstado {
	
	//movimiento en horizontal
	public static Action H = new DynamicAction("H");
	//movimiento en vertical
    public static Action V = new DynamicAction("V");
	
    //Representamos el estado con un vector de pares (coche,posicion)
    /*Si es una letra != C, será un coche, si es una C será un camnión.
     * La posición será H o V e indicará si el coche está en horizontal o vertical
     * 
     * Además usamos una matriz 6x6 de booleanos donde iremos guardando casillas
     * ocupadas con 1, o libres con 0*/
    
    //***************************************************************************
    /*no sé cómo declarar el vector de pares para que no influya la dimensión,
     * sería usando la clase vector??????*/
    //He añadido la clase par, que define los pares
	
    boolean ocupadas [][] = new boolean [6][6];
    //definimos en qué casilla está la puerta
    private Par <Integer,Integer> puerta;
    
    public AtascoEstado()
    {
    	//quedaría poner el vector con los coches
    	
    	//no sé si hay manera de inicializar las casillas a 1
        for(int i=0;i<6;i++) {
        	for(int j =0; j<6; j++) {
        		ocupadas[i][j]=false;
        	}	
        }
        puerta = new Par<Integer, Integer>(3,6);
    }
	
    
    
}
