package interpreter;

import game.shellda.Element;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	ICondition() {
	}

	boolean eval(Element e) {
		return true;
	} // à redéfinir dans chaque sous-classe
	
	public class CanMove extends IAction{
		boolean Canmove(Element e, Direction direction){
			switch(direction) {
			case NORTH:
				if(1==0) {
					return true;
				}
				else
					return false;
			case SOUTH:
				if(1==0) {
					return true;
				}
				else
					return false;
			case EAST:
				if(1==0) {
					return true;
				}
				else
					return false;
			case WEST:
				if(1==0) {
					return true;
				}
				else
					return false;
				
			default:
				return false;
			}
			
			
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