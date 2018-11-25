package genetic;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aima.core.environment.nqueens.NQueensGenAlgoUtil;
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
	}

	private static void myGeneticAlgorithmSearch() {
		System.out.println("Demo GeneticAlgorithm  -->");
		try {
			FitnessFunction<Integer> fitnessFunction = GeneticAlgoUtil.getFitnessFunction();
			GoalTest goalTest = GeneticAlgoUtil.getGoalTest();
			// Generate an initial population
			Set<Individual<Integer>> population = new HashSet<>();
			for (int i = 0; i < 4; i++) {
				population.add(GeneticAlgoUtil.generateRandomIndividual(arraySize));
			}	

				
			GeneticAlgorithm<Integer> ga = new GeneticAlgorithm<>(arraySize,
					GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), 0.15);
			
			GeneticAlgoProb gap = new GeneticAlgoProb(arraySize,
					GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), 0.15, 0.70);
			
			GeneticAlgorithm<Integer> ga2Children = new GeneticAlgoNewCruce(arraySize,
					GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), 0.15);
			
			GeneticAlgorithm<Integer> gaNoDest = new GeneticNoDestruct(arraySize,
					GeneticAlgoUtil.getFiniteAlphabetForBoardOfSize(arraySize), 0.15);

			// Run for a set amount of time
			Individual<Integer> bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);

			System.out.println("Max Time (1 second) Best Individual=\n"
					+ GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Board Size      = " + arraySize);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Iterations      = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);

			System.out.println("");
			System.out.println("Run till goal is achieved");
			System.out
					.println("Goal Test Best Individual=\n" + GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Board Size      = " + arraySize);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Iterations       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms."); 
			
			//Run with probability of not reproducing
			
			
			bestIndividual = gap.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);	
			System.out.println("");
			System.out.println("Run with probability of not reproducing");
			System.out
					.println("Goal Test Best Individual=\n" + GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Board Size      = " + arraySize);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + gap.getPopulationSize());
			System.out.println("Iterations       = " + gap.getIterations());
			System.out.println("Took            = " + gap.getTimeInMilliseconds() + "ms.");

			
			//Run with two children
			
			bestIndividual = ga2Children.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);	
			System.out.println("");
			System.out.println("Run with two children");
			System.out
					.println("Goal Test Best Individual=\n" + GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Board Size      = " + arraySize);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga2Children.getPopulationSize());
			System.out.println("Iterations       = " + ga2Children.getIterations());
			System.out.println("Took            = " + ga2Children.getTimeInMilliseconds() + "ms."); 
			
			//Run no destructive algorithm
			System.out.println("");
			System.out.println("Run no destructive algorithm");
			bestIndividual = gaNoDest.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);	
			
			System.out.println("Goal Test Best Individual=\n" + GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Board Size      = " + arraySize);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + gaNoDest.getPopulationSize());
			System.out.println("Iterations       = " + gaNoDest.getIterations());
			System.out.println("Took            = " + gaNoDest.getTimeInMilliseconds() + "ms.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
