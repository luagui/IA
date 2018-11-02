package atasco;

import aima.core.agent.Action;
import aima.core.search.framework.StepCostFunction;

public class AtascoStepCostFunction {

	@Override
    public double c(Object stateFrom, Action action, Object stateTo) {
        return 1;
    }
	
}
