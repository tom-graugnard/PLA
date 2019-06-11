package interpreter;

import java.util.Iterator;
import java.util.List;

import game.shellda.Element;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	IState m_source;
	List<ITransition> m_transitions;

	public IBehaviour(IState source, List<ITransition> transitions) {
		m_source = source;
		m_transitions = transitions;
	}

	IState step(Element e) {
		Iterator<ITransition> iter = m_transitions.iterator();
		while (iter.hasNext()) {
			ITransition t = iter.next();
			if (t.feasible(e)) {
				return t.exec(e);
			}
		}
		return m_source;
	}
}
