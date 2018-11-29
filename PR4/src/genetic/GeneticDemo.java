package genetic;


import java.util.HashSet;
import java.util.Set;

import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

/*Por si necesitamos debugear un individuo en concreto
 * 
 * 				List<Integer> l = Arrays.asList(11,3,11,3,8,4,5,2,3,2,6);
				Individual<Integer> dbug = new Individual<Integer>(l);
				GeneticAlgoUtil.printIndividual(dbug);
				System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(dbug));
				System.out.println("Fitness         = " + fitnessFunction.apply(dbug));
 */

public class GeneticDemo {

	private static final int arraySize = 11;

	public static void main(String[] args) {

		myGeneticAlgorithmSearch();
		//bancoDePruebas();
	}

	private static void myGeneticAlgorithmSearch() {
		System.out.println("Demo GeneticAlgorithm  -->");
		try {
			FitnessFunction<Integer> fitnessFunction = GeneticAlgoUtil.getFitnessFunction();
			GoalTest goalTest = GeneticAlgoUtil.getGoalTest();
			// Generate an initial population
			Set<Individual<Integer>> population = new HashSet<>();
			for (int i = 0; i < 6; i++) {
				population.add(GeneticAlgoUtil.generateRandomIndividual(arraySize));
			}	

				
			GeneticAlgorithm<Integer> ga = new GeneticAlgorithm<>(arraySize,
					GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), 0.5);
			
			GeneticAlgoProb gap = new GeneticAlgoProb(arraySize,
					GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), 0.5, 0.80);
			
			GeneticAlgorithm<Integer> ga2Children = new GeneticAlgoNewCruce(arraySize,
					GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), 0.5);
			
			GeneticAlgorithm<Integer> gaNoDest = new GeneticNoDestruct(arraySize,
					GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), 0.5);
			
			
			Individual<Integer> bestIndividual;
			
			
			// Run for a set amount of time
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);
	
			System.out.println("Run for a set amount of time");
			System.out.println("Goal            = " + GeneticAlgoUtil.getGoal());
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Value           = " + GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Iterations      = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			System.out.println("");
			
			
			
			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);	
			System.out.println("Run till goal is achieved");
			System.out.println("Run with probability of not reproducing");
			System.out.println("Goal            = " + GeneticAlgoUtil.getGoal());
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Value           = " + GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Iterations      = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			System.out.println("");
			
			
			
			
			//Run with probability of not reproducing
			
			
			bestIndividual = gap.geneticAlgorithm(population, fitnessFunction, goalTest, 10000L);	
			System.out.println("");
			System.out.println("Run with probability of not reproducing");
			System.out.println("Goal            = " + GeneticAlgoUtil.getGoal());
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Value           = " + GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + gap.getPopulationSize());
			System.out.println("Iterations      = " + gap.getIterations());
			System.out.println("Took            = " + gap.getTimeInMilliseconds() + "ms.");
			System.out.println("");

			
			
			
			
			//Run with two children
			
			bestIndividual = ga2Children.geneticAlgorithm(population, fitnessFunction, goalTest, 10000L);	
			System.out.println("");
			System.out.println("2ChildrenVersion");
			System.out.println("Goal            = " + GeneticAlgoUtil.getGoal());
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Value           = " + GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga2Children.getPopulationSize());
			System.out.println("Iterations      = " + ga2Children.getIterations());
			System.out.println("Took            = " + ga2Children.getTimeInMilliseconds() + "ms.");
			System.out.println("");
			
			
			
			
			//Run no destructive algorithm
			
			
			bestIndividual = gaNoDest.geneticAlgorithm(population, fitnessFunction, goalTest, 10000L);	
			System.out.println("Run no destructive algorithm");
			System.out.println("Goal            = " + GeneticAlgoUtil.getGoal());
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Value           = " + GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + gaNoDest.getPopulationSize());
			System.out.println("Iterations      = " + gaNoDest.getIterations());
			System.out.println("Took            = " + gaNoDest.getTimeInMilliseconds() + "ms.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Funcion utilizada para sacar estádisticas de los algoritmos con diferentes valores
	 * en sus parámetros
	 */
	@SuppressWarnings("unused")
	private static void bancoDePruebas() {
		int población = 6;
		int ejecuciones = 100;
		long maxTimeMiliseconds = 5000L;
		double probMutacion = 1;
		double probCruce = 0.3;
		long iteracionesTotales = 0;
		long tiempoTotal = 0;
		long aciertosTotales = 0;
		double porcentaje = 0.0;
		
		
		FitnessFunction<Integer> fitnessFunction = GeneticAlgoUtil.getFitnessFunction();
		GoalTest goalTest = GeneticAlgoUtil.getGoalTest();
		
		// Descomentar para utiliza
		
		
		GeneticAlgorithm<Integer> ga = new GeneticAlgorithm<>(arraySize,
				GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), probMutacion);
	 
		
		/*	
		GeneticAlgorithm<Integer> ga = new GeneticAlgoProb(arraySize,
				GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), probMutacion,probCruce);				
		*/
		
		/*
		GeneticAlgorithm<Integer> ga = new GeneticAlgoNewCruce(arraySize,
				GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), probMutacion);
		*/
		
		/*	 
		GeneticAlgorithm<Integer> ga = new GeneticNoDestruct(arraySize,
				GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), probMutacion);
		*/
		
		
		Individual<Integer> bestIndividual;
		for(int i = 0; i < ejecuciones; i++) {
			// Generate an initial population
			Set<Individual<Integer>> population = new HashSet<>();
			for (int j = 0; j < población; j++) {
				population.add(GeneticAlgoUtil.generateRandomIndividual(arraySize));
			}
			
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, maxTimeMiliseconds);
			iteracionesTotales +=  ga.getIterations();
			tiempoTotal += ga.getTimeInMilliseconds();
			if (goalTest.isGoalState(bestIndividual)) {
				aciertosTotales++;
			}
		}
		porcentaje = (double)aciertosTotales / (double)ejecuciones;
		
		System.out.println("Maximo tiempo ejecucion   = " + maxTimeMiliseconds + "ms.");
		System.out.println("Numero de ejecuciones     = " + ejecuciones);
		System.out.println("Poblacion                 = " + población);
	//	System.out.println("Probabilidad de cruce     = " + probCruce);
		System.out.println("Probabilidad de mutacion  = " + probMutacion);
		System.out.println("Iteraciones Medias        = " + (iteracionesTotales / ejecuciones));
		System.out.println("Tiempo Medio              = " + (tiempoTotal / ejecuciones)+ "ms.");
		System.out.println("Porcentaje Acierto        = " + porcentaje*100 + " %");
		}
}

