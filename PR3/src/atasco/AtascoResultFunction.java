package atasco;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ResultFunction;


public class AtascoResultFunction implements ResultFunction {

	public Object result(Object s, Action a) {
		AtascoEstado estado = (AtascoEstado) s;
		if (AtascoEstado.V0A.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveA(0);
			return nuevoEstado;
		}
		if (AtascoEstado.V0B.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveB(0);
			return nuevoEstado;
		}
		if (AtascoEstado.V1A.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveA(1);
			return nuevoEstado;
		}
		if (AtascoEstado.V1B.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveB(1);
			return nuevoEstado;
		}
		if (AtascoEstado.V2A.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveA(2);
			return nuevoEstado;
		}
		if (AtascoEstado.V2B.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveB(2);
			return nuevoEstado;
		}
		if (AtascoEstado.V3A.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveA(3);
			return nuevoEstado;
		}
		if (AtascoEstado.V3B.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveB(3);
			return nuevoEstado;
		}
		if (AtascoEstado.V4A.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveA(4);
			return nuevoEstado;
		}
		if (AtascoEstado.V4B.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveB(4);
			return nuevoEstado;
		}
		if (AtascoEstado.V5A.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveA(5);
			return nuevoEstado;
		}
		if (AtascoEstado.V5B.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveB(5);
			return nuevoEstado;
		}
		if (AtascoEstado.V6A.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveA(6);
			return nuevoEstado;
		}
		if (AtascoEstado.V6B.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveB(6);
			return nuevoEstado;
		}
		if (AtascoEstado.V7A.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveA(7);
			return nuevoEstado;
		}
		if (AtascoEstado.V7B.equals(a)) {
			AtascoEstado nuevoEstado = new AtascoEstado(estado);
			nuevoEstado.moveB(7);
			return nuevoEstado;
		}

		// The Action is not understood or is a NoOp
		// the result will be the current state.
		return s;
	}

}
