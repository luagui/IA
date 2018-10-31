package atasco;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

public class AtascoEstado {
	
	//movimiento en horizontal
	public static Action H = new DynamicAction("H");
	//movimiento en vertical
    public static Action V = new DynamicAction("V");
	
    //Representamos el estado con un vector de pares (coche,posicion)
    /*Si es una letra != C, ser� un coche, si es una C ser� un camni�n.
     * La posici�n ser� H o V e indicar� si el coche est� en horizontal o vertical
     * 
     * Adem�s usamos una matriz 6x6 de booleanos donde iremos guardando casillas
     * ocupadas con 1, o libres con 0*/
    
    //***************************************************************************
    /*no s� c�mo declarar el vector de pares para que no influya la dimensi�n,
     * ser�a usando la clase vector??????*/
    //He a�adido la clase par, que define los pares
	
    boolean ocupadas [][] = new boolean [6][6];
    //definimos en qu� casilla est� la puerta
    private Par <Integer,Integer> puerta;
    
    public AtascoEstado()
    {
    	//quedar�a poner el vector con los coches
    	
    	//no s� si hay manera de inicializar las casillas a 1
        for(int i=0;i<6;i++) {
        	for(int j =0; j<6; j++) {
        		ocupadas[i][j]=false;
        	}	
        }
        puerta = new Par<Integer, Integer>(3,6);
    }
	
    
    
}
