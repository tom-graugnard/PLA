package interpreter;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAutomaton {
	IState current;
	List<IBehaviour> behaviours;

	IAutomaton(IState initial, List<IBehaviour> behaviours) {
		this.current = initial;
		this.behaviours = behaviours;
	}

	boolean step(Entity e) throws Exception {
		// - selectionne le comportement en fonction de l'état courant
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		// return true si une transition effectuée, false si aucune transition possible
		// (=?= mort de l'automate ?)

		Iterator<IBehaviour> iter = behaviours.iterator();

		IBehaviour currentBehaviour = null;
		while (iter.hasNext()) {
			IBehaviour b = iter.next();
			if (b.source.name.equals(current.name) || b.source.id == current.id) {
				currentBehaviour = b;
				break;
			}
		}

		if (currentBehaviour == null) {
			throw new Exception("Aucune transition possible\n");
		}
		IState tmp = current;
		current = currentBehaviour.step(e);
		if (tmp == current)
			return false;
		return true;
	}
}
