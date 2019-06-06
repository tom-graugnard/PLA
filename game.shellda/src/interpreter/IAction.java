package interpreter;

import game.shellda.Element;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAction {
	
	public IAction(){}
	void exec(Element e){}
	
	public class Egg extends IAction{
		Egg(){
		}
		void exec(Element e) {
			e.egg();
		}
	}
	
	public static class Hit extends IAction {
		Direction direction ;
		
		public Hit(Direction direction){
			this.direction = direction ;
		}
		
		void exec(Element e){
			e.hit(this.direction);
		}
	}
	
	public class Pop extends IAction{
		Pop(){
			
		}
		void exec(Element e) {
			e.pop();
		}
	}
	
	public class Wizz extends IAction{
		Wizz(){
			
		}
		void exec(Element e) {
			e.wizz();
		}
	}
	
	
	
	public static class Move extends IAction {
		Direction direction ;
		
		public Move(Direction direction){
			this.direction = direction ;
		}
		
		void exec(Element e){
			e.move(this.direction) ;
		}
	}
	
}
