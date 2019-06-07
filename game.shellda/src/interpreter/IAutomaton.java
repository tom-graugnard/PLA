package interpreter;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import game.shellda.Element;


/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAutomaton {
	public String m_name;
	
	public IState m_current;
	List<IBehaviour> m_behaviours;

	public IAutomaton(IState initial, List<IBehaviour> behaviours, String name) {
		m_name = name;
		m_current = initial;
		m_behaviours = behaviours;
	}

	public boolean step(Element e) throws Exception {
		// - selectionne le comportement en fonction de l'état courant
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		// return true si une transition effectuée, false si aucune transition possible
		// (=?= mort de l'automate ?)

		Iterator<IBehaviour> iter = m_behaviours.iterator();

		IBehaviour currentBehaviour = null;
		while (iter.hasNext()) {
			IBehaviour b = iter.next();
			if (b.source.name.equals(m_current.name) ) {
				currentBehaviour = b;
				break;
			}
		}

		if (currentBehaviour == null) {
			throw new Exception("Aucune transition possible\n");
		}
		IState tmp = m_current;
		m_current = currentBehaviour.step(e);
		if (tmp == m_current)
			return false;
		return true;
	}
}
