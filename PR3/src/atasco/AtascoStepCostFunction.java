package atasco;

import aima.core.agent.Action;
import aima.core.search.framework.problem.StepCostFunction;

public class AtascoStepCostFunction implements StepCostFunction{

    public double c(Object stateFrom, Action action, Object stateTo) {
        return 1;
    }
}
