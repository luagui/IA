package atasco;

import aima.core.search.framework.evalfunc.HeuristicFunction;
import atasco.AtascoEstado;

public class AtascoImprovedHeuristicFunction implements HeuristicFunction {

	@Override
	public double h(Object o) {
		double valorHeur = 0;
		AtascoEstado estado = (AtascoEstado) o;

		if (estado.getCocheRojo().getOrientacion().equals("H")) {// Coche rojo en horizontal
			if (estado.getColumnaPuerta() == 0) {// Tenemos la puerta a la izquierda
				int numPasos = estado.getCocheRojo().getColumna(); // casillas desde el coche a la puerta
				valorHeur = numPasos;
				for (int i = 0; i < numPasos; i++) {// Para cada una vemos si están ocupadas
					if (estado.getOcupadas()[estado.getCocheRojo().getFila()][i] != -1) {// casilla ocupada
						int numV = estado.getOcupadas()[estado.getCocheRojo().getFila()][i];
						valorHeur += comprobarObstaculosColumna(estado, numV);
					}
				}
			} else {// Tenemos la puerta a la derecha
				System.out.print("Tiene que entrar por aqui \n ");
				int numPasos = 5 - (estado.getCocheRojo().getColumna() + 1); // casillas desde el coche a la puerta
				System.out.print("Columna del coche rojo: " + estado.getCocheRojo().getColumna() + "\n");
				System.out.print("Numero de casillas a la puerta: " + numPasos + "\n");
				// el +1 es porque el coche acaba en estado.getCocheRojo().getColumna()+1
				valorHeur = numPasos;
				for (int i = estado.getCocheRojo().getColumna() + 2; i <= 5; i++) {// Para cada una vemos si están
																					// ocupadas
					if (estado.getOcupadas()[estado.getCocheRojo().getFila()][i] != -1) {// casilla ocupada
						System.out.print(
								"Casilla " + "(" + estado.getCocheRojo().getFila() + "," + (i) + ")" + " ocupada \n");
						int numV = estado.getOcupadas()[estado.getCocheRojo().getFila()][i];
						valorHeur += comprobarObstaculosColumna(estado, numV);
					}
				}
			}
		}

		else {// Coche rojo en vertical
			if (estado.getFilaPuerta() == 0) {// Tenemos la puerta arriba
				int numPasos = estado.getCocheRojo().getFila(); // casillas desde el coche a la puerta
				valorHeur = numPasos;
				for (int i = 0; i < numPasos; i++) {// Para cada una vemos si están ocupadas
					if (estado.getOcupadas()[i][estado.getCocheRojo().getColumna()] != -1) {// casilla ocupada
						int numV = estado.getOcupadas()[estado.getCocheRojo().getFila()][i];
						valorHeur += comprobarObstaculosFila(estado, numV);
					}
				}
			} else {// Tenemos la puerta abajo
				int numPasos = 5 - estado.getCocheRojo().getFila() + 1; // casillas desde el coche a la puerta
				// el +1 es porque el coche acaba en estado.getCocheRojo().getFila()+1
				valorHeur = numPasos;
				for (int i = estado.getCocheRojo().getFila() + 2; i <= 5; i++) {// Para cada una vemos si están ocupadas
					if (estado.getOcupadas()[i][estado.getCocheRojo().getColumna()] != -1) {// casilla ocupada
						int numV = estado.getOcupadas()[estado.getCocheRojo().getFila()][i];
						valorHeur += comprobarObstaculosFila(estado, numV);
					}
				}
			}

		}
		System.out.print("Valor heuristico: " + valorHeur + "\n ");
		return valorHeur;
	}

	int comprobarObstaculosColumna(AtascoEstado estado, int numV) {
		int valorHeurArriba = Integer.MAX_VALUE;
		int valorHeurAbajo = Integer.MAX_VALUE;
		int vehiculoFila = estado.getVehiculo(numV).getFila();
		int vehiculoColumna = estado.getVehiculo(numV).getColumna();
		
		
		if (estado.getVehiculo(numV).getTipo() == 1) {
			// es un coche el que bloquea
			if (vehiculoFila == estado.getCocheRojo().getFila()) { // tiene la parte de arriba en la fila de la puerta
						if (vehiculoFila - 2 >= 0) { //Se podria hacer hueco hacia arriba
							valorHeurArriba = 2;
							for (int i = 1; i <= 2; i++) { //Comprobamos los dos huecos superiores a ver si están ocupados
								if (estado.getOcupadas()[vehiculoFila - i][vehiculoColumna] != -1) { //Casilla Ocupada
									valorHeurArriba += 1;
								}
							}
						}
						if ((vehiculoFila + 2) < 6) { //Se podria hacer hueco hacia abajo
							valorHeurAbajo = 1;
							if (estado.getOcupadas()[vehiculoFila + 2][vehiculoColumna] != -1) { //Casilla Ocupada
								valorHeurAbajo += 1;
							}
						}
			
																								
			} else { //Tiene la parte de abajo en la fila del coche rojo
				if (vehiculoFila - 1 >= 0) { //Se podria hacer hueco hacia arriba
					valorHeurArriba = 1;
					if (estado.getOcupadas()[vehiculoFila - 1][vehiculoColumna] != -1) { //Casilla Ocupada
						valorHeurArriba += 1;
					}
				}
				if ((vehiculoFila + 3) < 6) { //Se podria hacer hueco hacia abajo
					valorHeurAbajo = 2;
					for (int i = 2; i <= 3; i++) { //Comprobamos los dos huecos inferiores a ver si están ocupados
						if (estado.getOcupadas()[vehiculoFila + i][vehiculoColumna] != -1) { //Casilla Ocupada
							valorHeurAbajo += 1;
						}
					}
				}
			}
		
		
		} else {// Es un camión el que bloquea
			if (vehiculoFila == estado.getCocheRojo().getFila()) { // Tiene la parte de arriba en la fila de la puerta
				if (vehiculoFila - 3 >= 0) { //Se podria hacer hueco hacia arriba
					valorHeurArriba = 3;
					for (int i = 1; i <= 3; i++) { //Comprobamos los dos huecos superiores a ver si están ocupados
						if (estado.getOcupadas()[vehiculoFila - i][vehiculoColumna] != -1) { //Casilla Ocupada
							valorHeurArriba += 1;
						}
					}
				}
				if ((vehiculoFila + 3) < 6) { //Se podria hacer hueco hacia abajo
					valorHeurAbajo = 1;
					if (estado.getOcupadas()[vehiculoFila + 3][vehiculoColumna] != -1) { //Casilla Ocupada
						valorHeurAbajo += 1;
					}
				}
				
																					
				
			} else if (Math.abs(vehiculoFila - estado.getCocheRojo().getFila()) == 1) { //En medio 
		
					if (vehiculoFila - 2 >= 0) { //Se podria hacer hueco hacia arriba
						valorHeurArriba = 2;
						for (int i = 1; i <= 2; i++) { //Comprobamos los dos huecos superiores a ver si están ocupados
							if (estado.getOcupadas()[vehiculoFila - i][vehiculoColumna] != -1) { //Casilla Ocupada
								valorHeurArriba += 1;
							}
						}
					}
					if ((vehiculoFila + 4) < 6) { //Se podria hacer hueco hacia abajo
						valorHeurAbajo = 2;
						for (int i = 3; i <= 4; i++) { //Comprobamos los dos huecos inferiores a ver si están ocupados
							if (estado.getOcupadas()[vehiculoFila+ i][vehiculoColumna] != -1) { //Casilla Ocupada
								valorHeurAbajo += 1;
							}
						}	
					}
				
			} else { // Tiene la parte de abajo en la puerta
				if (vehiculoFila - 1 >= 0) { //Se podria hacer hueco hacia arriba
					valorHeurArriba = 1;
					if (estado.getOcupadas()[vehiculoFila - 1][vehiculoColumna] != -1) { //Casilla Ocupada
						valorHeurArriba += 1;
					}
				}
				if ((vehiculoFila + 5) < 6) { //Se podria hacer hueco hacia abajo
					valorHeurAbajo = 3;
					for (int i = 3; i <= 5; i++) { //Comprobamos los dos huecos inferiores a ver si están ocupados
						if (estado.getOcupadas()[vehiculoFila + i][vehiculoColumna] != -1) { //Casilla Ocupada
							valorHeurAbajo += 1;
						}
					}
				}
			}
		}
		return Math.min(valorHeurArriba, valorHeurAbajo);
	}
	
	int comprobarObstaculosFila(AtascoEstado estado, int numV) {
		int valorHeurIzquierda = Integer.MAX_VALUE;
		int valorHeurDerecha = Integer.MAX_VALUE;
		int vehiculoFila = estado.getVehiculo(numV).getFila();
		int vehiculoColumna = estado.getVehiculo(numV).getColumna();
		
		
		if (estado.getVehiculo(numV).getTipo() == 1) {
			// es un coche el que bloquea
			if (vehiculoColumna == estado.getCocheRojo().getColumna()) { // Tiene la parte de la izquierda en la columna 
																		 // del coche rojo
						if (vehiculoColumna - 2 >= 0) { //Se podria hacer hueco hacia izquierda
							valorHeurIzquierda = 2;
							for (int i = 1; i <= 2; i++) { //Comprobamos los dos huecos izquierdos a ver si están ocupados
								if (estado.getOcupadas()[vehiculoFila][vehiculoColumna -  i] != -1) { //Casilla Ocupada
									valorHeurIzquierda += 1;
								}
							}
						}
						if ((vehiculoColumna + 2) < 6) { //Se podria hacer hueco hacia derecha
							valorHeurDerecha = 1;
							if (estado.getOcupadas()[vehiculoFila][vehiculoColumna + 2] != -1) { //Casilla Ocupada
								valorHeurDerecha += 1;
							}
						}
			
																								
			} else { //Tiene la parte de la derecha en la columna del coche rojo
				if (vehiculoColumna - 1 >= 0) { //Se podria hacer hueco hacia izquierda
					valorHeurIzquierda = 1;
					if (estado.getOcupadas()[vehiculoFila][vehiculoColumna - 1] != -1) { //Casilla Ocupada
						valorHeurIzquierda += 1;
					}
				}
				if ((vehiculoColumna + 3) < 6) { //Se podria hacer hueco hacia derecha
					valorHeurDerecha = 2;
					for (int i = 2; i <= 3; i++) { //Comprobamos los dos huecos derechos a ver si están ocupados
						if (estado.getOcupadas()[vehiculoFila][vehiculoColumna + i ] != -1) { //Casilla Ocupada
							valorHeurDerecha += 1;
						}
					}
				}
			}
		
		
		} else {// Es un camión el que bloquea
			if (vehiculoColumna == estado.getCocheRojo().getColumna()) { // Tiene la parte de izquierda en la columna de la puerta
				if (vehiculoColumna - 3 >= 0) { //Se podria hacer hueco hacia izquierda
					valorHeurIzquierda = 3;
					for (int i = 1; i <= 3; i++) { //Comprobamos los dos huecos superiores a ver si están ocupados
						if (estado.getOcupadas()[vehiculoFila][vehiculoColumna - i] != -1) { //Casilla Ocupada
							valorHeurIzquierda += 1;
						}
					}
				}
				if ((vehiculoColumna + 3) < 6) { //Se podria hacer hueco hacia abajo
					valorHeurDerecha = 1;
					if (estado.getOcupadas()[vehiculoFila][vehiculoColumna + 3] != -1) { //Casilla Ocupada
						valorHeurDerecha += 1;
					}
				}
				
																					
				
			} else if (Math.abs(vehiculoColumna - estado.getCocheRojo().getColumna()) == 1) { //En medio 
		
					if (vehiculoColumna - 2 >= 0) { //Se podria hacer hueco hacia izquierda
						valorHeurIzquierda = 2;
						for (int i = 1; i <= 2; i++) { //Comprobamos los dos huecos superiores a ver si están ocupados
							if (estado.getOcupadas()[vehiculoFila][vehiculoColumna - i] != -1) { //Casilla Ocupada
								valorHeurIzquierda += 1;
							}
						}
					}
					if ((vehiculoColumna + 4) < 6) { //Se podria hacer hueco hacia derecha
						valorHeurDerecha = 2;
						for (int i = 3; i <= 4; i++) { //Comprobamos los dos huecos derechos a ver si están ocupados
							if (estado.getOcupadas()[vehiculoFila][vehiculoColumna + i] != -1) { //Casilla Ocupada
								valorHeurDerecha += 1;
							}
						}	
					}
				
			} else { // Tiene la parte derecha en la puerta
				if (vehiculoColumna - 1 >= 0) { //Se podria hacer hueco hacia arriba
					valorHeurIzquierda = 1;
					if (estado.getOcupadas()[vehiculoFila][vehiculoColumna - 1] != -1) { //Casilla Ocupada
						valorHeurIzquierda += 1;
					}
				}
				if ((vehiculoColumna + 5) < 6) { //Se podria hacer hueco hacia abajo
					valorHeurDerecha = 3;
					for (int i = 3; i <= 5; i++) { //Comprobamos los dos huecos superiores a ver si están ocupados
						if (estado.getOcupadas()[vehiculoFila][vehiculoColumna + i] != -1) { //Casilla Ocupada
							valorHeurDerecha += 1;
						}
					}
				}
			}
		}
		return Math.min(valorHeurIzquierda, valorHeurDerecha);
	}
	
}
