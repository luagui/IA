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
		// si se cumplen las precondiciones y no se va a un estado imposible entonces
		// se añade la acción a la lista de acciones posibles
		if (estado.movimientoValido(AtascoEstado.V0A)) {
			System.out.print("Se puede mover V0A \n");
			actions.add(AtascoEstado.V0A);
		}
		if (estado.movimientoValido(AtascoEstado.V1A)) {
			System.out.print("Se puede mover V1A \n");
			actions.add(AtascoEstado.V1A);
		}
		if (estado.movimientoValido(AtascoEstado.V2A)) {
			System.out.print("Se puede mover V2A \n");
			actions.add(AtascoEstado.V2A);
		}
		if (estado.movimientoValido(AtascoEstado.V3A)) {
			System.out.print("Se puede mover V3A \n");
			actions.add(AtascoEstado.V3A);
		}
		if (estado.movimientoValido(AtascoEstado.V4A)) {
			System.out.print("Se puede mover V4A \n");
			actions.add(AtascoEstado.V4A);
		}
		if (estado.movimientoValido(AtascoEstado.V5A)) {
			System.out.print("Se puede mover V5A \n");
			actions.add(AtascoEstado.V5A);
		}
		if (estado.movimientoValido(AtascoEstado.V6A)) {
			System.out.print("Se puede mover V6A \n");
			actions.add(AtascoEstado.V6A);
		}
		if (estado.movimientoValido(AtascoEstado.V7A)) {
			System.out.print("Se puede mover V7A \n");
			actions.add(AtascoEstado.V7A);
		}
		
		if (estado.movimientoValido(AtascoEstado.V0B)) {
			System.out.print("Se puede mover V0B \n");
			actions.add(AtascoEstado.V0B);
		}
		if (estado.movimientoValido(AtascoEstado.V1B)) {
			System.out.print("Se puede mover V1B \n");
			actions.add(AtascoEstado.V1B);
		}
		if (estado.movimientoValido(AtascoEstado.V2B)) {
			System.out.print("Se puede mover V2B \n");
			actions.add(AtascoEstado.V2B);
		}
		if (estado.movimientoValido(AtascoEstado.V3B)) {
			System.out.print("Se puede mover V3B \n");
			actions.add(AtascoEstado.V3B);
		}
		if (estado.movimientoValido(AtascoEstado.V4B)) {
			System.out.print("Se puede mover V4B \n");
			actions.add(AtascoEstado.V4B);
		}
		if (estado.movimientoValido(AtascoEstado.V5B)) {
			System.out.print("Se puede mover V5B \n");
			actions.add(AtascoEstado.V5B);
		}
		if (estado.movimientoValido(AtascoEstado.V6B)) {
			System.out.print("Se puede mover V6B \n");
			actions.add(AtascoEstado.V6B);
		}
		if (estado.movimientoValido(AtascoEstado.V7B)) {
			System.out.print("Se puede mover V7B \n");
			actions.add(AtascoEstado.V7B);
		}
		return actions;
	}
	
}
