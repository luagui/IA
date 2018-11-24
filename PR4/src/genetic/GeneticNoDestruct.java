package genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

public class GeneticNoDestruct extends GeneticAlgorithm<Integer> {

	public GeneticNoDestruct(int individualLength, Collection<Integer> finiteAlphabet, double mutationProbability) {
		super(individualLength, finiteAlphabet, mutationProbability);
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
			if ((fitnessFn.apply(child) < fitnessFn.apply(x)) || (fitnessFn.apply(child) < fitnessFn.apply(y))) {
				child = x;
			}
			newPopulation.add(child);
		}
		return newPopulation;
	}
	
}
