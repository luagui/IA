package atasco;


import aima.core.search.framework.problem.ActionsFunction;
import aima.core.search.framework.problem.ResultFunction;

public class AtascoFunctionFactory {

    private static ActionsFunction _actionsFunction = null;
    private  static ResultFunction _resultFunction = null;

    public static ActionsFunction getAtionsFunction()
    {
        if(_actionsFunction == null)
            _actionsFunction = new AtascoActionsFunction();
        return _actionsFunction;
    }

    public static ResultFunction getResultFunction()
    {
        if(_resultFunction == null)
            _resultFunction = new AtascoResultFunction();
        return _resultFunction;

    }
	
	
}
