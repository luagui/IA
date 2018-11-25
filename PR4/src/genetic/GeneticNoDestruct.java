package genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

public class GeneticNoDestruct extends GeneticAlgorithm<Integer> {

	public GeneticNoDestruct(int individualLength, Collection<Integer> finiteAlphabet, double mutationProbability) {
		super(individualLength, finiteAlphabet, mutationProbability);
	}
	
	public Individual<Integer> geneticAlgorithm(Collection<Individual<Integer>> initPopulation, FitnessFunction<Integer> fitnessFn,
			GoalTest goalTest, long maxTimeMilliseconds) {
		Individual<Integer> bestIndividual = null;

		// Create a local copy of the population to work with
		List<Individual<Integer>> population = new ArrayList<>(initPopulation);
		// Validate the population and setup the instrumentation
		validatePopulation(population);
		updateMetrics(population, 0, 0L);

		long startTime = System.currentTimeMillis();

		// repeat
		int itCount = 0;
		do {
			population = nextGeneration(population, fitnessFn);
			bestIndividual = retrieveBestIndividual(population, fitnessFn);

			updateMetrics(population, ++itCount, System.currentTimeMillis() - startTime);

			// until some individual is fit enough, or enough time has elapsed
			if (maxTimeMilliseconds > 0L && (System.currentTimeMillis() - startTime) > maxTimeMilliseconds)
				break;
			if (comprobarPoblacionHomogenea(population)) {
				System.out.println("La población se ha homogenealizado. Se para la ejecución");
				bestIndividual = population.get(0);
				break;
			}
		} while (!goalTest.isGoalState(bestIndividual));

		// return the best individual in population, according to FITNESS-FN
		return bestIndividual;
	}
	
	protected List<Individual<Integer>> nextGeneration(List<Individual<Integer>> population,FitnessFunction<Integer> fitnessFn) {

		List<Individual<Integer>> newPopulation = new ArrayList<Individual<Integer>>(population.size());
		
		for (int i = 0; i < population.size(); i++) {
			Individual<Integer> x = randomSelection(population, fitnessFn);
			Individual<Integer> y = randomSelection(population, fitnessFn);
			Individual<Integer> child;

			child = reproduce(x, y);
			if (random.nextDouble() <= mutationProbability) {
				child = mutate(child);
			}
			//el hijo es "peor" que los padres y no pasa a la siguiente generación
			if ((fitnessFn.apply(child) < fitnessFn.apply(x))) {
				child = x;
			} else if (fitnessFn.apply(child) < fitnessFn.apply(y)) {
				child = y;
			}
			
			
			newPopulation.add(child);
		}
		comprobarPoblacionHomogenea(newPopulation);
		return newPopulation;
	}
	
	public boolean comprobarPoblacionHomogenea(List<Individual<Integer>> population) {
		boolean homogeneo = true;
		Individual<Integer> a = population.get(0);
		for (int i = 1; i < population.size() && homogeneo; i++) {
			homogeneo = a.equals(population.get(i));
			a = population.get(i);
		}
		return homogeneo;
	}
	
}
