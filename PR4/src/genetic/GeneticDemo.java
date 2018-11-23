package genetic;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import aima.core.environment.nqueens.NQueensGenAlgoUtil;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

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
			for (int i = 0; i < 3; i++) {
				population.add(GeneticAlgoUtil.generateRandomIndividual(arraySize));
			}

			GeneticAlgorithm<Integer> ga = new GeneticAlgorithm<>(arraySize,
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
			System.out
					.println("Goal Test Best Individual=\n" + GeneticAlgoUtil.auxOperation(bestIndividual));
			System.out.println("Board Size      = " + arraySize);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Best Individual = " + GeneticAlgoUtil.printIndividual(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
