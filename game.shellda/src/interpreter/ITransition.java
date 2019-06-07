package interpreter;

import game.shellda.Element;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ITransition {
	ICondition m_condition ;
	IAction m_action ;
	IState m_target ;
	
	public ITransition(ICondition condition, IAction action, IState target){
		m_condition = condition ;
		m_action = action ;
		m_target = target ;
	}
	
	boolean feasible(Element e) {
		return m_condition.eval(e);
	}
	
	IState exec(Element e) {
		m_action.exec(e);
		return m_target;
	}
}
