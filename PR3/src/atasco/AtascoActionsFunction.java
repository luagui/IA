package atasco;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ActionsFunction;




public class AtascoActionsFunction implements ActionsFunction {

	public Set<Action> actions(Object state) {
		AtascoEstado estado = (AtascoEstado) state;
		// lista de acciones posibles
		Set<Action> actions = new LinkedHashSet<Action>();
		// si se cumplen las precondiciones y no se va a un estado de peligro entonces
		// se añade la acción a la lista de acciones posibles
		if (estado.movimientoValido(AtascoEstado.V0A)) {
			actions.add(AtascoEstado.V0A);
		}
		if (estado.movimientoValido(AtascoEstado.V1A)) {
			actions.add(AtascoEstado.V1A);
		}
		if (estado.movimientoValido(AtascoEstado.V2A)) {
			actions.add(AtascoEstado.V2A);
		}
		if (estado.movimientoValido(AtascoEstado.V3A)) {
			actions.add(AtascoEstado.V3A);
		}
		if (estado.movimientoValido(AtascoEstado.V4A)) {
			actions.add(AtascoEstado.V4A);
		}
		if (estado.movimientoValido(AtascoEstado.V6A)) {
			actions.add(AtascoEstado.V6A);
		}
		if (estado.movimientoValido(AtascoEstado.V7A)) {
			actions.add(AtascoEstado.V7A);
		}
		
		if (estado.movimientoValido(AtascoEstado.V0B)) {
			actions.add(AtascoEstado.V0B);
		}
		if (estado.movimientoValido(AtascoEstado.V1B)) {
			actions.add(AtascoEstado.V1B);
		}
		if (estado.movimientoValido(AtascoEstado.V2B)) {
			actions.add(AtascoEstado.V2B);
		}
		if (estado.movimientoValido(AtascoEstado.V3B)) {
			actions.add(AtascoEstado.V3B);
		}
		if (estado.movimientoValido(AtascoEstado.V4B)) {
			actions.add(AtascoEstado.V4B);
		}
		if (estado.movimientoValido(AtascoEstado.V6B)) {
			actions.add(AtascoEstado.V6B);
		}
		if (estado.movimientoValido(AtascoEstado.V7B)) {
			actions.add(AtascoEstado.V7B);
		}
		return actions;
	}
	
}
