package interpreter;

import game.shellda.Clink;
import game.shellda.Element;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	boolean m_cond;

	public ICondition() {
	}

	boolean eval(Element e) {
		return true;
	} // à redéfinir dans chaque sous-classe

	public static class CanMove extends ICondition {
		Direction direction;
		
		public CanMove(Direction direction) {
			this.direction = direction;
		}

		boolean eval(Element e) {
			return e.canmove(direction);
		}

	}
	
	public static class CanHit extends ICondition {
		Direction direction;
		
		public CanHit(Direction direction) {
			this.direction = direction;
		}

		boolean eval(Element e) {
			return e.canhit(direction);
		}

	}
	

	/*
	 * public class True extends Condition { True(){} boolean eval(Entity e) {
	 * return true; } }
	 * 
	 * public class Cell extends Condition { Direction direction ; Kind kind ;
	 * Distance distance ;
	 * 
	 * Cell(Direction direction, Kind kind, Distance distance){ this.direction =
	 * direction ; this.kind = kind ; this.distance = distance ; }
	 * 
	 * Cell(Direction direction, Kind kind){ this.direction = direction ; this.kind
	 * = kind ; this.distance = 1 ; }
	 * 
	 * boolean eval(Entity e) { return is_Kind(this.kind, this.direction,
	 * this.distance, e.position, e.map) ; } }
	 * 
	 * public class GotPower extends Condition { GotPower(){} boolean eval(Entity e)
	 * { return (e.power > 0) ; } }
	 */

}