package atasco;

import aima.core.search.framework.evalfunc.HeuristicFunction;
import atasco.AtascoEstado;

public class AtascoBloqHeuristicFunction implements HeuristicFunction {

    @Override
    public double h(Object o) {
        AtascoEstado estado = (AtascoEstado) o;
        
        return 0;
    }

}
