package atasco;

import aima.core.search.framework.evalfunc.HeuristicFunction;
import atasco.AtascoEstado;

public class AtascoBloqHeuristicFunction implements HeuristicFunction {

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
						valorHeur += daHeuristicaEnHoriz(estado, numV);
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
						valorHeur += daHeuristicaEnHoriz(estado, numV);
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
						valorHeur += daHeuristicaEnVert(estado, numV);
					}
				}
			} else {// Tenemos la puerta abajo
				int numPasos = 5 - estado.getCocheRojo().getFila() + 1; // casillas desde el coche a la puerta
				// el +1 es porque el coche acaba en estado.getCocheRojo().getFila()+1
				valorHeur = numPasos;
				for (int i = estado.getCocheRojo().getFila() + 2; i <= 5; i++) {// Para cada una vemos si están ocupadas
					if (estado.getOcupadas()[i][estado.getCocheRojo().getColumna()] != -1) {// casilla ocupada
						int numV = estado.getOcupadas()[estado.getCocheRojo().getFila()][i];
						valorHeur += daHeuristicaEnVert(estado, numV);
					}
				}
			}

		}
		System.out.print("Valor heuristico: " + valorHeur + "\n ");
		return valorHeur;
	}

	int daHeuristicaEnHoriz(AtascoEstado estado, int numV) {
		int valorHeur = 0;
		if (estado.getVehiculo(numV).getTipo() == 1) {
			// es un coche el que bloquea
			if (estado.getVehiculo(numV).getFila() == estado.getCocheRojo().getFila()) { // tiene la parte de arriba en
																							// la fila de la puerta
				if (estado.movimientoBValido(numV)) {// se puede mover abajo
					valorHeur += 1;
				} else
					valorHeur += 2;
			} else {// Tiene la parte de abajo en la fila de la puerta
				if (estado.movimientoAValido(numV)) {// se puede mover arriba
					valorHeur += 1;
				} else
					valorHeur += 2;
			}
		} else {// Es un camión el que bloquea
			if (estado.getVehiculo(numV).getFila() == estado.getCocheRojo().getFila()) { // tiene la parte de arriba en
																							// la fila de la puerta
				if (estado.movimientoBValido(numV)) {// se puede mover abajo
					valorHeur += 1;
				} else
					valorHeur += 3;
			} else if (Math.abs(estado.getVehiculo(numV).getFila() - estado.getCocheRojo().getFila()) == 1) {
				// Está en el medio de la puerta
				valorHeur += 2;
			} else { // Tiene la parte de abajo en la puerta
				if (estado.movimientoAValido(numV)) {// se puede mover arriba
					valorHeur += 1;
				} else
					valorHeur += 3;
			}
		}
		return valorHeur;
	}
	
	int daHeuristicaEnVert(AtascoEstado estado, int numV) {
		int valorHeur = 0;
		if (estado.getVehiculo(numV).getTipo() == 1) {
			// es un coche el que bloquea
			if (estado.getVehiculo(numV).getColumna() == estado.getCocheRojo().getColumna()) { // tiene la parte de arriba en
																							// la fila de la puerta
				if (estado.movimientoAValido(numV)) {// se puede mover a la derecha
					valorHeur += 1;
				} else
					valorHeur += 2;
			} else {// Tiene la parte de "atras" en la fila de la puerta
				if (estado.movimientoBValido(numV)) {// se puede mover a la iz
					valorHeur += 1;
				} else
					valorHeur += 2;
			}
		} else {// Es un camión el que bloquea
			if (estado.getVehiculo(numV).getColumna() == estado.getCocheRojo().getColumna()) { // tiene la parte de arriba en
																							// la col de la puerta
				if (estado.movimientoAValido(numV)) {// se puede mover a la derecha
					valorHeur += 1;
				} else
					valorHeur += 3;
			} else if (Math.abs(estado.getVehiculo(numV).getFila() - estado.getCocheRojo().getFila()) == 1) {
				// Está en el medio de la puerta
				valorHeur += 2;
			} else { // Tiene la parte de abajo en la puerta
				if (estado.movimientoBValido(numV)) {// se puede mover a la iz
					valorHeur += 1;
				} else
					valorHeur += 3;
			}
		}
		return valorHeur;
	}
	
}
