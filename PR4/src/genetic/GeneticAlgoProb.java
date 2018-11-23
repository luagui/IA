package genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

public class GeneticAlgoProb extends GeneticAlgorithm<Integer> {
	private double crosProbability;
	
	public GeneticAlgoProb(int individualLength, Collection<Integer> finiteAlphabet, double mutationProbability, double crosProbability) {
		super(individualLength, finiteAlphabet, mutationProbability);
		this.crosProbability = crosProbability;
	}
	
	protected List<Individual<Integer>> nextGeneration(List<Individual<Integer>> population,FitnessFunction<Integer> fitnessFn) {

		List<Individual<Integer>> newPopulation = new ArrayList<Individual<Integer>>(population.size());
		
		for (int i = 0; i < population.size(); i++) {
			Individual<Integer> x = randomSelection(population, fitnessFn);
			Individual<Integer> y = randomSelection(population, fitnessFn);
			Individual<Integer> child;
			if (random.nextDouble() <= crosProbability) {
				child = reproduce(x, y);
				if (random.nextDouble() <= mutationProbability) {
					child = mutate(child);
				}
			} else { //No se cruza y devolvemos el padre x
				child = x;
			}
			newPopulation.add(child);
		}
		return newPopulation;
	}

}
