package interpreter;

import game.shellda.Element;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ITransition {
	ICondition condition ;
	IAction action ;
	IState target ;
	
	public ITransition(ICondition condition, IAction action, IState target){
		this.condition = condition ;
		this.action = action ;
		this.target = target ;
	}
	
	boolean feasible(Element e) {
		// teste si la condition de la transition est satisfaite
		return condition.eval(e);
	}
	
	IState exec(Element e) {
		// execute l'action
		// return l'état cible de la transition 
		action.exec(e);
		return target;
	}
}
