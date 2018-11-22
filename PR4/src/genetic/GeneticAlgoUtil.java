package genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;
import aima.core.util.datastructure.XYLocation;

public class GeneticAlgoUtil {

	public static FitnessFunction<Integer> getFitnessFunction() {
		return new GeneticFitnessFunction();
	}
	
	public static GoalTest getGoalTest() {
		return new GeneticAlgoGoalTest();
	}
	

	public static Individual<Integer> generateRandomIndividual(int arraySize) {
		List<Integer> individualRepresentation = new ArrayList<>();
		for (int i = 0; i < arraySize; i++) { 
			//Hacemos que los valores sean un numero random entre el 1 y el 12
			//arraySize será 11 (6 números y 5 operaciones)
			individualRepresentation.add( (int) (Math.random() * 12)+1);
		}
		return new Individual<>(individualRepresentation);
	}

	public static Collection<Integer> getFiniteAlphabetForBoardOfSize(int size) {
		Collection<Integer> fab = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			fab.add(i);
		}

		return fab;
	}
	
	//Clase para la función fitness
	public static class GeneticFitnessFunction implements FitnessFunction<Integer> {

		public double apply(Individual<Integer> individual) {
			double fitness = individual.getRepresentation().get(0);
			int num;
		for(int i =1; i < arraySize-1; i += 2) {
			//hay que sacar el array size de algún sitio
			//o suponer que es 11 siempre
			
			//num es el número que sigue a la operación i
			num = individual.getRepresentation().get(i+1);
			if(i % 2 == 1) { //hay operación
				if ((individual.getRepresentation().get(i) % 4) == 0) {//division
					fitness = fitness / num;
				}
				else if ((individual.getRepresentation().get(i) % 4) == 1) {//suma
					fitness = fitness + num;
				}
				else if ((individual.getRepresentation().get(i) % 4) == 2) {//resta
					fitness = fitness - num;
				}
				else {//multiplicación
					fitness = fitness * num;
				}
			}
		}

			return Math.abs(RESULTADO - fitness);
		}

		
	}

	public static class GeneticAlgoGoalTest implements GoalTest {
		
		@Override
		public boolean isGoalState(Object state) {
			Individual<Integer> individual = (Individual<Integer>) state; 
			//no sé si esto resultaría
			return false;
		}
	}
	
}
