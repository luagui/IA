package atasco;


import aima.core.agent.Action;
import aima.core.search.framework.*;
//Esto no sé por qué hay que añadirlo si se supone que la de arriba lo incluye
import aima.core.search.framework.problem.*;

import aima.core.search.uninformed.BreadthFirstSearch;
//import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;


import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class AtascoDemoBusquedasNoInformadas {
	 static AtascoEstado estadoInicial = new AtascoEstado();

	    public static void main(String [] args)
	    {
	        AtascoDemo();
	    }

	    private static void AtascoDemo()
	    {
	        breadthFirstDemo();
	       //depthLimitedFirstDemo();
	        //iterativeDeepeningDemo();

	    }

	    private static void  breadthFirstDemo()
	    {
	        System.out.println("\nAtascoBFSDemo--->");
	        try
	        {
	            Problem problem
	                    = new Problem(estadoInicial,AtascoFunctionFactory.getActionsFunction(),
	                    AtascoFunctionFactory.getResultFunction(),new AtascoGoalTest(),
	                    new AtascoStepCostFunction());
	            //aquí
	            BreadthFirstSearch search = new BreadthFirstSearch();
	            SearchAgent agent = new SearchAgent(problem,search);

	            printActions(agent.getActions());
	            printInstrumentation(agent.getInstrumentation());

	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private static void depthLimitedFirstDemo()
	    {
	        System.out.println("\nAtascoDLFSDEMO--->");
	        try
	        {
	            Problem problem
	                    = new Problem(estadoInicial,AtascoFunctionFactory.getActionsFunction(),
	                    AtascoFunctionFactory.getResultFunction(),new AtascoGoalTest(),
	                    new AtascoStepCostFunction());
	            //Aquí
	            DepthLimitedSearch search = new DepthLimitedSearch(10);
	            SearchAgent agent = new SearchAgent(problem,search);

	            printActions(agent.getActions());
	            printInstrumentation(agent.getInstrumentation());

	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

	    private static void iterativeDeepeningDemo()
	    {
	        System.out.println("\nAtascoIDDemo--->");
	        try
	        {
	            Problem problem
	                    = new Problem(estadoInicial,AtascoFunctionFactory.getActionsFunction(),
	                    AtascoFunctionFactory.getResultFunction(),new AtascoGoalTest(),
	                    new AtascoStepCostFunction());
	            // aquí
	            IterativeDeepeningSearch search = new IterativeDeepeningSearch();
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
