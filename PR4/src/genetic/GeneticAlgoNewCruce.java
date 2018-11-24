package genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

public class GeneticAlgoNewCruce extends GeneticAlgorithm<Integer> {

	
	public GeneticAlgoNewCruce(int individualLength, Collection<Integer> finiteAlphabet, double mutationProbability) {
		super(individualLength, finiteAlphabet, mutationProbability);
		
	}
	
	protected List<Individual<Integer>> nextGeneration(List<Individual<Integer>> population,FitnessFunction<Integer> fitnessFn) {

		List<Individual<Integer>> newPopulation = new ArrayList<Individual<Integer>>(population.size());
		
		//el cambio es population.size()/2 en la condición del bucle
		for (int i = 0; i < population.size()/2; i++) {
			Individual<Integer> x = randomSelection(population, fitnessFn);
			Individual<Integer> y = randomSelection(population, fitnessFn);
			Individual<Integer> child1, child2;
			//hacemos dos hijos:
			//child1 -> primera parte de x - segunda parte de y
			//child2 -> primera parte de y - segunda parte de x
			//donde cada parte la determina el valor random que se genera el la función reproduce
			child1 = reproduceAux(x,y);
			child2 = reproduceAux(y,x);
			newPopulation.add(child1);
			newPopulation.add(child2);
		}
		return newPopulation;
	}

	
	protected Individual<Integer> reproduceAux(Individual<Integer> x, Individual<Integer> y){
		Individual<Integer> child;

			child = reproduce(x, y);
			if (random.nextDouble() <= mutationProbability) {
				child = mutate(child);
			}
		
		return child;
	}
	
}
