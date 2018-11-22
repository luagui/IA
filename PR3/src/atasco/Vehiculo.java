package atasco;

public class Vehiculo {
	
	
	private int fila;
	private int columna;
	private String orientacion;
	private int tipo;

	/*
	 * fila y columna son la posici�n de m�s a la izquierda del veh�culo en caso de que este
	 * est� en horizontal o de m�s arriba en caso de que este est� en vertical
	 * orientacion = H -> veh�culo en horizontal
	 * orientacion = V -> veh�culo en vertical 
	 * tipo == 0 -> Coche rojo
	 * tipo == 1 -> Coche
	 * tipo == 3 -> Camion */

	
	public Vehiculo (int posicionFila, int posicionColumna, String orientacion, int tipo) {
		this.fila = posicionFila;
		this.columna = posicionColumna;
		this.orientacion = orientacion;
		this.tipo = tipo;
	}
	
	public int getFila(){
		return this.fila;
	}
	
	public int getColumna(){
		return this.columna;
	}
	
	public String getOrientacion(){
		return this.orientacion;
	}
	
	public int getTipo(){
		return this.tipo;
	}
	
	public void setFila(int nuevaFila){
		this.fila = nuevaFila;
	}
	
	public void setColumna(int nuevaColumna){
		this.columna = nuevaColumna;
	}
	
	 public boolean equals(Object o) {

	    	boolean eq = true;
	    	
	        if(this == o) return true;

	        if((o == null || (this.getClass() != o.getClass()))) return false;

	        Vehiculo otroVehiculo = (Vehiculo) o;
	        
	        eq = (this.columna == otroVehiculo.getColumna()) && (this.fila == otroVehiculo.getFila())
	        		&& (this.orientacion.equals(otroVehiculo.getOrientacion())) && (this.tipo == otroVehiculo.getTipo());
	        
	        return eq;
	   }
	 
	 public int hashCode() { //Utilizo primos que se supone que da menos colisiones en los hash
		 return 31*this.fila + 13*this.columna + 5*this.tipo + (this.orientacion.equals("V") ? 1 : 0);  
	 }
}
