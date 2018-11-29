package genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;




import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;

public class GeneticAlgoUtil {
	private static double objetivo = 431.0;
	
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
		
		for (int i = 0; i <= size; i++) { //Le pasamos arraySize = 11
			fab.add(i +  1);
		}

		return fab;
	}
	
	//Clase para la función fitness
	public static class GeneticFitnessFunction implements FitnessFunction<Integer> {

		public double apply(Individual<Integer> individual) {
			double value = auxOperation(individual);
			if (objetivo == value) {
				return Double.MAX_VALUE;
			}
			return 1/Math.abs(objetivo - value);
		}

		
	}

	public static class GeneticAlgoGoalTest  implements GoalTest {	
		@SuppressWarnings("unchecked")
		public boolean isGoalState(Object arg0) {
			return objetivo == auxOperation((Individual<Integer>) arg0);
		}
	
	}
	
	//nos devuelve el valor de hacer todas las operaciones del individuo
	public static double auxOperation (Individual<Integer> individual) {
		//operation = primer número del individuo
		double operation = individual.getRepresentation().get(0);
		if (operation == 11.0) {
			operation = 25.0;
		} else if (operation == 12.0) {
			operation = 50.0;
		}
		double num;
		//recorremos los siguientes elementos del individuo
		for(int i =1; i < individual.length() -1; i += 2) {
		
		//num es el número que sigue a la operación i
		num = (double) individual.getRepresentation().get(i+1);
		if (num == 11.0) {
			num = 25.0;
		} else if (num == 12.0) {
			num = 50.0;
		}
			if ((individual.getRepresentation().get(i) % 4) == 0) {//division
				operation = operation / num;
			}
			else if ((individual.getRepresentation().get(i) % 4) == 1) {//suma
				operation = operation + num;
			}
			else if ((individual.getRepresentation().get(i) % 4) == 2) {//resta
				operation = operation - num;
			}
			else {//multiplicación
				operation = operation * num;
			}
		}
		return operation;
	}
	public static String printIndividual(Individual<Integer> individual){
		String result = new String();
		int num = individual.getRepresentation().get(0);
		
		if (num == 11.0) {
			result += "25";
		} else if (num == 12.0) {
			result += "50";
		} else {
			result += String.valueOf(num);
		}
		
		for(int i =1; i < individual.length() -1; i += 2) {
		
			if ((individual.getRepresentation().get(i) % 4) == 0) {//division
				result +=" /";
			}
			else if ((individual.getRepresentation().get(i) % 4) == 1) {//suma
				result +=" +";
			}
			else if ((individual.getRepresentation().get(i) % 4) == 2) {//resta
				result +=" -";
			}
			else {//multiplicación
				result +=" *";
			}
			
			num = individual.getRepresentation().get(i+1);
			if (num == 11.0) {
				result += " 25";
			} else if (num == 12.0) {
				result += " 50";
			} else {
				result = result + " " + String.valueOf(num);
			}
		}
		return result;
	}
	
	public static double getGoal(){
		return GeneticAlgoUtil.objetivo;
	}
}
