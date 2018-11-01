package atasco;

public class Vehiculo {
	private int fila;
	private int columna;
	private int tipo;
	private String orientacion; 
	
	// tipo == 0 -> Coche rojo
	// tipo == 1 -> Coche
	// tipo == 3 -> Camion
	
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
}
