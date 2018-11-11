package atasco;

import aima.core.agent.Action;
import aima.core.search.framework.*;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import atasco.AtascoEstado;
import atasco.AtascoFunctionFactory;
import atasco.AtascoGoalTest;
import atasco.AtascoHeuristicFunction;
import atasco.AtascoBloqHeuristicFunction;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class AtascoDemoBusquedasInformadas {
	 public static void main(String [] args)
	    {
		 	//movesHeuristic();
	       //bloqueandoHeuristic();
	       //atascoHeuristic();
	       //movesHeuristicWithTreeSearch();
	      // atascoHeuristicWithTreeSearch();
	       greedyBestFirstSearchAtasco();
	       //greedyBestFirstSearchmoves();

	    }


	    private static void movesHeuristic()
	    {
	        System.out.println("\n Demo Atasco Astar with GraphSearch(Moves Heuristic) -->");
	        try
	        {
	            Problem problem = new Problem(new AtascoEstado(),
	                    AtascoFunctionFactory.getActionsFunction(),
	                    AtascoFunctionFactory.getResultFunction(),
	                    new AtascoGoalTest());
	            AStarSearch search = new AStarSearch(new GraphSearch(),new AtascoBloqHeuristicFunction());
	            SearchAgent agent = new SearchAgent(problem,search);

	            printActions(agent.getActions());
	            printInstrumentation(agent.getInstrumentation());
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }


	    private static void movesHeuristicWithTreeSearch()
	    {
	        System.out.println("\n Demo Atasco Astar with  TreeSearch(Moves Heuristic) -->");
	        try
	        {
	            Problem problem = new Problem(new AtascoEstado(),
	                    AtascoFunctionFactory.getActionsFunction(),
	                    AtascoFunctionFactory.getResultFunction(),
	                    new AtascoGoalTest());
	            AStarSearch search = new AStarSearch(new TreeSearch(),new AtascoBloqHeuristicFunction());
	            SearchAgent agent = new SearchAgent(problem,search);

	            printActions(agent.getActions());
	            printInstrumentation(agent.getInstrumentation());
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

	    private static void atascoHeuristicWithTreeSearch()
	    {
	        System.out.println("\n Demo Atasco Astar TreeSearch(Atasco Heuristic) -->");
	        try
	        {
	            Problem problem = new Problem(new AtascoEstado(),
	                    AtascoFunctionFactory.getActionsFunction(),
	                    AtascoFunctionFactory.getResultFunction(),
	                    new AtascoGoalTest());
	            AStarSearch search = new AStarSearch(new TreeSearch(),new AtascoHeuristicFunction());
	            SearchAgent agent = new SearchAgent(problem,search);

	            printActions(agent.getActions());
	            printInstrumentation(agent.getInstrumentation());
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

	    private static void atascoHeuristic()
	    {
	        System.out.println("\n Demo Atasco Astar GraphSearch(Atasco Heuristic) -->");
	        try
	        {
	            Problem problem = new Problem(new AtascoEstado(),
	                    AtascoFunctionFactory.getActionsFunction(),
	                    AtascoFunctionFactory.getResultFunction(),
	                    new AtascoGoalTest());
	            AStarSearch search = new AStarSearch(new GraphSearch(),new AtascoHeuristicFunction());
	            SearchAgent agent = new SearchAgent(problem,search);

	            printActions(agent.getActions());
	            printInstrumentation(agent.getInstrumentation());
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

	    private static void greedyBestFirstSearchAtasco()
	    {
	        System.out.println("\n Demo Atasco greedy best first search(Atasco Heuristic) -->");
	        try
	        {
	            Problem problem = new Problem(new AtascoEstado(),
	                    AtascoFunctionFactory.getActionsFunction(),
	                    AtascoFunctionFactory.getResultFunction(),
	                    new AtascoGoalTest());
	            GreedyBestFirstSearch search = new GreedyBestFirstSearch(new GraphSearch(),new AtascoHeuristicFunction());
	            SearchAgent agent = new SearchAgent(problem,search);

	            printActions(agent.getActions());
	            printInstrumentation(agent.getInstrumentation());
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }

	    }

	    private static void greedyBestFirstSearchmoves()
	    {
	        System.out.println("\n Demo Atasco greedy best first search(moves Heuristic) -->");
	        try
	        {
	            Problem problem = new Problem(new AtascoEstado(),
	                    AtascoFunctionFactory.getActionsFunction(),
	                    AtascoFunctionFactory.getResultFunction(),
	                    new AtascoGoalTest());
	            GreedyBestFirstSearch search = new GreedyBestFirstSearch(new GraphSearch(),new AtascoBloqHeuristicFunction());
	            SearchAgent agent = new SearchAgent(problem,search);

	            printActions(agent.getActions());
	            printInstrumentation(agent.getInstrumentation());
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }

	    }

	    private static void printInstrumentation(Properties properties) {
	        Iterator<Object> keys = properties.keySet().iterator();
	        while (keys.hasNext()) {
	            String key = (String) keys.next();
	            String property = properties.getProperty(key);
	            System.out.println(key + " : " + property);
	        }

	    }

	    private static void printActions(List<Action> actions) {
	        for (int i = 0; i < actions.size(); i++) {
	            String action = actions.get(i).toString();
	            System.out.println(action);
	        }
	    }



}
